package org.apostasy.apostle.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract DamageSources getDamageSources();

    @WrapOperation(
            method = "onStruckByLightning",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"
            )
    )
    private boolean apostle$tripleThunderboltDamage(Entity instance, ServerWorld serverWorld, DamageSource damageSource, float v, Operation<Boolean> original) {
        Entity entity = damageSource.getAttacker();

        if (entity instanceof LightningEntity lightning) {
            if (Boolean.TRUE.equals(lightning.getAttached(ApostleAttachmentTypes.IS_THUNDERSTRIKE))) {
                return original.call(instance, serverWorld, this.getDamageSources().create(ApostleDamageTypes.ELECTRIFIED), v * 2);
            }
        }
        return original.call(instance, serverWorld, damageSource, v);
    }
}
