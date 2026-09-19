package org.apostasy.apostle.core.item;

import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;
import net.minecraft.server.world.ServerWorld;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.ApostleCriterions;
import org.apostasy.apostle.core.index.magic.Schools;
import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

import java.util.Collections;

/**
 * @author Chemthunder
 */
public class WoodenTotemItem extends Item {
    public WoodenTotemItem(Settings settings) {
        super(settings);
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);

        if (slot == EquipmentSlot.OFFHAND) {
            if (entity instanceof LivingEntity living) {
                if (living.getHealth() < getTotemPopLevel()) {
                    this.pop(living, stack, world);
                }
            }
        }
    }

    public void pop(LivingEntity living, ItemStack stack, ServerWorld world) {
        stack.split(1);
        this.getComponent().applyDeathEffects(stack, living);
        world.spawnParticles(
                new MagicParticleEffect(
                        this.getSchool().color(),
                        new Quaternionf(0, 0, 0, 0)
                ),
                living.getX(),
                living.getY() + 1.0F,
                living.getZ(),
                15,
                0,
                0,
                0,
                0.1F
        );

        Apostle.grantAchievement(ApostleCriterions.POP_WOODEN_TOTEM, living);
    }

    public MagicSchool getSchool() {
        return Schools.WILD;
    }

    public DeathProtectionComponent getComponent() {
        return new DeathProtectionComponent(
                Collections.singletonList(
                        new ApplyEffectsConsumeEffect(
                                new StatusEffectInstance(StatusEffects.REGENERATION, 30)
                        )
                )
        );
    }

    public float getTotemPopLevel() {
        return (4 * 2);
    }
}
