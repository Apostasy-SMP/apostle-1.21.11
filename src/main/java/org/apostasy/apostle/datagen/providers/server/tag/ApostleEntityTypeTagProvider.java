package org.apostasy.apostle.datagen.providers.server.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.tag.ApostleEntityTypeTags;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleEntityTypeTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public ApostleEntityTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries) {
        this.valueLookupBuilder(ApostleEntityTypeTags.HIDE_OUTLINE)
                .add(ApostleEntityTypes.CALLER_VEX)
                .add(ApostleEntityTypes.CROW)
                .setReplace(true);
    }
}
