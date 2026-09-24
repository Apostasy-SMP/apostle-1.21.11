package org.apostasy.apostle.core.client.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.item.TomeItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class ItemGroupCyclingEvents {
    public static final List<Item> DISPLAYED_ITEMS = new ArrayList<>();
    public static int index = 0;

    public static Item fetch() {
        return DISPLAYED_ITEMS.get(index);
    }

    public static void init() {
        for (Item item : ApostleItems.plugin.toRegister) {
            if (item instanceof TomeItem) {
                DISPLAYED_ITEMS.add(item);
            }
        }

        ClientTickEvents.START_CLIENT_TICK.register(new Tick());
    }

    public static class Tick implements ClientTickEvents.StartTick {
        public void onStartTick(MinecraftClient client) {
            PlayerEntity player = client.player;
            if (player == null) return;

            if (player.age % 20 == 0) {
                if (index < DISPLAYED_ITEMS.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }
            }
        }
    }
}
