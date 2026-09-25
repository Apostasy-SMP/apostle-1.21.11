package org.apostasy.apostle.datagen.providers.server.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.DamageTypeTags;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleDamageTypeTagProvider extends FabricTagProvider<DamageType> {
    public ApostleDamageTypeTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.DAMAGE_TYPE, registriesFuture);
    }

    protected void configure(RegistryWrapper.WrapperLookup registries) {
        this.getTagBuilder(DamageTypeTags.BYPASSES_ARMOR)
                .add(ApostleDamageTypes.ELECTRIFIED.getValue());

        this.getTagBuilder(DamageTypeTags.BYPASSES_COOLDOWN)
                .add(ApostleDamageTypes.WATER_BOLT.getValue());
    }
}
