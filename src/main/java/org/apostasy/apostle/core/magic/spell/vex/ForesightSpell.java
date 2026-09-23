package org.apostasy.apostle.core.magic.spell.vex;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class ForesightSpell implements Spell {
    public void cast(World world, LivingEntity caster) {

    }

    public List<Item> getIngredients() {
        return List.of(
                Items.TOTEM_OF_UNDYING,
                Items.OMINOUS_BOTTLE,
                Items.PAPER,
                Items.TOTEM_OF_UNDYING,
                Items.ENCHANTED_BOOK
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.VEX;
    }

    public String getName() {
        return "";
    }

    public int getCastTime() {
        return 0;
    }

    public int getCooldown() {
        return 0;
    }
}
