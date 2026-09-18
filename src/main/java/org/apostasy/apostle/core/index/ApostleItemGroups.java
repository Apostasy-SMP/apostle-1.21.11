package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.CreativeModeTabRegistrant;
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
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.item.ArtifactItem;
import org.apostasy.apostle.core.item.TomeItem;

/**
 * @author Chemthunder
 */
public interface ApostleItemGroups {
    CreativeModeTabRegistrant plugin = new CreativeModeTabRegistrant(Apostle.MOD_ID);

    RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(RegistryKeys.ITEM_GROUP, Apostle.id(Apostle.MOD_ID));
    ItemGroup ITEM_GROUP = plugin.register(GROUP_KEY.getValue().getPath(), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ApostleItems.WILD_TOME))
            .displayName(Text.translatable("itemGroup." + Apostle.MOD_ID).withColor(0xFF9740aa))
            .build());

    static void init() {
        ItemGroupEvents.modifyEntriesEvent(GROUP_KEY).register(ApostleItemGroups::addEntries);
    }

    private static void addEntries(FabricItemGroupEntries entries) {
        entries.add(ApostleItems.MAGIC_STAFF);
        entries.add(ApostleItems.MAGIC_STAFF);

        for (Item item : ApostleItems.plugin.toRegister) {
            if (item instanceof TomeItem) {
                entries.add(item);
            }
        }

        for (Spell spell : ApostleRegistries.SPELL) {
            ItemStack scrollStack = new ItemStack(ApostleItems.SPELL_SCROLL);
            scrollStack.set(ApostleComponentTypes.STORED_SPELL, new StoredSpellComponent(spell));

            entries.add(scrollStack);
        }

        for (MagicSchool school : ApostleRegistries.MAGIC_SCHOOL) {
            ItemStack staffStack = new ItemStack(ApostleItems.ARCANE_STAFF);
            staffStack.set(ApostleComponentTypes.SCHOOL, school);

            entries.add(staffStack);
        }

        for (Item item : Registries.ITEM) {
            if (item instanceof ArtifactItem) {
                entries.add(item);
            }
        }
    }
}
