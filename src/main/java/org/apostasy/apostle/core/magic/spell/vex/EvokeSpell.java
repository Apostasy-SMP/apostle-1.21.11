package org.apostasy.apostle.core.magic.spell.vex;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class EvokeSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        for (int i = 1; i < 8; i++) {
            Vec3d spawnPos = caster.raycast(i * 2.5, 0, false).getPos();

            double y = spawnPos.y;

            do {
                y--;
            } while (world.getBlockState(new BlockPos((int) spawnPos.x, (int) y, (int) spawnPos.z)).isTransparent());

            world.spawnEntity(
                    new EvokerFangsEntity(
                            world,
                            spawnPos.x,
                            y + 0.5,
                            spawnPos.z,
                            caster.getYaw(),
                            0,
                            caster
                    )
            );
        }
    }

    public void createChargeParticles(World world, LivingEntity user, int progress) {
        for (int i = 1; i < 8; i++) {
            Vec3d spawnPos = user.raycast(i * 2.5, 0, false).getPos();

            double y = spawnPos.y;

            do {
                y--;
            } while (world.getBlockState(new BlockPos((int) spawnPos.x, (int) y, (int) spawnPos.z)).isTransparent());

            for (int j = 0; j < 3; j++) {
                Random random = new Random();

                world.addParticleClient(
                        new MagicParticleEffect(Schools.VEX),
                        spawnPos.x + random.nextFloat(-0.3F, 0.3F),
                        y + 0.5,
                        spawnPos.z + random.nextFloat(-0.3F, 0.3F),
                        0,
                        random.nextFloat(0.3F, 0.7F),
                        0
                );
            }
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.TOTEM_OF_UNDYING,
                Items.OMINOUS_BOTTLE,
                Items.GOLD_INGOT,
                Items.ENCHANTED_BOOK,
                Items.GOLDEN_APPLE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.VEX;
    }

    public String getName() {
        return "Evoke";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (25 * 20);
    }
}
