package org.apostasy.apostle.core.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.BillboardParticleSubmittable;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.random.Random;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import static net.minecraft.util.math.ColorHelper.*;

/**
 * @author Chemthunder
 */
public class MagicParticleType extends AnimatedParticle {
    private final SpriteProvider provider;
    private final Vector3f color;
    private final Quaternionf quaternion;

    public MagicParticleType(ClientWorld world, double x, double y, double z, double velX, double velY, double velZ, SpriteProvider spriteProvider, float upwardsAcceleration, MagicParticleEffect effect) {
        super(world, x, y, z, spriteProvider, upwardsAcceleration);
        Quaternionf quat = new Quaternionf(effect.rotation());

        this.provider = spriteProvider;
        this.color = toVector(effect.color());

        this.quaternion = quat;
        this.setVelocity(velX, velY, velZ);
    }

    protected void render(BillboardParticleSubmittable submittable, Camera camera, Quaternionf rotation, float tickProgress) {
        this.setColor(color.x, color.y, color.z);
        this.getRotator().setRotation(quaternion, MinecraftClient.getInstance().gameRenderer.getCamera(), tickProgress);
        super.render(submittable, camera, rotation, tickProgress);
    }

    public void tick() {
        super.tick();

        if (this.alpha > 0.01F) {
            this.alpha -= 0.025F;
        }
    }

    public static Vector3f toVector(int rgb) {
        float f = getRed(rgb) / 255.0F;
        float g = getGreen(rgb) / 255.0F;
        float h = getBlue(rgb) / 255.0F;
        return new Vector3f(f, g, h);
    }

    public static double random(double min, double max) {
        return Math.random() * (max - min) + min;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<MagicParticleEffect> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public MagicParticleType createParticle(MagicParticleEffect parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
            return new MagicParticleType(world, x, y, z, velocityX, velocityY, velocityZ, spriteProvider, 0.0F, parameters);
        }
    }
}
