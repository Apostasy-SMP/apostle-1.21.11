package org.apostasy.apostle.mixin;

import net.minecraft.entity.Attackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.waypoint.ServerWaypoint;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.apostasy.apostle.core.index.ApostleStatusEffects;
import org.apostasy.apostle.core.item.StaffItem;
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

    @Inject(method = "tick", at = @At(value = "TAIL"))
    private void apostle$lockVelocity(CallbackInfo ci) {
        if (this.hasStatusEffect(ApostleStatusEffects.ROOTED)) {
            this.setVelocity(0, this.getVelocity().y, 0);
        }
    }
}
