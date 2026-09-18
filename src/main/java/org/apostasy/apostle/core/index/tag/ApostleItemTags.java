package org.apostasy.apostle.core.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleItemTags {
    TagBuilder<Item> tag = new TagBuilder<>(Apostle.MOD_ID, RegistryKeys.ITEM);

    TagKey<Item> CALLER_VEX_ITEMS = tag.register("caller_vex_items");
    TagKey<Item> CROW_FOOD = tag.register("crow_food");
}
