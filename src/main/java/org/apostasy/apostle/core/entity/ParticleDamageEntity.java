package org.apostasy.apostle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleTrackedData;

import java.util.Random;

/**
 * @author Chemthunder
 */
public class ParticleDamageEntity extends ThrownEntity {
    public static final TrackedData<RegistryKey<DamageType>> DAMAGE_TYPE = DataTracker.registerData(ParticleDamageEntity.class, ApostleTrackedData.DAMAGE_TYPE_KEY);
    public static final TrackedData<ParticleEffect> SPAWNED_EFFECT = DataTracker.registerData(ParticleDamageEntity.class, ApostleTrackedData.PARTICLE_EFFECT);

    public static final TrackedData<Integer> AMOUNT_TO_DEAL = DataTracker.registerData(ParticleDamageEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public ParticleDamageEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public static ParticleDamageEntity create(World world, RegistryKey<DamageType> damageType, ParticleEffect effect, int amountToDeal) {
        final ParticleDamageEntity entity = new ParticleDamageEntity(ApostleEntityTypes.PARTICLE_DAMAGE, world);
        entity.setDamageType(damageType);
        entity.setSpawnedEffect(effect);
        entity.setAmountToDeal(amountToDeal);
        return entity;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(DAMAGE_TYPE, DamageTypes.ARROW);
        builder.add(SPAWNED_EFFECT, ParticleTypes.ENCHANT);
        builder.add(AMOUNT_TO_DEAL, 0);
    }

    public void tick() {
        super.tick();

        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            this.getEntityWorld().addImportantParticleClient(
                    this.getParticleEffect(),
                    this.getX() + rand.nextFloat(-0.5F, 0.5F),
                    this.getY() + rand.nextFloat(-0.5F, 0.5F),
                    this.getZ() + rand.nextFloat(-0.5F, 0.5F),
                    0,
                    0,
                    0
            );
        }
    }

    public boolean hasNoGravity() {
        return true;
    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (entity != null) {
            if (entity instanceof LivingEntity living) {
                if (this.getEntityWorld() instanceof ServerWorld serverWorld) {
                    if (this.getOwner() != null) {
                        living.damage(serverWorld, living.getDamageSources().create(this.getDamageType(), this.getOwner()), this.getAmountToDeal());
                    } else {
                        living.damage(serverWorld, living.getDamageSources().create(this.getDamageType()), this.getAmountToDeal());
                    }
                    this.discard();
                }
            }
        }
    }

    public RegistryKey<DamageType> getDamageType() {
        return this.dataTracker.get(DAMAGE_TYPE);
    }

    public void setDamageType(RegistryKey<DamageType> type) {
        this.dataTracker.set(DAMAGE_TYPE, type);
    }

    public ParticleEffect getParticleEffect() {
        return this.dataTracker.get(SPAWNED_EFFECT);
    }

    public void setSpawnedEffect(ParticleEffect effect) {
        this.dataTracker.set(SPAWNED_EFFECT, effect);
    }

    public int getAmountToDeal() {
        return this.dataTracker.get(AMOUNT_TO_DEAL);
    }

    public void setAmountToDeal(int i) {
        this.dataTracker.set(AMOUNT_TO_DEAL, i);
    }
}
