package org.apostasy.apostle.core.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Tameable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.apostasy.apostle.core.entity.ai.CrowFlyGoal;
import org.apostasy.apostle.core.entity.ai.CrowMoveControl;
import org.apostasy.apostle.core.entity.ai.CrowNavigation;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.jspecify.annotations.Nullable;

public class CrowEntity extends PathAwareEntity implements Tameable {
    public CrowEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
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
        this.goalSelector.add(3, new CrowFlyGoal(this, 2));

        this.goalSelector.add(5, new LookAtEntityGoal(this, LivingEntity.class, 5F));
        this.goalSelector.add(7, new LookAroundGoal(this));

        this.targetSelector.add(0, new RevengeGoal(this, CrowEntity.class));
    }

    @Override
    public @Nullable LazyEntityReference<LivingEntity> getOwnerReference() {
        return this.getAttached(ApostleAttachmentTypes.OWNER);
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }
}
