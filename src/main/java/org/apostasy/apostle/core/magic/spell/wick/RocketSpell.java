package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class RocketSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        caster.addVelocity(0, 1, 0);

        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            float bound = 2.6F;

            world.addParticleClient(
                    ParticleTypes.FLAME,
                    caster.getX() + random.nextFloat(-bound, bound),
                    caster.getY(),
                    caster.getZ() + random.nextFloat(-bound, bound),
                    0,
                    1,
                    0
            );

            world.addParticleClient(
                    ParticleTypes.FLAME,
                    caster.getX() + random.nextFloat(-bound, bound),
                    caster.getY(),
                    caster.getZ() + random.nextFloat(-bound, bound),
                    0,
                    0.8F,
                    0
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.FIREWORK_ROCKET,
                Items.FIRE_CHARGE,
                Items.PAPER,
                Items.BLAZE_ROD,
                Items.FLINT_AND_STEEL
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Rocket";
    }

    public int getCastTime() {
        return 0;
    }

    public int getCooldown() {
        return (20 * 20);
    }
}
