package org.apostasy.apostle.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.core.item.StaffItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
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
            self.stopUsingItem();

            if (self instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(player.getMainHandStack(), (2 * 20));
            }
        }
    }
}
