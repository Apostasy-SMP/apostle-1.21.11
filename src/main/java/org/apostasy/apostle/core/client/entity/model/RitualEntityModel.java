package org.apostasy.apostle.core.client.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.model.EntityModel;
import org.apostasy.apostle.core.client.entity.state.RitualEntityRenderState;

/**
 * @author AcoYT
 */
public class RitualEntityModel extends EntityModel<RitualEntityRenderState> {
    private final ModelPart bone;

    public RitualEntityModel(ModelPart root) {
        super(root, RenderLayers::entityCutout);
        this.bone = root.getChild("bone");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData mesh = new ModelData();
        ModelPartData part = mesh.getRoot();
        ModelPartData bone = part.addChild(
                "bone",
                ModelPartBuilder.create()
                        .uv(-16, 0)
                        .cuboid(-8.0F, 0.0F, -8.0F, 16.0F, 0.0F, 16.0F, new Dilation(0.0F)),
                ModelTransform.origin(0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(mesh, 16, 16);
    }
}
