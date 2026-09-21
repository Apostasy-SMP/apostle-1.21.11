package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.cca.entity.tick.RocketComponent;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class RocketSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        caster.addVelocity(0, 1.4, 0);

        RocketComponent rocketComponent = RocketComponent.KEY.get(caster);

        rocketComponent.trigger();

        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            float xBound = 0.5F;
            float yBound = 0.4F;
            float velBound = 1.5F;
            float velOrigin = 0.5F;

            world.addParticleClient(
                    ParticleTypes.FLAME,
                    caster.getX() + random.nextFloat(-xBound, xBound),
                    caster.getY() + random.nextFloat(-yBound, yBound),
                    caster.getZ() + random.nextFloat(-xBound, xBound),
                    0,
                    random.nextFloat(velOrigin, velBound),
                    0
            );

            world.addParticleClient(
                    ParticleTypes.FLAME,
                    caster.getX(),
                    caster.getY(),
                    caster.getZ(),
                    random.nextFloat(-velBound, velBound),
                    random.nextFloat(-velBound, velBound),
                    random.nextFloat(-velBound, velBound)
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
