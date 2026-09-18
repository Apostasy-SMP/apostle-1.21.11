package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

public class DetectSoulsSpell implements Spell {
    @Override
    public void cast (World world, LivingEntity caster) {

        Box detector = new Box(caster.getBlockPos()).expand(32);

        for (Entity entity : world.getEntitiesByClass(Entity.class, detector, entity -> true)) {
            if (entity instanceof LivingEntity livingEntity) {
                livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 600));
            }
        }
    }

    @Override
    public List<Item> getIngredients() {
        return List.of(
                Items.SPECTRAL_ARROW,
                Items.SPECTRAL_ARROW,
                Items.GLOWSTONE_DUST,
                Items.GLOWSTONE_DUST,
                Items.SOUL_SAND
        );
    }

    @Override
    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    @Override
    public String getName() {
        return "Detect Souls";
    }

    @Override
    public int getCastTime() {
        return (8 * 20);
    }

    @Override
    public int getCooldown() {
        return (60 * 20);
    }
}
