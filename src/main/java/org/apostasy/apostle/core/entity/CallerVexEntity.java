package org.apostasy.apostle.core.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jspecify.annotations.Nullable;

import java.util.EnumSet;

/**
 * @author Chemthunder
 */
@SuppressWarnings("deprecation")
public class CallerVexEntity extends HostileEntity implements Ownable {
    public static final int field_28645 = MathHelper.ceil(3.9269907F);
    protected static final TrackedData<Byte> VEX_FLAGS;
    private @Nullable LazyEntityReference<LivingEntity> owner;
    private @Nullable BlockPos bounds;
    private boolean alive;
    private int lifeTicks;

    public CallerVexEntity(EntityType<? extends CallerVexEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new VexMoveControl(this);
        this.experiencePoints = 3;
    }

    public boolean isFlappingWings() {
        return this.age % field_28645 == 0;
    }

    protected boolean shouldTickBlockCollision() {
        return !this.isRemoved();
    }

    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
        if (this.alive && --this.lifeTicks <= 0) {
            this.lifeTicks = 20;
            this.serverDamage(this.getDamageSources().starve(), 1.0F);
        }

        if (this.age >= (45 * 20)) {
            this.discard();
        }
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new ChargeTargetGoal());
        this.goalSelector.add(8, new LookAtTargetGoal());
        this.goalSelector.add(9, new LookAtEntityGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, (new RevengeGoal(this, RaiderEntity.class)).setGroupRevenge());
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, LivingEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, 14.0F).add(EntityAttributes.ATTACK_DAMAGE, 4.0F);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VEX_FLAGS, (byte)0);
    }

    protected void readCustomData(ReadView view) {
        super.readCustomData(view);
        this.bounds = view.read("bound_pos", BlockPos.CODEC).orElse(null);
        view.getOptionalInt("life_ticks").ifPresentOrElse(this::setLifeTicks, () -> this.alive = false);
        this.owner = LazyEntityReference.fromData(view, "owner");
    }

    public void copyFrom(Entity original) {
        super.copyFrom(original);
        if (original instanceof CallerVexEntity CallerVexEntity) {
            this.owner = CallerVexEntity.owner;
        }

    }

    protected void writeCustomData(WriteView view) {
        super.writeCustomData(view);
        view.putNullable("bound_pos", BlockPos.CODEC, this.bounds);
        if (this.alive) {
            view.putInt("life_ticks", this.lifeTicks);
        }

        LazyEntityReference.writeData(this.owner, view, "owner");
    }

    public @Nullable LivingEntity getOwner() {
        return LazyEntityReference.resolve(this.owner, this.getEntityWorld(), LivingEntity.class);
    }

    public @Nullable BlockPos getBounds() {
        return this.bounds;
    }

    private boolean areFlagsSet() {
        int i = this.dataTracker.get(VEX_FLAGS);
        return (i & 1) != 0;
    }

    private void setVexFlag(boolean value) {
        int i = this.dataTracker.get(VEX_FLAGS);
        if (value) {
            i |= 1;
        } else {
            i &= ~1;
        }

        this.dataTracker.set(VEX_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.areFlagsSet();
    }

    public void setCharging(boolean charging) {
        this.setVexFlag(charging);
    }

    public void setOwner(LivingEntity owner) {
        this.owner = LazyEntityReference.of(owner);
    }

    public void setLifeTicks(int lifeTicks) {
        this.alive = true;
        this.lifeTicks = lifeTicks;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VEX_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_VEX_HURT;
    }

    public float getBrightnessAtEyes() {
        return 1.0F;
    }

    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        Random random = world.getRandom();
        this.initEquipment(random, difficulty);
        this.updateEnchantments(world, random, difficulty);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    protected void initEquipment(Random random, LocalDifficulty localDifficulty) {
        this.equipStack(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0.0F);
    }

    static {
        VEX_FLAGS = DataTracker.registerData(CallerVexEntity.class, TrackedDataHandlerRegistry.BYTE);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    class VexMoveControl extends MoveControl {
        public VexMoveControl(final CallerVexEntity owner) {
            super(owner);
        }

        public void tick() {
            if (this.state == State.MOVE_TO) {
                Vec3d vec3d = new Vec3d(this.targetX - CallerVexEntity.this.getX(), this.targetY - CallerVexEntity.this.getY(), this.targetZ - CallerVexEntity.this.getZ());
                double d = vec3d.length();
                if (d < CallerVexEntity.this.getBoundingBox().getAverageSideLength()) {
                    this.state = State.WAIT;
                    CallerVexEntity.this.setVelocity(CallerVexEntity.this.getVelocity().multiply(0.5F));
                } else {
                    CallerVexEntity.this.setVelocity(CallerVexEntity.this.getVelocity().add(vec3d.multiply(this.speed * 0.05 / d)));
                    if (CallerVexEntity.this.getTarget() == null) {
                        Vec3d vec3d2 = CallerVexEntity.this.getVelocity();
                        CallerVexEntity.this.setYaw(-((float)MathHelper.atan2(vec3d2.x, vec3d2.z)) * (180F / (float)Math.PI));
                    } else {
                        double e = CallerVexEntity.this.getTarget().getX() - CallerVexEntity.this.getX();
                        double f = CallerVexEntity.this.getTarget().getZ() - CallerVexEntity.this.getZ();
                        CallerVexEntity.this.setYaw(-((float)MathHelper.atan2(e, f)) * (180F / (float)Math.PI));
                    }
                    CallerVexEntity.this.bodyYaw = CallerVexEntity.this.getYaw();
                }
            }
        }
    }

    class ChargeTargetGoal extends Goal {
        public ChargeTargetGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            LivingEntity livingEntity = CallerVexEntity.this.getTarget();
            if (livingEntity != null && livingEntity.isAlive() && !CallerVexEntity.this.getMoveControl().isMoving() && CallerVexEntity.this.random.nextInt(toGoalTicks(7)) == 0) {
                return CallerVexEntity.this.squaredDistanceTo(livingEntity) > (double)4.0F;
            } else {
                return false;
            }
        }

        public boolean shouldContinue() {
            return CallerVexEntity.this.getMoveControl().isMoving() && CallerVexEntity.this.isCharging() && CallerVexEntity.this.getTarget() != null && CallerVexEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingEntity = CallerVexEntity.this.getTarget();
            if (livingEntity != null) {
                Vec3d vec3d = livingEntity.getEyePos();
                CallerVexEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0F);
            }

            CallerVexEntity.this.setCharging(true);
            CallerVexEntity.this.playSound(SoundEvents.ENTITY_VEX_CHARGE, 1.0F, 1.0F);
        }

        public void stop() {
            CallerVexEntity.this.setCharging(false);
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingEntity = CallerVexEntity.this.getTarget();
            if (livingEntity != CallerVexEntity.this.getOwner()) {
                if (!(livingEntity instanceof CallerVexEntity)) {
                    if (livingEntity != null) {
                        if (CallerVexEntity.this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
                            CallerVexEntity.this.tryAttack(castToServerWorld(CallerVexEntity.this.getEntityWorld()), livingEntity);
                            CallerVexEntity.this.setCharging(false);
                        } else {
                            double d = CallerVexEntity.this.squaredDistanceTo(livingEntity);
                            if (d < (double) 9.0F) {
                                Vec3d vec3d = livingEntity.getEyePos();
                                CallerVexEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0F);
                            }
                        }
                    }
                }
            }
        }
    }

    class LookAtTargetGoal extends Goal {
        public LookAtTargetGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            return !CallerVexEntity.this.getMoveControl().isMoving() && CallerVexEntity.this.random.nextInt(toGoalTicks(7)) == 0;
        }

        public boolean shouldContinue() {
            return false;
        }

        public void tick() {
            BlockPos blockPos = CallerVexEntity.this.getBounds();
            if (blockPos == null) {
                blockPos = CallerVexEntity.this.getBlockPos();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockPos2 = blockPos.add(CallerVexEntity.this.random.nextInt(15) - 7, CallerVexEntity.this.random.nextInt(11) - 5, CallerVexEntity.this.random.nextInt(15) - 7);
                if (CallerVexEntity.this.getEntityWorld().isAir(blockPos2)) {
                    CallerVexEntity.this.moveControl.moveTo((double)blockPos2.getX() + (double)0.5F, (double)blockPos2.getY() + (double)0.5F, (double)blockPos2.getZ() + (double)0.5F, 0.25F);
                    if (CallerVexEntity.this.getTarget() == null) {
                        CallerVexEntity.this.getLookControl().lookAt((double)blockPos2.getX() + (double)0.5F, (double)blockPos2.getY() + (double)0.5F, (double)blockPos2.getZ() + (double)0.5F, 180.0F, 20.0F);
                    }
                    break;
                }
            }
        }
    }
}
