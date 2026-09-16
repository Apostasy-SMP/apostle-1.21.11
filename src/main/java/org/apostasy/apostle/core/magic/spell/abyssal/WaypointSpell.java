package org.apostasy.apostle.core.magic.spell.abyssal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.api.magic.data.Waypoint;
import org.apostasy.apostle.core.cca.entity.data.WaypointComponent;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class WaypointSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        WaypointComponent waypoint = WaypointComponent.KEY.get(caster);
        Waypoint point = waypoint.getValue();

        if (world instanceof ServerWorld serverWorld) {
            if (point != null) {
                if (serverWorld.getRegistryKey() == point.dimension()) {
                    caster.teleportTo(
                            new TeleportTarget(
                                    serverWorld,
                                    point.position().toCenterPos(),
                                    caster.getVelocity(),
                                    caster.getYaw(),
                                    caster.getPitch(),
                                    TeleportTarget.NO_OP
                            )
                    );
                    waypoint.setValue(null);
                }
            } else {
                waypoint.setValue(new Waypoint(serverWorld.getRegistryKey(), caster.getBlockPos()));
            }
        }
    }

    public void tickCharge(World world, LivingEntity user) {
        for (int i = 0; i < 15; i++) {
            float bound = 2.4F;
            Random random = new Random();

            Vec3d spawnPos = new Vec3d(
                    user.getX() + random.nextFloat(-bound, bound),
                    (user.getY() + 1.0F) + random.nextFloat(-bound, bound),
                    user.getZ() + random.nextFloat(-bound, bound)
            );

            Vec3d velocity = user.getEntityPos().subtract(spawnPos).normalize().negate().multiply(-0.1F);

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

    public List<ParticleEffect> getChargeParticleEffects() {
        return List.of(
                ParticleTypes.END_ROD,
                ParticleTypes.PORTAL
        );
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.ENDER_EYE,
                Items.ENDER_PEARL,
                Items.PAPER,
                Items.BLAZE_ROD
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.ABYSSAL;
    }

    public String getName() {
        return "Waypoint";
    }

    public int getCastTime() {
        return (5 * 20);
    }

    public int getCooldown() {
        return (10 * 20);
    }
}
