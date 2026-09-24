package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ItemRegistrant;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.*;
import org.apostasy.apostle.core.item.artifact.BezoarItem;
import org.apostasy.apostle.core.item.artifact.QuenchingAshItem;
import org.apostasy.apostle.core.item.artifact.SturdyStoneItem;
import org.apostasy.apostle.core.item.conjured.VeinpiercerItem;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface ApostleItems {
    ItemRegistrant plugin = new ItemRegistrant(Apostle.MOD_ID);

    /// WEAVE
    Item WASTE_TOME = createTome(Schools.WASTE);
    Item WAVE_TOME = createTome(Schools.WAVE);
    Item WICK_TOME = createTome(Schools.WICK);
    Item WILD_TOME = createTome(Schools.WILD);
    Item WIND_TOME = createTome(Schools.WIND);
    Item WORSHIP_TOME = createTome(Schools.WORSHIP);

    /// ELDRITCH
    Item ABYSSAL_TOME = createTome(Schools.ABYSSAL);
    Item CALLER_TOME = createTome(Schools.CALLER);
    Item GORE_TOME = createTome(Schools.GORE);
    Item VEX_TOME = createTome(Schools.VEX);

    /// Other
    Item SPELL_SCROLL = plugin.register("spell_scroll", SpellScrollItem::new, new Item.Settings()
            .maxCount(1)
            .component(ApostleComponentTypes.SCROLL_COOLDOWN, 0)
    );

    Item STAFF = plugin.register("arcane_staff", StaffItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item MAGIC_DUST = plugin.register("magic_dust", MagicDustItem::new, new Item.Settings()
            .food(new FoodComponent.Builder().alwaysEdible().nutrition(0).build())
            .fireproof()
    );

    Item BEZOAR = plugin.register("bezoar", BezoarItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item QUENCHING_ASH = plugin.register("quenching_ash", QuenchingAshItem::new, new Item.Settings()
            .maxCount(16)
    );

    Item SACRIFICIAL_KNIFE = plugin.register("sacrificial_knife", SacrificialKnifeItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(SacrificialKnifeItem.createAttributes())
    );

    Item VILE_BLOOD = createBlood("vile",
            new StatusEffectInstance(
                    StatusEffects.SPEED,
                    (3 * 20)
            ),
            new StatusEffectInstance(
                    StatusEffects.STRENGTH,
                    (3 * 20)
            )
    );

    Item PURE_BLOOD = createBlood("pure",
            new StatusEffectInstance(
                    StatusEffects.INSTANT_HEALTH,
                    5,
                    2
            )
    );

    Item WOODEN_TOTEM = plugin.register("wooden_totem", WoodenTotemItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item TRANS_IDOL = plugin.register("transmogrification_idol", TransIdolItem::new, new Item.Settings()
            .maxCount(1)
    );

    Item VEINPIERCER = plugin.register("veinpiercer", VeinpiercerItem::new, new Item.Settings()
            .maxCount(1)
            .attributeModifiers(VeinpiercerItem.createAttributes())
            .component(ApostleComponentTypes.MODIFIER_LIFESTEAL, true)
    );

    Item STURDY_STONE = plugin.register("sturdy_stone", SturdyStoneItem::new, new Item.Settings()
            .maxCount(1)
    );

    private static Item createTome(MagicSchool school) {
        return plugin.register(school.name().getString().toLowerCase() + "_tome", settings -> new TomeItem(school), new Item.Settings());
    }

    private static Item createBlood(String name, StatusEffectInstance... instances) {
        return plugin.register(name + "_blood", settings -> new BloodItem(settings, instances), new Item.Settings()
                .maxCount(16));
    }

    private static RegistryKey<Item> rk(String name) {
        return RegistryKey.of(RegistryKeys.ITEM, Apostle.id(name));
    }

    static void init() {}
}
