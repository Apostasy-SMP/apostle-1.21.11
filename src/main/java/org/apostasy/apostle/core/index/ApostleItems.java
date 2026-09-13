package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.api.item.SpellScrollItem;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.core.Schools;
import org.apostasy.apostle.core.item.tome.*;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface ApostleItems {
    ItemRegistrant plugin = new ItemRegistrant(Apostle.MOD_ID);

    /// ELDRITCH
    Item ABYSSAL_TOME = createTome("abyssal", new AbyssalTomeItem("abyssal", List.of(
            Items.STICK
    ), Schools.ABYSSAL));

    Item CALLER_TOME = createTome("caller", new CallerTomeItem("caller", List.of(
            Items.STICK
    ), Schools.CALLER));

    Item GORE_TOME = createTome("gore", new GoreTomeItem("gore", List.of(
            Items.STICK
    ), Schools.GORE));

    Item VEX_TOME = createTome("vex", new VexTomeItem("vex", List.of(
            Items.STICK
    ), Schools.VEX));


    /// WEAVE
    Item WASTE_TOME = createTome("waste", new WasteTomeItem("waste", List.of(
            Items.STICK
    ), Schools.WASTE));

    Item WAVE_TOME = createTome("wave", new WaveTomeItem("wave", List.of(
            Items.STICK
    ), Schools.WAVE));

    Item WICK_TOME = createTome("wick", new WickTomeItem("wick", List.of(
            Items.STICK
    ), Schools.WICK));

    Item WILD_TOME = createTome("wild", new WildTomeItem("wild", List.of(
            Items.STICK
    ), Schools.WILD));

    Item WIND_TOME = createTome("wind", new WindTomeItem("wind", List.of(
            Items.STICK
    ), Schools.WIND));

    Item WORSHIP_TOME = createTome("worship", new WorshipTomeItem("worship", List.of(
            Items.STICK
    ), Schools.WORSHIP));


    /// SPELLS
    Item SPELL_SCROLL = plugin.register("spell_scroll", SpellScrollItem::new, new Item.Settings()
            .maxCount(1)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Apostle.id("spell_scroll")))
    );

    private static Item createTome(String name, TomeItem tome) {
        return plugin.register(name + "_tome", settings -> tome, new Item.Settings()
        );
    }

    static void init() {}
}
