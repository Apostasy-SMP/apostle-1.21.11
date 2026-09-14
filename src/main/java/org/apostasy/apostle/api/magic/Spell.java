package org.apostasy.apostle.api.magic;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
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

    default List<ParticleEffect> getChargeParticleEffects() {
        return List.of(
                ParticleTypes.END_ROD
        );
    }

    default boolean isUnobtainable() {
        return false;
    }
}
