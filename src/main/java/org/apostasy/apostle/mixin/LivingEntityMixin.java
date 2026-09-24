package org.apostasy.apostle.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.ApostleStatusEffects;
import org.apostasy.apostle.core.item.StaffItem;
import org.apostasy.apostle.core.utilities.ModUtil;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, ServerWaypoint {
    @Shadow
    public abstract boolean hasStatusEffect(RegistryEntry<StatusEffect> effect);

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At(value = "HEAD"))
    private void apostle$haltStaffUsageWhenTakingDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;
        Entity entity = source.getAttacker();

        if (StaffItem.isActive(self)) {
            if (self instanceof PlayerEntity player) {
                if (!player.getItemCooldownManager().isCoolingDown(player.getMainHandStack())) {
                    if (!ModUtil.getHotbarItems(self).contains(ApostleItems.STURDY_STONE)) {
                        player.stopUsingItem();
                        player.getItemCooldownManager().set(player.getMainHandStack(), (2 * 20));
                    }
                }
            } else {
                self.stopUsingItem();
            }
        }

        if (entity instanceof LivingEntity living) {
            if (living.getMainHandStack().getOrDefault(ApostleComponentTypes.MODIFIER_LIFESTEAL, false)) {
                living.heal(self.getHealth() / 4F);
            }
        }
    }

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void apostle$lockVelocity(CallbackInfo ci) {
        if (this.hasStatusEffect(ApostleStatusEffects.ROOTED)) {
            this.setVelocity(0, this.getVelocity().y, 0);
        }
    }

    @WrapOperation(
            method = "spawnItemParticles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"
            )
    )
    private void accursed$customEatParticles(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original) {
        LivingEntity living = (LivingEntity) (Object)this;

        if (living.getStackInHand(living.getActiveHand()).isOf(ApostleItems.MAGIC_DUST)) {
            original.call(instance, new MagicParticleEffect(0xFFe486bb, new Quaternionf(0, 0, 0, 0)), x, y, z, velocityX, velocityY, velocityZ);
            return;
        }
        original.call(instance, parameters, x, y, z, velocityX, velocityY, velocityZ);
    }
}
