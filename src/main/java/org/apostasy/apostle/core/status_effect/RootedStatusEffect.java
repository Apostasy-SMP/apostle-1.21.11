package org.apostasy.apostle.core.status_effect;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import org.apostasy.apostle.core.index.ApostleParticleTypes;

import java.util.Random;

/**
 * @author Chemthunder
 */
public class RootedStatusEffect extends StatusEffect {
    public RootedStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0xF0000000);
    }

    public ParticleEffect createParticle(StatusEffectInstance effect) {
        return new BlockStateParticleEffect(ParticleTypes.BLOCK_MARKER, Blocks.AIR.getDefaultState());
    }

    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        Random random = new Random();

        world.spawnParticles(
                ApostleParticleTypes.ROOT,
                entity.getX() + 0.5F + random.nextFloat(-0.5F, 0.5F),
                entity.getY() + 1.0F + random.nextFloat(-0.5F, 0.5F),
                entity.getZ() + 0.5F + random.nextFloat(-0.5F, 0.5F),
                2,
                0,
                0,
                0,
                0
        );

        return super.applyUpdateEffect(world, entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = new Random().nextInt(10, 40);
        return duration % i == 0;
    }
}
