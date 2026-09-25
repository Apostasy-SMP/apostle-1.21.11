package org.apostasy.apostle.datagen.providers.server.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import org.apostasy.apostle.core.index.ApostleBlocks;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ApostleBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries) {
        for (Block block : ApostleBlocks.SCALES) {
            this.valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(block)
                    .setReplace(false);
        }
        this.valueLookupBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ApostleBlocks.AMETHYST_SCALE_BLOCK)
                .setReplace(false);
    }
}
