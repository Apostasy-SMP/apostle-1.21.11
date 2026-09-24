package org.apostasy.apostle.datagen.providers.server;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleDynamicRegistryProvider extends FabricDynamicRegistryProvider {
    public ApostleDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        ApostleDamageTypes.plugin.addEntries(registries, entries);
    }

    public String getName() {
        return "Dynamic Registries";
    }
}
