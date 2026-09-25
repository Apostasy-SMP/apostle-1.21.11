package org.apostasy.apostle.datagen.providers.server.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.tag.ApostleItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ApostleItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries) {
        this.getTagBuilder(ApostleItemTags.CALLER_VEX_ITEMS)
                .addOptional(Identifier.of("quartermaster", "iron_cutlass"))
                .addOptional(Identifier.of("quartermaster", "iron_estoc"));

        this.valueLookupBuilder(ApostleItemTags.CALLER_VEX_ITEMS)
                .add(Items.IRON_SWORD)
                .add(Items.IRON_AXE)
                .add(Items.GOLDEN_PICKAXE);

        this.valueLookupBuilder(ApostleItemTags.CROW_FOOD)
                .add(Items.GOLD_NUGGET)
                .add(Items.IRON_NUGGET);

        this.valueLookupBuilder(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ApostleItems.SACRIFICIAL_KNIFE);

        this.getTagBuilder(ApostleItemTags.GOLD_SUBSTITUTES)
                .add(Registries.ITEM.getId(Items.GOLD_INGOT))
                .addOptional(Identifier.of("apostate", "chthonic_gold_ingot"));
    }
}
