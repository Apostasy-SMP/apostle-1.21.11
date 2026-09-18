package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.HolyNetEntityModel;
import org.apostasy.apostle.core.client.entity.state.HolyNetEntityRenderState;
import org.apostasy.apostle.core.entity.HolyNetEntity;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;
import org.apostasy.apostle.core.index.magic.Schools;

/**
 * @author Chemthunder
 */
public class HolyNetEntityRenderer extends EntityRenderer<HolyNetEntity, HolyNetEntityRenderState> {
    private final HolyNetEntityModel model;

    public HolyNetEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new HolyNetEntityModel(context.getEntityModels().getModelPart(ApostleEntityModelLayers.HOLY_NET));
    }

    public HolyNetEntityRenderState createRenderState() {
        return new HolyNetEntityRenderState();
    }

    public void updateRenderState(HolyNetEntity entity, HolyNetEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);

        state.size = entity.getSize();
    }

    public void render(HolyNetEntityRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        super.render(renderState, matrices, queue, cameraState);

        matrices.push();

        matrices.scale(renderState.size, renderState.size, renderState.size);

        queue.submitModel(
                this.model,
                renderState,
                matrices,
                RenderLayers.entityTranslucent(Apostle.id("textures/entity/holy_net.png")),
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                OverlayTexture.DEFAULT_UV,
                Schools.WORSHIP.color(),
                null,
                0x00,
                null
        );


        queue.submitModel(
                this.model,
                renderState,
                matrices,
                RenderLayers.eyes(Apostle.id("textures/entity/holy_net.png")),
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                OverlayTexture.DEFAULT_UV,
                Schools.WORSHIP.color(),
                null,
                0x00,
                null
        );

        matrices.pop();
    }

    public boolean shouldRender(HolyNetEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
