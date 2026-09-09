package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.api.TomeItem;
import org.apostasy.apostle.core.Apostle;

public interface ApostleItems {
    ItemRegistrant plugin = new ItemRegistrant(Apostle.MOD_ID);

    private Item createTome(String name, TomeItem tome) {
        Item built = Items.register(RegistryKey.of(RegistryKeys.ITEM, Apostle.id(name + "_tome")), settings -> tome, new Item.Settings()
                .maxCount(1)
        );
        plugin.toRegister.add(built);
        return built;
    }

    static void init() {}
}
