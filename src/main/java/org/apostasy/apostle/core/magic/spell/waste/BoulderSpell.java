package org.apostasy.apostle.core.magic.spell.waste;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class BoulderSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d spawnPos = caster.raycast(1.3, 0, true).getPos();

//        FallingBlockEntity rock = new FallingBlockEntity(world, spawnPos.x, spawnPos.y, spawnPos.z, Blocks.COBBLESTONE.getDefaultState());
//
//        rock.setB
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.COBBLESTONE,
                Items.COBBLESTONE,
                Items.COBBLESTONE,
                Items.COBBLESTONE,
                Items.COBBLESTONE,
                Items.COBBLESTONE,
                Items.COBBLESTONE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WASTE;
    }

    public String getName() {
        return "Boulder";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (25 * 20);
    }
}
