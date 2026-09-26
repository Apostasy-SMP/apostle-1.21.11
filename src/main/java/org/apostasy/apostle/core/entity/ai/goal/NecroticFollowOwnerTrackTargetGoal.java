package org.apostasy.apostle.core.entity.ai.goal;

import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;

/**
 * @author Chemthunder
 */
public class NecroticFollowOwnerTrackTargetGoal extends ActiveTargetGoal<LivingEntity> {
    public NecroticFollowOwnerTrackTargetGoal(MobEntity mob, boolean checkVisibility) {
        super(mob, LivingEntity.class, checkVisibility);
    }

    protected void findClosestTarget() {
        if (this.mob.getAttached(ApostleAttachmentTypes.OWNER) != null) {
            LivingEntity owner = LazyEntityReference.getLivingEntity(this.mob.getAttached(ApostleAttachmentTypes.OWNER), this.mob.getEntityWorld());
            if (owner != null) {
                if (owner.getAttacker() != null) {
                    this.targetEntity = owner.getAttacker();
                }
            }
        }
    }
}
