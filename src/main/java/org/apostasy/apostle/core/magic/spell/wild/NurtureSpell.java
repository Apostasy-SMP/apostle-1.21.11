package org.apostasy.apostle.core.magic.spell.wild;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.utilities.ModUtil;

import java.util.List;

/**
 * @author Chemthunder
 */
public class NurtureSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d pos = caster.raycast(15, 0, false).getPos();
        BlockPos bPos = ModUtil.toBlockPos(pos);

        ItemStack bonemealStack = new ItemStack(Items.BONE_MEAL);
        bonemealStack.setCount(64);

        BoneMealItem.createParticles(world, bPos, 15);

        for (int i = 0; i < 6; i++) {
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos);
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.north());
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.north(1));

            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.south());
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.south(1));

            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.east());
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.east(1));

            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.west());
            BoneMealItem.useOnFertilizable(bonemealStack, world, bPos.west(1));
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.BONE_MEAL,
                Items.MOSS_BLOCK,
                Items.STICK,
                Items.BONE,
                Items.GRASS_BLOCK
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WILD;
    }

    public String getName() {
        return "Nurture";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (40 * 20);
    }
}
