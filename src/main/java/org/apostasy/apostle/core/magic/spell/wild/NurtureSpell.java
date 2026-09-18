package org.apostasy.apostle.core.magic.spell.wild;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class NurtureSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        ItemStack bonemealStack = new ItemStack(Items.BONE_MEAL);
        bonemealStack.setCount(64);

        BoneMealItem.createParticles(world, caster.getBlockPos(), 15);
        BoneMealItem.useOnGround(bonemealStack, world, caster.getBlockPos(), null);
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
