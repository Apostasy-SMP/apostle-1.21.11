package org.apostasy.apostle.core.entity.ai;

import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class CrowNavigation extends BirdNavigation {
    public CrowNavigation(MobEntity mobEntity, World world) {
        super(mobEntity, world);
    }

    @Override
    protected boolean shouldJumpToNextNode(Vec3d currentPos) {
        if (this.currentPath.getCurrentNodeIndex() + 1 >= this.currentPath.getLength()) {
            return false;
        } else {
            Vec3d vec3d = Vec3d.ofBottomCenter(this.currentPath.getCurrentNodePos());
            if (!currentPos.isInRange(vec3d, 3.6F)) {
                return false;
            } else if (this.canPathDirectlyThrough(currentPos, this.currentPath.getNodePosition(this.entity))) {
                return true;
            } else {
                Vec3d vec3d2 = Vec3d.ofBottomCenter(this.currentPath.getNodePos(this.currentPath.getCurrentNodeIndex() + 1));
                Vec3d vec3d3 = vec3d.subtract(currentPos);
                Vec3d vec3d4 = vec3d2.subtract(currentPos);
                double d = vec3d3.lengthSquared();
                double e = vec3d4.lengthSquared();
                boolean bl = e < d;
                boolean bl2 = d < (double)0.5F;
                if (!bl && !bl2) {
                    return false;
                } else {
                    Vec3d vec3d5 = vec3d3.normalize();
                    Vec3d vec3d6 = vec3d4.normalize();
                    return vec3d6.dotProduct(vec3d5) < (double)0.0F;
                }
            }
        }
    }
}
