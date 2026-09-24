package org.apostasy.apostle.mixin.magic.fireball;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;
import org.apostasy.apostle.core.utilities.ModUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = FireballEntity.class)
public abstract class FireballEntityMixin {

    @Inject(
            method = "onCollision",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/projectile/FireballEntity;discard()V"
            )
    )
    private void apostle$buffFireball(HitResult hitResult, CallbackInfo ci) {
        FireballEntity self = (FireballEntity) (Object) this;

        if (self.getEntityWorld() instanceof ServerWorld serverWorld) {
            if (Boolean.TRUE.equals(self.getAttached(ApostleAttachmentTypes.IS_SPECIAL_FIREBALL))) {
                for (LivingEntity target : ModUtil.getNearbyLiving(self.getEntityWorld(), hitResult.getPos(), 3, (living -> true))) {
                    target.damage(serverWorld, target.getDamageSources().fireball(self, self.getOwner()), 6.0F);
                }
            }
        }
    }
}
