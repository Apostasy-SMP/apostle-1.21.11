package org.apostasy.apostle.core.entity;

import dev.rbn.bleedylib.api.screenshake.Screenshake;
import dev.rbn.bleedylib.api.screenshake.ScreenshakeHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.world.attribute.timeline.EasingType;
import org.apostasy.apostle.api.client.ScreenShaker;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.utilities.ModUtil;
import org.jspecify.annotations.Nullable;

import java.util.Random;

/**
 * @author Chemthunder
 */
public class EarthquakeEntity extends Entity implements Ownable {
    public EarthquakeEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {}

    public void tick() {
        super.tick();

        World world = this.getEntityWorld();

        if (this.age > (25 * 20)) {
            this.discard();
        }

        for (int i = 0; i < 50; i++) {
            Random rand = new Random();

            world.addParticleClient(
                    new BlockStateParticleEffect(ParticleTypes.BLOCK_CRUMBLE, Blocks.DIRT.getDefaultState()),
                    this.getX() + rand.nextFloat(-25, 25),
                    this.getY(),
                    this.getZ() + rand.nextFloat(-25, 25),
                    0,
                    0,
                    0
            );
        }

        ScreenshakeHandler.screenshakeAroundPoint(world, 30, 0.15F, 25, this.getEntityPos(), EasingType.OUT_EXPO);

        for (LivingEntity target : ModUtil.getNearbyLiving(world, this.getEntityPos(), 25, (entity) -> true)) {
            if (world instanceof ServerWorld serverWorld) {
                if (target != this.getOwner()) {
                    target.damage(serverWorld, target.getDamageSources().fall(), 2.0F);
                }
            }
        }
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    protected void readCustomData(ReadView view) {

    }

    protected void writeCustomData(WriteView view) {

    }

    @Nullable
    public Entity getOwner() {
        return LazyEntityReference.getLivingEntity(this.getAttached(ApostleAttachmentTypes.OWNER), this.getEntityWorld());
    }
}
