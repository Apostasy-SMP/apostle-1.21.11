package org.apostasy.apostle.core.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleTrackedData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class ParticleEntity extends ThrownEntity {
    public static final TrackedData<RegistryKey<DamageType>> DAMAGE_TYPE = DataTracker.registerData(ParticleEntity.class, ApostleTrackedData.DAMAGE_TYPE_KEY);
    public static final TrackedData<ParticleEffect> SPAWNED_EFFECT = DataTracker.registerData(ParticleEntity.class, ApostleTrackedData.PARTICLE_EFFECT);
    public static final TrackedData<List<String>> FLAGS = DataTracker.registerData(ParticleEntity.class, ApostleTrackedData.STRING_LIST);

    public static final TrackedData<Integer> AMOUNT_TO_DEAL = DataTracker.registerData(ParticleEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Float> SIZE = DataTracker.registerData(ParticleEntity.class, TrackedDataHandlerRegistry.FLOAT);
    public static final TrackedData<Integer> AMOUNT_OF_PARTICLES = DataTracker.registerData(ParticleEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ParticleEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public static ParticleEntity create(World world, RegistryKey<DamageType> damageType, ParticleEffect effect, int amountToDeal) {
        final ParticleEntity entity = new ParticleEntity(ApostleEntityTypes.PARTICLE_DAMAGE, world);
        entity.setDamageType(damageType);
        entity.setSpawnedEffect(effect);
        entity.setAmountToDeal(amountToDeal);
        return entity;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(DAMAGE_TYPE, DamageTypes.ARROW);
        builder.add(SPAWNED_EFFECT, ParticleTypes.ENCHANT);
        builder.add(AMOUNT_TO_DEAL, 0);

        builder.add(FLAGS, new ArrayList<>());
        builder.add(SIZE, 0.5F);
        builder.add(AMOUNT_OF_PARTICLES, 5);
    }

    public void tick() {
        super.tick();

        Random rand = new Random();

        for (int i = 0; i < this.getAmountToSpawn(); i++) {
            float bound = this.getSize();

            this.getEntityWorld().addImportantParticleClient(
                    this.getParticleEffect(),
                    this.getX() + rand.nextFloat(-bound, bound),
                    this.getY() + rand.nextFloat(-bound, bound),
                    this.getZ() + rand.nextFloat(-bound, bound),
                    0,
                    0,
                    0
            );
        }

        if (this.age >= (20 * 10)) {
            this.discard();
        }
    }

    public boolean hasNoGravity() {
        return true;
    }

    protected void onBlockCollision(BlockState state) {
        if (!state.isIn(BlockTags.AIR) && !state.isTransparent()) {
            this.discard();
        }
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (entity != null) {
            if (entity instanceof LivingEntity living) {
                if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                    if (this.getOwner() != null) {
                        living.damage(serverWorld, living.getDamageSources().create(this.getDamageType(), this.getOwner()), this.getAmountToDeal());
                    } else {
                        living.damage(serverWorld, living.getDamageSources().create(this.getDamageType()), this.getAmountToDeal());
                    }

                    if (this.getFlags().contains(Flags.FIRE)) {
                        living.setOnFireForTicks(4 * 20);
                    }

                    if (this.getFlags().contains(Flags.LIFESTEAL)) {
                        if (this.getOwner() != null && this.getOwner() instanceof LivingEntity owner) {
                            owner.heal(living.getHealth() / 2F);
                        }
                    }
                    this.discard();
                }
            }
        }
    }

    public RegistryKey<DamageType> getDamageType() {
        return this.dataTracker.get(DAMAGE_TYPE);
    }

    public void setDamageType(RegistryKey<DamageType> type) {
        this.dataTracker.set(DAMAGE_TYPE, type);
    }

    public ParticleEffect getParticleEffect() {
        return this.dataTracker.get(SPAWNED_EFFECT);
    }

    public void setSpawnedEffect(ParticleEffect effect) {
        this.dataTracker.set(SPAWNED_EFFECT, effect);
    }

    public int getAmountToDeal() {
        return this.dataTracker.get(AMOUNT_TO_DEAL);
    }

    public void setAmountToDeal(int i) {
        this.dataTracker.set(AMOUNT_TO_DEAL, i);
    }

    public List<String> getFlags() {
        return this.dataTracker.get(FLAGS);
    }

    public void withFlag(String flag) {
        List<String> setFlags = new ArrayList<>(getFlags());
        setFlags.add(flag);
        this.dataTracker.set(FLAGS, setFlags);
    }

    public int getAmountToSpawn() {
        return this.dataTracker.get(AMOUNT_OF_PARTICLES);
    }

    public void setAmountOfSpawn(int integer) {
        this.dataTracker.set(AMOUNT_OF_PARTICLES, integer);
    }

    public float getSize() {
        return this.dataTracker.get(SIZE);
    }

    public void setSize(float fag) {
        this.dataTracker.set(SIZE, fag);
    }

    public interface Flags {
        String LIFESTEAL = "lifesteal";
        String FIRE = "fire";
    }
}
