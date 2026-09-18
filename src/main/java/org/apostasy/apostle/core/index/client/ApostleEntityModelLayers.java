package org.apostasy.apostle.core.index.client;

import net.acoyt.acornlib.api.builder.specified.ModelLayerLocationBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.CrowEntityModel;
import org.apostasy.apostle.core.client.entity.model.HolyNetEntityModel;
import org.apostasy.apostle.core.client.entity.model.RitualEntityModel;

/**
 * @author Chemthunder
 */
public interface ApostleEntityModelLayers {
    ModelLayerLocationBuilder plugin = new ModelLayerLocationBuilder(Apostle.MOD_ID);

    EntityModelLayer RITUAL = plugin.register("ritual", RitualEntityModel::getTexturedModelData);
    EntityModelLayer CROW = plugin.register("crow", CrowEntityModel::getTexturedModelData);
    EntityModelLayer HOLY_NET = plugin.register("holy_net", HolyNetEntityModel::getTexturedModelData);

    static void clinit() {
        plugin.build();
    }
}
