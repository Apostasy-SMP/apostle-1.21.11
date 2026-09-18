package org.apostasy.apostle.core.client.particle;

import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;

/**
 * @author Chemthunder
 */
public class RootParticleType extends AnimatedParticle {
    public RootParticleType(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float upwardsAcceleration) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);
    }

    public void tick() {
        super.tick();

        if (alpha > 0.0) {
            alpha -= 0.15F;
        }
    }
}
