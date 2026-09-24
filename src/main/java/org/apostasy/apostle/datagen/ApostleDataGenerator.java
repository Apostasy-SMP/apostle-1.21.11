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
import org.apostasy.apostle.datagen.providers.server.ApostleItemTagProvider;

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
		pack.addProvider(ApostleItemTagProvider::new);
		pack.addProvider(ApostleDynamicRegistryProvider::new);
	}

	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, ApostleDamageTypes.plugin::bootstrap);
	}
}
