package org.apostasy.apostle.core.cca.entity.tick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.apostasy.apostle.api.cca.TickDownComponent;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.utilities.ModUtil;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

/**
 * @author Chemthunder
 */
public class RocketComponent extends TickDownComponent<LivingEntity, RocketComponent> {
    public static final ComponentKey<RocketComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("rocket"),
            RocketComponent.class
    );

    public RocketComponent(LivingEntity obj) {
        super(KEY, obj, (20), RocketComponent::onFinishAscent);
    }

    public static void onFinishAscent(LivingEntity living) {
        World world = living.getEntityWorld();

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.EXPLOSION_EMITTER,
                    living.getX(),
                    living.getY() + 1.0F,
                    living.getZ(),
                    1,
                    0,
                    0,
                    0,
                    0.1F
            );

            for (LivingEntity target : ModUtil.getNearbyEntities(world, living.getBlockPos(), 10, LivingEntity.class)) {
                target.damage(serverWorld, target.getDamageSources().explosion(living, living), 15);
            }
        }
    }
}
