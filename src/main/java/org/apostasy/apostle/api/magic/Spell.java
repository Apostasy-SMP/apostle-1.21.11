package org.apostasy.apostle.api.magic;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

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

    default void tickCharge(World world, LivingEntity user) {}

    default int getHealthSacrifice() {
        return 0;
    }

    default List<ParticleEffect> getChargeParticleEffects() {
        return List.of(
                ParticleTypes.END_ROD
        );
    }

    default void createChargeParticles(World world, LivingEntity user) {
        Vec3d particlePos = user.raycast(1.3, 0, false).getPos();

        world.addParticleClient(
                this.getChargeParticleEffects().get(new Random().nextInt(this.getChargeParticleEffects().size())),
                particlePos.x,
                particlePos.y,
                particlePos.z,
                0,
                0,
                0
        );
    }

    default boolean isUnobtainable() {
        return false;
    }
}
