package org.apostasy.apostle.mixin.magic.pyromaniac;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.apostasy.apostle.core.cca.entity.tick.PyromaniacComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void apostle$setOnFireIfPyromaniac(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = source.getAttacker();
        LivingEntity self = (LivingEntity) (Object) this;

        if (entity instanceof LivingEntity living) {
            PyromaniacComponent component = PyromaniacComponent.get(living);

            if (component.isActive()) {
                self.setOnFireFor(20);
            }
        }
    }
}
