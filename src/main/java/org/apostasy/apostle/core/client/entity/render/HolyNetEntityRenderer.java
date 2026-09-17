package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper;
import org.apostasy.apostle.api.client.Nitro;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.state.HolyNetEntityRenderState;
import org.apostasy.apostle.core.entity.HolyNetEntity;
import org.apostasy.apostle.core.index.magic.Schools;

/**
 * @author Chemthunder
 */
public class HolyNetEntityRenderer extends EntityRenderer<HolyNetEntity, HolyNetEntityRenderState> {
    public HolyNetEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
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

        queue.submitCustom(
                matrices,
                RenderLayers.entityCutout(Apostle.id("textures/entity/ritual_worship")),
                ((matricesEntry, vertexConsumer) -> createBox(matrices, vertexConsumer, renderState))
        );
    }

    private void createBox(MatrixStack matrices, VertexConsumer consumer, HolyNetEntityRenderState state) {
        matrices.push();

        Nitro.solidColCubeAtPos(
                matrices,
                consumer,
                ColorHelper.withAlpha(75, Schools.WORSHIP.color()),
                (float) state.x,
                (float) state.y,
                (float) state.z,
                state.size
        );

        matrices.pop();
    }

    public boolean shouldRender(HolyNetEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }
}
