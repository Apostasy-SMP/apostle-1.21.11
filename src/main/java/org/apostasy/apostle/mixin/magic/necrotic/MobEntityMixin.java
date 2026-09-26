package org.apostasy.apostle.mixin.magic.necrotic;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = MobEntity.class)
public abstract class MobEntityMixin {
    @ModifyReturnValue(method = "getTarget", at = @At(value = "RETURN"))
    private LivingEntity apostle$denyTargetingOwner(LivingEntity target) {
        MobEntity mob = (MobEntity) (Object) this;

        if (mob.getType().isIn(EntityTypeTags.UNDEAD)) {
            LivingEntity owner = LazyEntityReference.getLivingEntity(mob.getAttached(ApostleAttachmentTypes.OWNER), mob.getEntityWorld());

            if (owner != null) {
                if (owner == target) {
                    return null;
                }

                if (owner.getAttacker() != null) {
                    return owner.getAttacker();
                }
            }
        }
        return target;
    }
}
