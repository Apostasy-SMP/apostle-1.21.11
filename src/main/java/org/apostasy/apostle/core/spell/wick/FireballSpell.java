package org.apostasy.apostle.core.spell.wick;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.core.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class FireballSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {}

    public List<Item> getIngredients() {
        return List.of(
                Items.FIRE_CHARGE,
                Items.BLAZE_POWDER,
                Items.BLAZE_ROD,
                Items.BLACKSTONE,
                Items.PAPER
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Fireball";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (15 * 20);
    }
}
