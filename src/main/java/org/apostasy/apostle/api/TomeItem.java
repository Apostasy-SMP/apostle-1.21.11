package org.apostasy.apostle.api;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author Chemthunder
 */
public class TomeItem extends Item {
    private final String id;
    private final List<Ingredient> ritualIngredients;

    public TomeItem(String id, List<Ingredient> ritualIngredients) {
        super(new Settings()
                .maxCount(1));

        this.id = id;
        this.ritualIngredients = ritualIngredients;
    }

    public void tickRitual(World world) {}
}
