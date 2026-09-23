package org.apostasy.apostle.core.utilities;

import net.minecraft.entity.Entity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Chemthunder
 */
public class ModUtil {
    public static <E extends Entity> List<E> getNearbyEntities(World world, BlockPos pos, int radius, Class<E> eClass) {
        return getNearbyEntities(world, pos, radius, eClass, EntityPredicates.EXCEPT_SPECTATOR);
    }

    public static <E extends Entity> List<E> getNearbyEntities(World world, BlockPos pos, int radius, Class<E> eClass, Predicate<? super E> predicate) {
        return world.getEntitiesByClass(eClass, new Box(pos).expand(radius + (radius / 2d)), predicate);
    }

    public static <E extends Entity> List<E> getNearbyEntities(World world, Vec3d pos, int radius, Class<E> eClass) {
        return getNearbyEntities(world, pos, radius, eClass, EntityPredicates.EXCEPT_SPECTATOR);
    }

    public static <E extends Entity> List<E> getNearbyEntities(World world, Vec3d pos, int radius, Class<E> eClass, Predicate<? super E> predicate) {
        return world.getEntitiesByClass(eClass, new Box(toBlockPos(pos)).expand(radius + (radius / 2d)), predicate);
    }

    public static BlockPos toBlockPos(Vec3d vec3d) {
        return new BlockPos.Mutable(
                vec3d.x,
                vec3d.y,
                vec3d.z
        );
    }
}
