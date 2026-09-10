package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.item.tome.WildTomeItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface ApostleItems {
    ItemRegistrant plugin = new ItemRegistrant(Apostle.MOD_ID);

    Item WILD_TOME = createTome("wild", new WildTomeItem("wild", List.of(
            Items.STICK
    )));

    private static Item createTome(String name, TomeItem tome) {
        return plugin.register(name + "_tome", settings -> tome, new Item.Settings()
        );
    }

    static void init() {}
}
