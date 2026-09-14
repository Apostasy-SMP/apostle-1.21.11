package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.core.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class CataclysmSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        Vec3d particlePos = caster.raycast(1.3, 0, false).getPos();

        FireballEntity entity = new FireballEntity(world, caster, caster.getRotationVec(0).multiply(1), 200);
        entity.setPosition(new Vec3d(particlePos.x, particlePos.y + 1, particlePos.z));
        world.spawnEntity(entity);
    }

    public List<Item> getIngredients() {
        return List.of();
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Cataclysm";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (15 * 20);
    }

    public List<ParticleEffect> getChargeParticleEffects() {
        return List.of(
                ParticleTypes.FLAME,
                ParticleTypes.SMALL_FLAME,
                ParticleTypes.SMOKE
        );
    }

    public void createChargeParticles(World world, LivingEntity user) {
        Vec3d particlePos = user.raycast(1.3, 0, false).getPos();

        for (int i = 0; i < 15; i++) {
            float bound = 1.6F;
            Random random = new Random();

            Vec3d spawnPos = new Vec3d(
                    particlePos.getX() + random.nextFloat(-bound, bound),
                    (particlePos.getY() + 1.0F) + random.nextFloat(-bound, bound),
                    particlePos.getZ() + random.nextFloat(-bound, bound)
            );

            Vec3d velocity = particlePos.subtract(spawnPos).normalize().negate().multiply(-0.1F);

            world.addParticleClient(
                    this.getChargeParticleEffects().get(random.nextInt(this.getChargeParticleEffects().size())),
                    spawnPos.x,
                    spawnPos.y,
                    spawnPos.z,
                    velocity.x,
                    velocity.y,
                    velocity.z
            );
        }
    }

    public boolean isUnobtainable() {
        return true;
    }
}
