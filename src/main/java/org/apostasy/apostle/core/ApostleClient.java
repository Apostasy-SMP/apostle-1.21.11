package org.apostasy.apostle.core;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.render.item.property.select.SelectProperties;
import org.apostasy.apostle.api.client.event.CreateOverlayCallback;
import org.apostasy.apostle.api.client.event.UpdateRenderStateCallback;
import org.apostasy.apostle.core.client.event.BloodlustHudEvent;
import org.apostasy.apostle.core.client.event.ItemGroupCyclingEvents;
import org.apostasy.apostle.core.client.event.SpellHudEvents;
import org.apostasy.apostle.core.client.event.UndeadOutlineColorEvent;
import org.apostasy.apostle.core.index.client.ApostleRenderLayers;
import org.apostasy.apostle.core.index.client.ApostleRenderPipelines;
import org.apostasy.apostle.core.client.item.MagicSchoolProperty;
import org.apostasy.apostle.core.client.item.SpellScrollProperty;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleParticleTypes;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;
import org.apostasy.apostle.core.networking.ApostleNetworking;

/**
 * @author Chemthunder
 */
public class ApostleClient implements ClientModInitializer {
    public static final RenderStateDataKey<Boolean> IS_ARCHMAGE = RenderStateDataKey.create();

    public void onInitializeClient() {
        ApostleEntityTypes.clinit();
        ApostleEntityModelLayers.clinit();
        ApostleParticleTypes.clinit();
        ApostleRenderPipelines.clinit();
        ApostleRenderLayers.clinit();

        ApostleNetworking.s2c();

        SelectProperties.ID_MAPPER.put(SpellScrollProperty.ID, SpellScrollProperty.TYPE);
        SelectProperties.ID_MAPPER.put(MagicSchoolProperty.ID, MagicSchoolProperty.TYPE);

        SpellHudEvents.init();
        ItemGroupCyclingEvents.init();

        UpdateRenderStateCallback.EVENT.register(new UndeadOutlineColorEvent());

        HudElementRegistry.addFirst(Apostle.id("bloodlust_overlay"), new BloodlustHudEvent());
    }
}
