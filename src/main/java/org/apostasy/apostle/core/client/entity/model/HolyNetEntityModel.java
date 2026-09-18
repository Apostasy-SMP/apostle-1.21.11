package org.apostasy.apostle.core.client.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.entity.model.EntityModel;
import org.apostasy.apostle.core.client.entity.state.HolyNetEntityRenderState;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class HolyNetEntityModel extends EntityModel<HolyNetEntityRenderState> {
	private final ModelPart side2;
	private final ModelPart side3;
	private final ModelPart vert;
	private final ModelPart vert2;
	
	public HolyNetEntityModel(ModelPart root) {
        super(root, RenderLayers::entityCutout);
        this.side2 = root.getChild("side2");
		this.side3 = root.getChild("side3");
		this.vert = root.getChild("vert");
		this.vert2 = root.getChild("vert2");
	}
	
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData side2 = modelPartData.addChild("side2", ModelPartBuilder.create().uv(0, 0).cuboid(-24.0F, -42.0F, 23.0F, 48.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 4).cuboid(-24.0F, -42.0F, -24.0F, 48.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 6).cuboid(-24.0F, -1.0F, -24.0F, 48.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 2).cuboid(-24.0F, -1.0F, 23.0F, 48.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData side3 = modelPartData.addChild("side3", ModelPartBuilder.create().uv(0, 8).cuboid(-23.0F, -42.0F, 23.0F, 46.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 10).cuboid(-23.0F, -42.0F, -24.0F, 46.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 12).cuboid(-23.0F, -1.0F, -24.0F, 46.0F, 1.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 14).cuboid(-23.0F, -1.0F, 23.0F, 46.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData vert = modelPartData.addChild("vert", ModelPartBuilder.create().uv(0, 16).cuboid(23.0F, -41.0F, 23.0F, 1.0F, 40.0F, 1.0F, new Dilation(0.0F))
		.uv(4, 16).cuboid(23.0F, -41.0F, -24.0F, 1.0F, 40.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 24.0F, 0.0F));

		ModelPartData vert2 = modelPartData.addChild("vert2", ModelPartBuilder.create().uv(8, 16).cuboid(23.0F, -41.0F, 23.0F, 1.0F, 40.0F, 1.0F, new Dilation(0.0F))
		.uv(12, 16).cuboid(23.0F, -41.0F, -24.0F, 1.0F, 40.0F, 1.0F, new Dilation(0.0F)), ModelTransform.origin(-47.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 128, 128);
	}
}