package org.apostasy.apostle.core;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.item.property.select.SelectProperties;
import org.apostasy.apostle.core.client.item.MagicSchoolProperty;
import org.apostasy.apostle.core.client.item.SpellScrollProperty;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;

/**
 * @author Chemthunder
 */
public class ApostleClient implements ClientModInitializer {
    public void onInitializeClient() {
        ApostleEntityTypes.clinit();
        ApostleEntityModelLayers.clinit();

        SelectProperties.ID_MAPPER.put(SpellScrollProperty.ID, SpellScrollProperty.TYPE);
        SelectProperties.ID_MAPPER.put(MagicSchoolProperty.ID, MagicSchoolProperty.TYPE);
    }
}
