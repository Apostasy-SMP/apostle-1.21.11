package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.CreativeModeTabRegistrant;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.abs.ArtifactItem;
import org.apostasy.apostle.core.item.BloodItem;
import org.apostasy.apostle.core.item.abs.ConjuredItem;
import org.apostasy.apostle.core.item.TomeItem;
import org.apostasy.apostle.core.item.component.StoredSpellComponent;

import java.util.function.Predicate;

/**
 * @author Chemthunder
 */
public interface ApostleItemGroups {
    CreativeModeTabRegistrant plugin = new CreativeModeTabRegistrant(Apostle.MOD_ID);

    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Apostle.id(Apostle.MOD_ID));

    static void init() {
        plugin.register(GROUP_KEY.getValue().getPath(), FabricItemGroup.builder()
                .icon(() -> new ItemStack(ApostleItems.WILD_TOME))
                .displayName(Text.literal(MiscUtils.formatString(Apostle.MOD_ID)).withColor(0xFF9740aa))
                .build());

        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(ApostleItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries entries) {
        entries.add(Apostle.createStackWithComponent(ApostleItems.STAFF, ApostleComponentTypes.SCHOOL, Schools.NONE));
        entries.add(ApostleItems.MAGIC_DUST);

        applyEntries(entries, (item -> item instanceof TomeItem));

        for (Spell spell : ApostleRegistries.SPELL) {
            ItemStack scrollStack = new ItemStack(ApostleItems.SPELL_SCROLL);
            scrollStack.set(ApostleComponentTypes.STORED_SPELL, new StoredSpellComponent(spell));

            entries.add(scrollStack);
        }

        for (MagicSchool school : ApostleRegistries.MAGIC_SCHOOL) {
            if (school != Schools.NONE) {
            ItemStack staffStack = new ItemStack(ApostleItems.STAFF);
            staffStack.set(ApostleComponentTypes.SCHOOL, school);

            entries.add(staffStack);
            }
        }

        applyEntries(entries, (item -> item instanceof ArtifactItem));

        entries.add(ApostleItems.SACRIFICIAL_KNIFE);

        applyEntries(entries, (item -> item instanceof BloodItem));

        applyEntries(entries, (item -> item instanceof ConjuredItem));
    }

    private static void applyEntries(FabricItemGroupEntries entries, Predicate<? super Item> predicate) {
        for (Item item : Registries.ITEM) {
            if (predicate.test(item)) {
                entries.add(item);
            }
        }
    }
}
