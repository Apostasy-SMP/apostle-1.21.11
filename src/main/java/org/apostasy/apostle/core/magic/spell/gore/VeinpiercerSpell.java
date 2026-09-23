package org.apostasy.apostle.core.magic.spell.gore;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class VeinpiercerSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        caster.giveOrDropStack(ApostleItems.VEINPIERCER.getDefaultStack());
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.NETHERITE_SPEAR,
                ApostleItems.PURE_BLOOD,
                ApostleItems.PURE_BLOOD,
                Items.OMINOUS_BOTTLE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.GORE;
    }

    public String getName() {
        return "Veinpiercer";
    }

    public int getCastTime() {
        return (4 * 20);
    }

    public int getCooldown() {
        return (45 * 20);
    }
}
