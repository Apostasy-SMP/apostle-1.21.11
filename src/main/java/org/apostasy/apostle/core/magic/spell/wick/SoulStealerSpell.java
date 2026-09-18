package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.entity.ParticleEntity;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class SoulStealerSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        ParticleEntity entity = ParticleEntity.create(world, DamageTypes.IN_FIRE, ParticleTypes.SOUL_FIRE_FLAME, 5);
        entity.setPosition(caster.getX(), caster.getY() + 1.5F, caster.getZ());
        entity.setVelocity(caster.getRotationVec(0).multiply(1));
        entity.setOwner(caster);
        entity.withFlag(ParticleEntity.Flags.FIRE);
        entity.withFlag(ParticleEntity.Flags.LIFESTEAL);
        world.spawnEntity(entity);

        Vec3d particlePos = caster.raycast(1.3, 0, false).getPos();

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    particlePos.x,
                    particlePos.y,
                    particlePos.z,
                    10,
                    0,
                    0,
                    0,
                    0.1F
            );
        }
    }

    public void createChargeParticles(World world, LivingEntity user, int progress) {
        Spell.super.createChargeParticles(world, user, progress);
    }

    public List<ParticleEffect> getChargeParticleEffects() {
        return List.of(ParticleTypes.SOUL_FIRE_FLAME);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.SOUL_SAND,
                Items.SOUL_SOIL,
                Items.SOUL_TORCH,
                Items.FIRE_CHARGE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Soul Stealer";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (40 * 20);
    }
}
