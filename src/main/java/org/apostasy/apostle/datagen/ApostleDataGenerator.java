package org.apostasy.apostle.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apostasy.apostle.datagen.providers.ApostleAdvancementProvider;
import org.apostasy.apostle.datagen.providers.ApostleItemTagProvider;
import org.apostasy.apostle.datagen.providers.ApostleLanguageProvider;
import org.apostasy.apostle.datagen.providers.ApostleModelProvider;

/**
 * @author Chemthunder
 */
public class ApostleDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
		var pack = fdg.createPack();

		pack.addProvider(ApostleModelProvider::new);
		pack.addProvider(ApostleLanguageProvider::new);

		pack.addProvider(ApostleAdvancementProvider::new);

		pack.addProvider(ApostleItemTagProvider::new);
	}
}
