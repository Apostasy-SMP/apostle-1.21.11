package org.apostasy.apostle.core;

import net.fabricmc.api.ClientModInitializer;
import org.apostasy.apostle.core.index.ApostleEntityModelLayers;
import org.apostasy.apostle.core.index.ApostleEntityTypes;

/**
 * @author Chemthunder
 */
public class ApostleClient implements ClientModInitializer {
    public void onInitializeClient() {
        ApostleEntityTypes.clinit();
        ApostleEntityModelLayers.clinit();
    }
}
