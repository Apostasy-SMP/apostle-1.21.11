package org.apostasy.apostle.core.magic.spell.wave;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.entity.ParticleEntity;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleParticleTypes;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class WaterBoltSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d raycastPos = caster.raycast(1.3, 0, true).getPos();

        for (int i = 0; i < 5; i++) {
            ParticleEntity entity = new ParticleEntity(ApostleEntityTypes.PARTICLE_DAMAGE, world);
            entity.setSpawnedEffect(ApostleParticleTypes.WAVE);
            entity.setAmountOfSpawn(6);
            entity.setDamageType(ApostleDamageTypes.WATER_BOLT);
            entity.setAmountToDeal(6);
            entity.setPosition(raycastPos);
            entity.setVelocity(caster, caster.getPitch(), caster.getYaw(), 0, 2, 6);
            world.spawnEntity(entity);
        }
    }

    public void createChargeParticles(World world, LivingEntity user, int progress) {
        Vec3d raycastPos = user.raycast(1.3, 0, true).getPos();
        Random random = new Random();

        double y = raycastPos.y;

        do {
            y--;
        } while (
                world.getBlockState(
                        new BlockPos.Mutable(
                                raycastPos.x,
                                y,
                                raycastPos.z
                        )
                ).isAir()
        );

        for (int i = 0; i < 5; i++) {
            Vec3d spawnPos = new Vec3d(
                    raycastPos.x + random.nextFloat(-0.5F, 0.5F),
                    y,
                    raycastPos.z + random.nextFloat(-0.5F, 0.5F)
            );

            Vec3d velocity = spawnPos.subtract(raycastPos).normalize().negate().multiply(-0.1F);

            world.addParticleClient(
                    new MagicParticleEffect(Schools.WAVE),
                    spawnPos.x,
                    spawnPos.y,
                    spawnPos.z,
                    velocity.x,
                    random.nextFloat(0.4F, 0.9F),
                    velocity.z
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.TRIDENT,
                Items.PRISMARINE_CRYSTALS,
                Items.GRAVEL,
                Items.CLAY,
                Items.KELP,
                Items.KELP
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WAVE;
    }

    public String getName() {
        return "Water Bolt";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (25 * 20);
    }
}
