package org.apostasy.apostle.mixin.magic.bloodlust;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin {
    @WrapMethod(method = "damage")
    private boolean apostle$bloodlustApply(ServerWorld world, DamageSource source, float amount, Operation<Boolean> original) {
        Entity entity = source.getAttacker();

        if (entity instanceof LivingEntity attacker) {
            BloodlustComponent lust = BloodlustComponent.KEY.get(attacker);

            if (lust.getDuration() > 0) {
                return original.call(world, source, amount + lust.getModifier());
            }
        }
        return original.call(world, source, amount);
    }

    @Inject(method = "onKilledBy", at = @At(value = "HEAD"))
    private void apostle$increaseBloodlustPower(LivingEntity adversary, CallbackInfo ci) {
        if (adversary instanceof LivingEntity player) {
            BloodlustComponent lust = BloodlustComponent.KEY.get(player);

            if (lust.getDuration() > 0) {
                if (lust.getModifier() < 6) {
                    lust.setModifier(lust.getModifier() + 1);
                }
            }
        }
    }
}
