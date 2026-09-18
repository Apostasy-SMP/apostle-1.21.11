package org.apostasy.apostle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.magic.Schools;
import org.joml.Quaternionf;

/**
 * @author Chemthunder
 */
public class HolyNetEntity extends Entity {
    public static final TrackedData<Float> SIZE = DataTracker.registerData(HolyNetEntity.class, TrackedDataHandlerRegistry.FLOAT);

    public static final float MAX_SIZE = 10F;

    public HolyNetEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(SIZE, 0.0F);
    }

    public void tick() {
        super.tick();

        if (this.age >= (30 * 20)) {
            if (this.getSize() > 0) {
                this.setSize(this.getSize() - 0.25F);
                if (this.getSize() <= 0) {
                    if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                        serverWorld.spawnParticles(
                                new MagicParticleEffect(
                                        Schools.WORSHIP.color(),
                                        new Quaternionf(0, 0, 0, 0)
                                ),
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                8,
                                0,
                                0,
                                0,
                                0.1F
                        );

                        serverWorld.spawnParticles(
                                ParticleTypes.END_ROD,
                                this.getX(),
                                this.getY(),
                                this.getZ(),
                                8,
                                0,
                                0,
                                0,
                                0.1F
                        );
                    }
                    this.discard();
                }
            }
        } else {
            if (this.getSize() < MAX_SIZE) {
                this.setSize(this.getSize() + 0.25F);
            }
        }

        Box detector = new Box(this.getBlockPos()).expand(this.getSize() + (this.getSize() / 2));

        for (Entity entity : this.getEntityWorld().getEntitiesByClass(Entity.class, detector, entity -> true)) {
            if (entity instanceof ProjectileEntity projectile) {
                projectile.setVelocity(projectile.getVelocity().x / 2, projectile.getVelocity().y, projectile.getVelocity().z / 2);
            }
        }
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    protected void readCustomData(ReadView view) {}

    protected void writeCustomData(WriteView view) {}

    public float getSize() {
        return this.dataTracker.get(SIZE);
    }

    public void setSize(float f) {
        this.dataTracker.set(SIZE, f);
    }
}
