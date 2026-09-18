package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.utilities.ModUtil;

import java.util.List;

public class DetectSoulsSpell implements Spell {
    public void cast (World world, LivingEntity caster) {
        for (LivingEntity entity : ModUtil.getNearbyEntities(world, caster.getBlockPos(), 32, LivingEntity.class)) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600));
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.SPECTRAL_ARROW,
                Items.SPECTRAL_ARROW,
                Items.GLOWSTONE_DUST,
                Items.GLOWSTONE_DUST,
                Items.SOUL_SAND
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Detect Souls";
    }

    public int getCastTime() {
        return (8 * 20);
    }

    public int getCooldown() {
        return (60 * 20);
    }
}
