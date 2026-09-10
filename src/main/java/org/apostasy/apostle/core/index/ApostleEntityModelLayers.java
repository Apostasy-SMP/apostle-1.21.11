package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.builder.specified.ModelLayerLocationBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.RitualEntityModel;

public interface ApostleEntityModelLayers {
    ModelLayerLocationBuilder plugin = new ModelLayerLocationBuilder(Apostle.MOD_ID);

    EntityModelLayer RITUAL = plugin.register("ritual", RitualEntityModel::getTexturedModelData);

    static void clinit() {
        plugin.build();
    }
}
