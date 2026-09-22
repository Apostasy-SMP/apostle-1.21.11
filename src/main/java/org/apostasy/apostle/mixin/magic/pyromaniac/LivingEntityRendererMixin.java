package org.apostasy.apostle.mixin.magic.pyromaniac;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.command.ModelCommandRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.state.CameraRenderState;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.cca.entity.tick.PyromaniacComponent;
import org.apostasy.apostle.core.client.index.ApostleRenderLayers;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> implements FeatureRendererContext<S, M> {

    @Shadow
    public abstract Identifier getTexture(S state);

    @Unique
    private static final RenderStateDataKey<Boolean> IS_PYROMANIAC = RenderStateDataKey.create();

    protected LivingEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Inject(method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    private void apostle$updatePyromaniacState(T entity, S state, float f, CallbackInfo ci) {
        state.setData(IS_PYROMANIAC, PyromaniacComponent.get(entity).isActive());
    }

    @WrapOperation(method = "render(Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;Lnet/minecraft/client/render/state/CameraRenderState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/command/OrderedRenderCommandQueue;submitModel(Lnet/minecraft/client/model/Model;Ljava/lang/Object;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/RenderLayer;IIILnet/minecraft/client/texture/Sprite;ILnet/minecraft/client/render/command/ModelCommandRenderer$CrumblingOverlayCommand;)V"))
    private void apostle$renderPyromaniacLayer(OrderedRenderCommandQueue instance, Model<?> model, Object o, MatrixStack stack, RenderLayer renderLayer, int light, int overlay, int tintedColor, Sprite sprite, int i, ModelCommandRenderer.CrumblingOverlayCommand crumblingOverlayCommand, Operation<Void> original, S state) {
        original.call(instance, model, o, stack, renderLayer, light, overlay, tintedColor, sprite, i, crumblingOverlayCommand);
        if (state.getDataOrDefault(IS_PYROMANIAC, false)) {
            stack.push();
            stack.scale(1.1F, 1.1F, 1.1F);
            original.call(instance, model, o, stack, ApostleRenderLayers.entityPyromaniac(this.getTexture(state)), light, overlay, tintedColor, sprite, i, crumblingOverlayCommand);
            stack.pop();
        }

    }
}
