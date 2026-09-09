package org.apostasy.apostle.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import org.apostasy.apostle.core.index.ApostleItems;

/**
 * @author Chemthunder
 */
public class ApostleModelProvider extends FabricModelProvider {
    public ApostleModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        ApostleItems.plugin.toRegister.forEach(item -> itemModelGenerator.register(item, Models.GENERATED));
    }
}
