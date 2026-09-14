package org.apostasy.apostle.core.index.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.RitualRecipe;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface ItemCrafts {
    RitualRecipe TEST = register("test", new RitualRecipe() {
        public List<Item> getIngredients() {
            return List.of(
                    Items.STICK,
                    Items.STICK,
                    Items.STICK,
                    Items.STICK
            );
        }

        public ItemStack getOutput() {
            return Items.BEDROCK.getDefaultStack();
        }
    });

    private static RitualRecipe register(String name, RitualRecipe craft) {
        return Registry.register(ApostleRegistries.RITUAL_RECIPE, Apostle.id(name), craft);
    }

    static void init() {}
}
