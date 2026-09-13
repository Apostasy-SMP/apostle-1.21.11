package org.apostasy.apostle.api.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface Spell {
    void cast(World world, PlayerEntity caster);

    List<Item> getIngredients();

    MagicSchool getMagicSchool();

    String getName();

    int getCastTime();

    int getCooldown();

    default int getHealthSacrifice() {
        return 0;
    }
}
