package org.apostasy.apostle.datagen.providers.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.index.ApostleBlocks;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.tag.ApostleItemTags;
import org.apostasy.apostle.core.item.StaffItem;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleRecipeProvider extends FabricRecipeProvider {
    public ApostleRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            public void generate() {
                createShaped(RecipeCategory.TOOLS, ApostleItems.STAFF)
                        .pattern(" /A")
                        .pattern(" g/")
                        .pattern("/  ")
                        .input('/', Items.STICK)
                        .input('A', Items.AMETHYST_CLUSTER)
                        .input('g', ApostleItemTags.GOLD_SUBSTITUTES)
                        .criterion("has_amethyst_shard", conditionsFromItem(Items.AMETHYST_CLUSTER))
                        .offerTo(exporter);

                createShaped(RecipeCategory.BUILDING_BLOCKS, ApostleBlocks.AMETHYST_SCALE_BLOCK, 8)
                        .pattern("CS")
                        .pattern("SC")
                        .input('C', Items.AMETHYST_CLUSTER)
                        .input('S', Items.AMETHYST_SHARD)
                        .criterion("has_amethyst_shard", conditionsFromItem(Items.AMETHYST_CLUSTER))
                        .offerTo(exporter);
            }
        };
    }

    public String getName() {
        return "";
    }
}
