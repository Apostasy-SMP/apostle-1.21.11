package org.apostasy.apostle.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.apostasy.apostle.core.item.StaffItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void apostle$haltStaffUsageWhenTakingDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (StaffItem.isActive(self)) {
            if (self instanceof PlayerEntity player) {
                if (!player.getItemCooldownManager().isCoolingDown(player.getMainHandStack())) {
                    player.stopUsingItem();
                    player.getItemCooldownManager().set(player.getMainHandStack(), (2 * 20));
                }
            } else {
                self.stopUsingItem();
            }
        }
    }

    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    private void apostle$increaseBloodlustPower(LivingEntity adversary, CallbackInfo ci) {
        if (adversary instanceof PlayerEntity player) {
            BloodlustComponent lust = BloodlustComponent.KEY.get(player);

            if (lust.getDuration() > 0) {
                if (lust.getModifier() < 6) {
                    lust.setModifier(lust.getModifier() + 1);
                }
            }
        }
    }
}
