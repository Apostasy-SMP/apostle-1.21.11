package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.RitualEntityModel;
import org.apostasy.apostle.core.client.entity.state.RitualEntityRenderState;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;

/**
 * @author Chemthunder
 */
public class RitualEntityRenderer extends EntityRenderer<RitualEntity, RitualEntityRenderState> {
    private final RitualEntityModel ritualPlane;

    public RitualEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.ritualPlane = new RitualEntityModel(context.getPart(ApostleEntityModelLayers.RITUAL));
    }

    public void render(RitualEntityRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        super.render(renderState, matrices, queue, cameraState);

        MinecraftClient client = MinecraftClient.getInstance();
        float delta = client.getRenderTickCounter().getDynamicDeltaTicks();

        matrices.push();

        if (renderState.heldTome != null) {
            matrices.translate(0, 0.75F, 0);
            matrices.scale(4, 4, 4);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotation((delta + renderState.age) / 32));

            queue.submitModel(
                    this.ritualPlane,
                    renderState,
                    matrices,
                    RenderLayers.entityCutout(Apostle.id("textures/entity/ritual_" + renderState.school.name().getString().toLowerCase() + ".png")),
                    LightmapTextureManager.MAX_LIGHT_COORDINATE,
                    OverlayTexture.DEFAULT_UV,
                    renderState.heldTome.getSchool().color(),
                    null,
                    0x00,
                    null
            );
        }

        matrices.pop();

        for (ItemStack stack : renderState.stacksToRender) {
            int index = renderState.stacksToRender.indexOf(stack);
            int distance = 1;

            matrices.push();

            if (index == 0) {
                matrices.translate(0, 2, 0);

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((delta + renderState.age)));
            }
            if (index > 0) {
                matrices.translate(0, 2, 0);

                matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((delta + renderState.age) * (2 + ((index - 1) * 2))),
                        0,
                        0,
                        0
                );

                matrices.translate(distance, 0, distance);

                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((delta + renderState.age)));
                matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((delta + renderState.age)));

                matrices.translate(0, 2, 0);
            }

            ItemRenderState itemState = new ItemRenderState();
            client.getItemModelManager().clearAndUpdate(
                    itemState,
                    stack,
                    ItemDisplayContext.GUI,
                    client.world,
                    null,
                    -1
            );

            itemState.render(
                    matrices,
                    queue,
                    LightmapTextureManager.MAX_LIGHT_COORDINATE,
                    OverlayTexture.DEFAULT_UV,
                    0
            );
            matrices.pop();
        }
    }

    public boolean shouldRender(RitualEntity entity, Frustum frustum, double x, double y, double z) {
        return true;
    }

    protected float getShadowOpacity(RitualEntityRenderState state) {
        return 0.0F;
    }

    protected float getShadowRadius(RitualEntityRenderState state) {
        return 0.0F;
    }

    public RitualEntityRenderState createRenderState() {
        return new RitualEntityRenderState();
    }

    public void updateRenderState(RitualEntity entity, RitualEntityRenderState state, float tickProgress) {
        super.updateRenderState(entity, state, tickProgress);

        state.entity = entity;

        state.heldTome = entity.getHeldTome();
        state.stacksToRender = entity.getHeldStacks();

        if (entity.getHeldTome() != null) {
            state.school = entity.getHeldTome().getSchool();
        }
    }
}
