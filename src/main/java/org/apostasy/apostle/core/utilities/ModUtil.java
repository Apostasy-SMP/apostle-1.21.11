package org.apostasy.apostle.core.utilities;

import net.minecraft.client.option.HotbarStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.ArrayList;
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

    public static List<LivingEntity> getNearbyLiving(World world, Vec3d pos, int radius, Predicate<? super LivingEntity> predicate) {
        return world.getEntitiesByClass(LivingEntity.class, new Box(toBlockPos(pos)).expand(radius + (radius / 2d)), predicate);
    }

    public static BlockPos toBlockPos(Vec3d vec3d) {
        return new BlockPos.Mutable(
                vec3d.x,
                vec3d.y,
                vec3d.z
        );
    }

    public static List<Item> getHotbarItems(LivingEntity living) {
        List<Item> stacks = new ArrayList<>();

        if (living instanceof PlayerEntity player) {
            for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
                stacks.add(player.getInventory().getStack(i).getItem());
            }
        } else {
            stacks.add(living.getMainHandStack().getItem());
            stacks.add(living.getOffHandStack().getItem());
        }

        return stacks;
    }
}
