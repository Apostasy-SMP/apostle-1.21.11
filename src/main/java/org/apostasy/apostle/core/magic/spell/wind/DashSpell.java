package org.apostasy.apostle.core.magic.spell.wind;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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

        world.playSound(
                null,
                caster.getX(),
                caster.getY(),
                caster.getZ(),
                SoundEvents.ENTITY_BREEZE_CHARGE,
                SoundCategory.PLAYERS,
                1,
                1
        );

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.POOF,
                    caster.lastX,
                    caster.lastY,
                    caster.lastZ,
                    15,
                    0,
                    0,
                    0,
                    0.2F
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.WIND_CHARGE,
                Items.PAPER,
                Items.LEATHER,
                Items.FEATHER
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
