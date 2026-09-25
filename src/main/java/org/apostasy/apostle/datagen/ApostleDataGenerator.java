package org.apostasy.apostle.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;
import org.apostasy.apostle.datagen.providers.client.ApostleLanguageProvider;
import org.apostasy.apostle.datagen.providers.client.ApostleModelProvider;
import org.apostasy.apostle.datagen.providers.client.ApostleParticleProvider;
import org.apostasy.apostle.datagen.providers.server.ApostleAdvancementProvider;
import org.apostasy.apostle.datagen.providers.server.ApostleDynamicRegistryProvider;
import org.apostasy.apostle.datagen.providers.server.tag.ApostleBlockTagProvider;
import org.apostasy.apostle.datagen.providers.server.tag.ApostleDamageTypeTagProvider;
import org.apostasy.apostle.datagen.providers.server.tag.ApostleItemTagProvider;
import org.apostasy.apostle.datagen.providers.server.ApostleRecipeProvider;

/**
 * @author Chemthunder
 */
public class ApostleDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
		var pack = fdg.createPack();

		/// CLIENT ===
		pack.addProvider(ApostleModelProvider::new);
		pack.addProvider(ApostleLanguageProvider::new);
		pack.addProvider(ApostleParticleProvider::new);

		/// SERVER ===
		pack.addProvider(ApostleAdvancementProvider::new);
		pack.addProvider(ApostleDynamicRegistryProvider::new);
		pack.addProvider(ApostleRecipeProvider::new);

		pack.addProvider(ApostleItemTagProvider::new);
		pack.addProvider(ApostleDamageTypeTagProvider::new);
		pack.addProvider(ApostleBlockTagProvider::new);
	}

	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, ApostleDamageTypes.plugin::bootstrap);
	}
}
