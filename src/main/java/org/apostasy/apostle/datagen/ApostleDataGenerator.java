package org.apostasy.apostle.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apostasy.apostle.datagen.providers.*;

/**
 * @author Chemthunder
 */
public class ApostleDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
		var pack = fdg.createPack();

		pack.addProvider(ApostleModelProvider::new);
		pack.addProvider(ApostleLanguageProvider::new);
		pack.addProvider(ApostleParticleProvider::new);

		pack.addProvider(ApostleAdvancementProvider::new);

		pack.addProvider(ApostleItemTagProvider::new);
	}
}
