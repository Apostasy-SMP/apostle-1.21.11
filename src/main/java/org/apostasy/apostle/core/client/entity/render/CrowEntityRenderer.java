package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.model.CrowEntityModel;
import org.apostasy.apostle.core.client.entity.state.CrowEntityRenderState;
import org.apostasy.apostle.core.entity.CrowEntity;
import org.apostasy.apostle.core.index.client.ApostleEntityModelLayers;

public class CrowEntityRenderer extends MobEntityRenderer<CrowEntity, CrowEntityRenderState, CrowEntityModel> {
    public static final Identifier TEXTURE = Apostle.id("textures/entity/crow.png");

    public CrowEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CrowEntityModel(ctx.getPart(ApostleEntityModelLayers.CROW)), 0.4F);
    }

    @Override
    public CrowEntityRenderState createRenderState() {
        return new CrowEntityRenderState();
    }

    @Override
    public void updateRenderState(CrowEntity crow, CrowEntityRenderState state, float partialTicks) {
        super.updateRenderState(crow, state, partialTicks);
    }

    @Override
    public void render(CrowEntityRenderState state, MatrixStack matrices, OrderedRenderCommandQueue queue, CameraRenderState cameraState) {
        super.render(state, matrices, queue, cameraState);
    }

    @Override
    public Identifier getTexture(CrowEntityRenderState state) {
        return TEXTURE;
    }
}
