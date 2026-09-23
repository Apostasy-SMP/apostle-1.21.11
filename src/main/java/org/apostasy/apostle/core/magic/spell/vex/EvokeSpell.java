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
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

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

    public int getStaffCooldown() {
        return (4 * 20);
    }
}
