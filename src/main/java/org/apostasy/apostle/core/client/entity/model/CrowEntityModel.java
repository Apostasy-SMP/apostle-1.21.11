package org.apostasy.apostle.core.client.entity.model;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.util.math.MathHelper;
import org.apostasy.apostle.core.client.entity.state.CrowEntityRenderState;

public class CrowEntityModel extends EntityModel<CrowEntityRenderState> {
    private final ModelPart main, head;

    private final ModelPart leftWing, rightWing;
    private final ModelPart leftFoot, rightFoot;

    private final ModelPart tailFeather;

    public CrowEntityModel(ModelPart root) {
        super(root);
        this.main = root.getChild("main");
        this.head = this.main.getChild("head");

        this.leftWing = this.main.getChild("leftWing");
        this.rightWing = this.main.getChild("rightWing");

        this.leftFoot = this.main.getChild("leftFoot");
        this.rightFoot = this.main.getChild("rightFoot");

        this.tailFeather = this.main.getChild("tailFeather");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData main = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 20.5F, -0.25F));
        main.addChild("body", ModelPartBuilder.create()
                        .uv(0, 0).cuboid(-1.0F, -2.5F, -4.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.of(-1.0F, 0.0F, 0.0F, -0.7854F, 0.0F, 0.0F));

        main.addChild("head", ModelPartBuilder.create()
                        .uv(0, 23).cuboid(-1.5F, -2.0F, -3.5F, 3.0F, 3.0F, 5.0F, new Dilation(0.0F))
                        .uv(13, 21).cuboid(0.0F, -0.5F, -5.0F, 0.0F, 1.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.origin(0.0F, -3.5F, -2.5F));


        main.addChild("leftWing", ModelPartBuilder.create()
                        .uv(0, 10).cuboid(0.1F, 0.0F, -2.0F, 0.0F, 4.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.origin(2.0F, -2.5F, -1.0F));
        main.addChild("rightWing", ModelPartBuilder.create()
                        .uv(0, 10).cuboid(-0.1F, 0.0F, -2.0F, 0.0F, 4.0F, 8.0F, new Dilation(0.0F)),
                ModelTransform.origin(-2.0F, -2.5F, -1.0F));

        main.addChild("leftFoot", ModelPartBuilder.create()
                        .uv(16, 22).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                        .uv(20, 8).cuboid(-0.5F, 1.0F, -1.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.origin(1.0F, 2.5F, 0.25F));

        main.addChild("rightFoot", ModelPartBuilder.create()
                        .uv(16, 22).cuboid(-0.5F, -1.0F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                        .uv(20, 8).cuboid(-0.5F, 1.0F, -1.0F, 1.0F, 0.0F, 2.0F, new Dilation(0.0F)),
                ModelTransform.origin(-1.0F, 2.5F, 0.25F));

        main.addChild("tailFeather", ModelPartBuilder.create()
                        .uv(20, 0).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 0.0F, 6.0F, new Dilation(0.0F)),
                ModelTransform.origin(0.0F, -0.25F, 3.0F));

       return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(CrowEntityRenderState state) {
        super.setAngles(state);
        this.head.yaw = MathHelper.RADIANS_PER_DEGREE * state.relativeHeadYaw;
        this.head.pitch = MathHelper.RADIANS_PER_DEGREE * state.pitch;
    }
}
