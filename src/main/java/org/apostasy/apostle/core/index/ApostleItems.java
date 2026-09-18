package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.MagicDustItem;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.apostasy.apostle.core.item.StaffItem;
import org.apostasy.apostle.core.item.TomeItem;
import org.apostasy.apostle.core.item.artifact.BezoarItem;
import org.apostasy.apostle.core.item.artifact.QuenchingAshItem;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface ApostleItems {
    ItemRegistrant plugin = new ItemRegistrant(Apostle.MOD_ID);

    /// WEAVE
    Item WASTE_TOME = createTome("waste", Schools.WASTE);

    Item WAVE_TOME = createTome("wave", Schools.WAVE);

    Item WICK_TOME = createTome("wick", Schools.WICK);

    Item WILD_TOME = createTome("wild", Schools.WILD);

    Item WIND_TOME = createTome("wind", Schools.WIND);

    Item WORSHIP_TOME = createTome("worship", Schools.WORSHIP);

    /// ELDRITCH
    Item ABYSSAL_TOME = createTome("abyssal", Schools.ABYSSAL);

    Item CALLER_TOME = createTome("caller", Schools.CALLER);

    Item GORE_TOME = createTome("gore", Schools.GORE);

    Item VEX_TOME = createTome("vex", Schools.VEX);

    /// Other
    Item SPELL_SCROLL = plugin.register("spell_scroll", SpellScrollItem::new, new Item.Settings()
            .maxCount(1)
            .component(ApostleComponentTypes.SCROLL_COOLDOWN, 0)
            .registryKey(rk("spell_scroll"))
    );

    Item MAGIC_STAFF = plugin.register("magic_staff", StaffItem::new, new Item.Settings()
            .maxCount(1)
            .registryKey(rk("magic_staff"))
    );

    Item ARCANE_STAFF = plugin.register("arcane_staff", StaffItem::new, new Item.Settings()
            .maxCount(1)
            .registryKey(rk("arcane_staff"))
    );

    Item MAGIC_DUST = plugin.register("magic_dust", MagicDustItem::new, new Item.Settings()
            .registryKey(rk("magic_dust"))
            .fireproof()
    );

    Item BEZOAR = plugin.register("bezoar", BezoarItem::new, new Item.Settings()
            .maxCount(1)
            .registryKey(rk("bezoar"))
    );

    Item QUENCHING_ASH = plugin.register("quenching_ash", QuenchingAshItem::new, new Item.Settings()
            .maxCount(16)
            .registryKey(rk("quenching_ash"))
    );

    private static Item createTome(String name, MagicSchool school) {
        return plugin.register(name + "_tome", settings -> new TomeItem(school), new Item.Settings());
    }

    private static RegistryKey<Item> rk(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Apostle.id(name));
    }

    static void init() {}
}
