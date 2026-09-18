package org.apostasy.apostle.core.entity.ai.goal;

import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jspecify.annotations.Nullable;

public class CrowFlyGoal extends WanderAroundFarGoal {
    public CrowFlyGoal(PathAwareEntity pathAwareEntity, double d) {
        super(pathAwareEntity, d);
    }

    @Override
    protected @Nullable Vec3d getWanderTarget() {
        Vec3d vec3d = this.mob.getRotationVec(0.0F);
        Vec3d vec3d2 = find(this.mob, 21, 4, vec3d.x, vec3d.z, ((float)Math.PI / 2F), 3, 1);
        return vec3d2 != null ? vec3d2 : NoPenaltySolidTargeting.find(this.mob, 14, 2, -2, vec3d.x, vec3d.z, (double)((float)Math.PI / 2F));
    }

    public static @Nullable Vec3d find(PathAwareEntity entity, int horizontalRange, int verticalRange, double x, double z, float angle, int maxAboveSolid, int minAboveSolid) {
        boolean bl = NavigationConditions.isPositionTargetInRange(entity, horizontalRange);
        return FuzzyPositions.guessBestPathTarget(entity, () -> {
            BlockPos blockPos = FuzzyPositions.localFuzz(entity.getRandom(), 7, horizontalRange, verticalRange, 0, x, z, angle);
            if (blockPos == null) {
                return null;
            } else {
                BlockPos blockPos2 = FuzzyTargeting.towardTarget(entity, horizontalRange, bl, blockPos);
                if (blockPos2 == null) {
                    return null;
                } else {
                    blockPos2 = FuzzyPositions.upWhile(blockPos2, entity.getRandom().nextInt(maxAboveSolid - minAboveSolid + 1) + minAboveSolid, entity.getEntityWorld().getTopYInclusive(), (pos) -> NavigationConditions.isSolidAt(entity, pos));
                    return !NavigationConditions.isWaterAt(entity, blockPos2) && !NavigationConditions.hasPathfindingPenalty(entity, blockPos2) ? blockPos2 : null;
                }
            }
        });
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.mob.getAttributeValue(EntityAttributes.FLYING_SPEED));
    }
}
