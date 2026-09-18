package org.apostasy.apostle.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.render.item.property.select.SelectProperties;
import org.apostasy.apostle.core.client.event.SpellHudEvents;
import org.apostasy.apostle.core.client.item.MagicSchoolProperty;
import org.apostasy.apostle.core.client.item.SpellScrollProperty;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleParticleTypes;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;

/**
 * @author Chemthunder
 */
public class ApostleClient implements ClientModInitializer {
    public static final RenderStateDataKey<Boolean> IS_ARCHMAGE = RenderStateDataKey.create();

    public void onInitializeClient() {
        ApostleEntityTypes.clinit();
        ApostleEntityModelLayers.clinit();
        ApostleParticleTypes.clinit();

        SelectProperties.ID_MAPPER.put(SpellScrollProperty.ID, SpellScrollProperty.TYPE);
        SelectProperties.ID_MAPPER.put(MagicSchoolProperty.ID, MagicSchoolProperty.TYPE);

        SpellHudEvents.init();
    }
}
