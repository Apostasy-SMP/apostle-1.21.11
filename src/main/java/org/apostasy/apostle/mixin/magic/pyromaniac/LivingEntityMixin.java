package org.apostasy.apostle.mixin.magic.pyromaniac;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.apostasy.apostle.core.cca.entity.tick.PyromaniacComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyReturnValue(method = "modifyAppliedDamage", at = @At("RETURN"))
    private float apostle$modifyDamage(float original, DamageSource source) {
        if (source.isDirect() && source.getAttacker() instanceof LivingEntity attacker) {
            if (PyromaniacComponent.get(attacker).isActive() && this.isOnFire()) {
                original *= 1.33F;
            }
        }
        return original;
    }
}
