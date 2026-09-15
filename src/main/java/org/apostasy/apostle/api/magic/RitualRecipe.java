package org.apostasy.apostle.api.magic;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface RitualRecipe {
    List<Item> getIngredients();

    ItemStack getOutput();

    MagicSchool getSchool();
}
