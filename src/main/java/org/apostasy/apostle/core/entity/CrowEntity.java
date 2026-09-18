package org.apostasy.apostle.core.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.apostasy.apostle.core.entity.ai.CrowMoveControl;
import org.apostasy.apostle.core.entity.ai.CrowNavigation;
import org.apostasy.apostle.core.entity.ai.goal.CrowFlyGoal;
import org.apostasy.apostle.core.entity.ai.goal.DashAtTargetGoal;
import org.apostasy.apostle.core.index.tag.ApostleItemTags;
import org.jspecify.annotations.Nullable;

public class CrowEntity extends TameableEntity {
    public CrowEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new CrowMoveControl(this, 30, 0.3F, false);

        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
        this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return createMobAttributes()
                .add(EntityAttributes.MAX_HEALTH, 8)
                .add(EntityAttributes.ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.FLYING_SPEED, 3);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new CrowNavigation(this, world);
    }


    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return world.getBlockState(pos).isAir() ? 10.0F : 0.0F;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.goalSelector.add(1, new MeleeAttackGoal(this, 0.4, false));
        this.goalSelector.add(2, new DashAtTargetGoal(this));

        this.goalSelector.add(3, new FollowOwnerGoal(this, 2, 3, 21));
        this.goalSelector.add(4, new CrowFlyGoal(this, 2));

        this.goalSelector.add(5, new LookAtEntityGoal(this, LivingEntity.class, 5F));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(0, new AttackWithOwnerGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, CrowEntity.class));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isIn(ApostleItemTags.CROW_FOOD) && !this.isTamed()) {
            if (this.getRandom().nextBoolean()) {
                this.getEntityWorld().sendEntityStatus(this, (byte)7);
                this.setTamedBy(player);
            } else {
                this.getEntityWorld().sendEntityStatus(this, (byte)6);
            }
            return ActionResult.CONSUME;
        }
        return super.interactMob(player, hand);
    }

    @Override
    public boolean shouldTryTeleportToOwner() {
        LivingEntity livingEntity = this.getOwner();
        return livingEntity != null && this.distanceTo(this.getOwner()) > 33;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {}


    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
