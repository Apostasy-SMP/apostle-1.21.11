package org.apostasy.apostle.core;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Chemthunder
 */
public class Apostle implements ModInitializer {
	public static final String MOD_ID = "apostle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public void onInitialize() {
		ApostleItems.init();
		ApostleEntityTypes.init();

		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
