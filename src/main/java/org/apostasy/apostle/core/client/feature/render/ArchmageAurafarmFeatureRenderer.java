package org.apostasy.apostle.core.client.feature.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.render.item.ItemRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.RotationAxis;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.ApostleClient;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.ApostleRegistries;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class ArchmageAurafarmFeatureRenderer<S extends BipedEntityRenderState, M extends BipedEntityModel<S>> extends FeatureRenderer<S, M> {
    public ArchmageAurafarmFeatureRenderer(FeatureRendererContext<S, M> context) {
        super(context);
    }

    public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, int light, S state, float limbAngle, float limbDistance) {
        MinecraftClient client = MinecraftClient.getInstance();
        float delta = client.getRenderTickCounter().getDynamicDeltaTicks();

        if (Boolean.TRUE.equals(state.getData(ApostleClient.IS_ARCHMAGE))) {
            List<ItemStack> staves = new ArrayList<>();

            staves.add(new ItemStack(Items.AIR));

            for (MagicSchool school : ApostleRegistries.MAGIC_SCHOOL) {
                ItemStack staff = new ItemStack(ApostleItems.ARCANE_STAFF);
                staff.set(ApostleComponentTypes.SCHOOL, school);
                staves.add(staff);
            }

            staves.add(new ItemStack(ApostleItems.MAGIC_STAFF));

            for (ItemStack stack : staves) {
                int index = staves.indexOf(stack);
                int distance = 1;

                matrices.push();

                matrices.translate(0, -3, 0);

                if (index == 0) {
                    matrices.translate(0, 2, 0);

                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((delta + state.age)));
                }
                if (index > 0) {
                    matrices.translate(0, 2, 0);

                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((delta + state.age) * (2 + ((index - 1) * 2))),
                            0,
                            0,
                            0
                    );

                    matrices.translate(distance, 0, distance);

//                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees((delta + state.age)));
                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees((delta + state.age)));

                    matrices.translate(0, 2, 0);
                }

                matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(25));

                ItemRenderState itemState = new ItemRenderState();
                client.getItemModelManager().clearAndUpdate(
                        itemState,
                        stack,
                        ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
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
    }
}
