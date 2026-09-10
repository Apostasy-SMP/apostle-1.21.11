package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.HeldItemContext;
import net.minecraft.util.math.RotationAxis;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.RitualEntityModel;
import org.apostasy.apostle.core.client.entity.state.RitualEntityRenderState;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.index.ApostleEntityModelLayers;

import java.util.ArrayList;

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

        float delta = MinecraftClient.getInstance().getRenderTickCounter().getDynamicDeltaTicks();

        matrices.push();

        if (renderState.heldTome != null) {
            matrices.translate(0, 0.75F, 0);
            matrices.scale(4, 4, 4);

            matrices.multiply(RotationAxis.POSITIVE_Y.rotation((delta + renderState.age) / 32));

            queue.submitModel(
                    this.ritualPlane,
                    renderState,
                    matrices,
                    RenderLayers.entityCutout(Apostle.id("textures/entity/ritual_" + renderState.heldTome.getId() + ".png")),
                    LightmapTextureManager.MAX_LIGHT_COORDINATE,
                    OverlayTexture.DEFAULT_UV,
                    0x00,
                    null
            );
        }

        matrices.pop();

        MinecraftClient client = MinecraftClient.getInstance();

        matrices.push();

        matrices.translate(0, 3, 0);

        ItemRenderState itemState = new ItemRenderState();
        client.getItemModelManager().clearAndUpdate(
                itemState,
                new ItemStack(Items.TOTEM_OF_UNDYING),
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
    }
}
