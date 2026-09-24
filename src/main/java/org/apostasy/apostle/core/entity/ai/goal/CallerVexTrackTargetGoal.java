package org.apostasy.apostle.core.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.core.entity.CallerVexEntity;

/**
 * @author Chemthunder
 */
public class CallerVexTrackTargetGoal extends ActiveTargetGoal<LivingEntity> {
    public CallerVexTrackTargetGoal(MobEntity mob, boolean checkVisibility) {
        super(mob, LivingEntity.class, checkVisibility);
    }

    protected void findClosestTarget() {
        ServerWorld serverWorld = getServerWorld(this.mob);
        this.targetEntity = serverWorld.getClosestEntity(
                this.mob.getEntityWorld().getEntitiesByClass(
                        LivingEntity.class,
                        this.getSearchBox(this.getFollowRange()),
                        (livingEntity) -> !(livingEntity instanceof CallerVexEntity)),
                this.getAndUpdateTargetPredicate(),
                this.mob,
                this.mob.getX(),
                this.mob.getEyeY(),
                this.mob.getZ()
        );
    }
}
