package org.apostasy.apostle.core.item;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.item.component.GrimoireComponent;
import org.apostasy.apostle.core.networking.s2c.OpenGrimoirePayload;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class GrimoireItem extends Item {
    public GrimoireItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user instanceof ServerPlayerEntity serverPlayer) {
            ServerPlayNetworking.send(serverPlayer, new OpenGrimoirePayload(user.getStackInHand(hand)));
        }
        return super.use(world, user, hand);
    }

    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        GrimoireComponent component = stack.get(ApostleComponentTypes.GRIMOIRE);

        if (component != null) {
            List<ItemStack> stacks = new ArrayList<>(component.list());

            if (clickType == ClickType.RIGHT) {
                if (!otherStack.isEmpty()) {
                    if (otherStack.isOf(ApostleItems.SPELL_SCROLL)) {
                        stacks.add(otherStack.split(1));
                        stack.set(ApostleComponentTypes.GRIMOIRE, new GrimoireComponent(stacks));
                        if (player.getEntityWorld().isClient()) {
                            player.playSound(SoundEvents.ITEM_BUNDLE_INSERT);
                        }
                        return true;
                    }
                } else {
                    ItemStack fetched = stacks.getLast();

                    cursorStackReference.set(fetched);

                    stacks.remove(fetched);
                    stack.set(ApostleComponentTypes.GRIMOIRE, new GrimoireComponent(stacks));
                    if (player.getEntityWorld().isClient()) {
                        player.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE);
                    }
                    return true;
                }
            }
        }

        return false;
    }
}
