package org.apostasy.apostle.core.event;

import net.fabricmc.fabric.api.event.player.ItemEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class RitualPushItemEvent implements UseEntityCallback {
    public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, @Nullable EntityHitResult hitResult) {
        if (hitResult != null) {
            if (hitResult.getEntity() instanceof RitualEntity ritual) {
                ItemStack stack = player.getStackInHand(hand);

                if (stack != null) {
                    if (ritual.getHeldTome() != null) {
                        TomeItem tome = ritual.getHeldTome();

                        ritual.pushStack(stack.split(1));
                    }
                }

                Apostle.LOGGER.info("a");
            }
        }
        return ActionResult.PASS;
    }
}
