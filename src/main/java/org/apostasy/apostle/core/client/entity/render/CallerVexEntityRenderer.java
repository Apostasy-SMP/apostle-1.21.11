package org.apostasy.apostle.core.client.entity.render;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.VexEntityModel;
import net.minecraft.client.render.entity.state.ArmedEntityRenderState;
import net.minecraft.client.render.entity.state.VexEntityRenderState;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.entity.spell.CallerVexEntity;

/**
 * @author Chemthunder
 */
public class CallerVexEntityRenderer extends MobEntityRenderer<CallerVexEntity, VexEntityRenderState, VexEntityModel> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/entity/illager/vex.png");
    private static final Identifier CHARGING_TEXTURE = Identifier.ofVanilla("textures/entity/illager/vex_charging.png");

    public CallerVexEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new VexEntityModel(context.getPart(EntityModelLayers.VEX)), 0.3F);
        this.addFeature(new HeldItemFeatureRenderer(this));
    }

    public VexEntityRenderState createRenderState() {
        return new VexEntityRenderState();
    }

    public Identifier getTexture(VexEntityRenderState vexEntityRenderState) {
        return vexEntityRenderState.charging ? CHARGING_TEXTURE : TEXTURE;
    }

    public void updateRenderState(CallerVexEntity livingEntity, VexEntityRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        ArmedEntityRenderState.updateRenderState(livingEntity, livingEntityRenderState, this.itemModelResolver, f);
        livingEntityRenderState.charging = livingEntity.isCharging();
    }
}
