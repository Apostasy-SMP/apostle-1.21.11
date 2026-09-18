package org.apostasy.apostle.core.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;

public class DashAtTargetGoal extends Goal {
    private final PathAwareEntity mob;
    private int cooldown = 0;

    public DashAtTargetGoal(PathAwareEntity mob) {
        super();
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        return this.mob.age > this.cooldown && this.mob.getTarget() != null
                && this.mob.distanceTo(this.mob.getTarget()) > 5;
    }

    @Override
    public void start() {
        this.cooldown = this.mob.age + 70;
        LivingEntity target = this.mob.getTarget();

        if (target != null) {
            Vec3d dash = this.mob.getEyePos().relativize(target.getEyePos())
                    .normalize();
            this.mob.addVelocity(dash);
        }
    }
}
