package org.apostasy.apostle.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.apostasy.apostle.datagen.providers.ApostleModelProvider;

/**
 * @author Chemthunder
 */
public class ApostleDataGenerator implements DataGeneratorEntrypoint {
	public void onInitializeDataGenerator(FabricDataGenerator fdg) {
		fdg.createPack()
				.addProvider(ApostleModelProvider::new);
	}
}
