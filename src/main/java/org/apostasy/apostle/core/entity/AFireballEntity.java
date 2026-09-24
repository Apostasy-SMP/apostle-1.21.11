package org.apostasy.apostle.core.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class AFireballEntity extends FireballEntity {
    public AFireballEntity(EntityType<? extends AFireballEntity> entityType, World world) {
        super(entityType, world);
    }

    public AFireballEntity(World world, LivingEntity owner, Vec3d velocity) {
        super(world, owner, velocity, 0);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
    }
}
