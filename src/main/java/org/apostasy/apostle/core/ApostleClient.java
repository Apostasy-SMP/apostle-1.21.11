package org.apostasy.apostle.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.render.item.property.select.SelectProperties;
import org.apostasy.apostle.core.client.hud.SpellHudElement;
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

        HudElementRegistry.addFirst(Apostle.id("staff"), new SpellHudElement());
    }
}
