package org.apostasy.apostle.core.spell.wind;

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
public class DashSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        caster.setVelocity(caster.getRotationVec(0).multiply(2));
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.STICK,
                Items.STICK,
                Items.STICK,
                Items.STICK
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WIND;
    }

    public String getName() {
        return "Dash";
    }

    public int getCastTime() {
        return 0;
    }

    public int getCooldown() {
        return 15;
    }
}
