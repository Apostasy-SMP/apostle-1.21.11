package org.apostasy.apostle.mixin.client;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import org.apostasy.apostle.api.client.event.UpdateRenderStateCallback;
import org.apostasy.apostle.core.ApostleClient;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Chemthunder
 */
@Mixin(value = LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends EntityRenderer<T, S> implements FeatureRendererContext<S, M> {
    protected LivingEntityRendererMixin(EntityRendererFactory.Context context) {
        super(context);
    }

    @Inject(
            method = "updateRenderState(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/client/render/entity/state/LivingEntityRenderState;F)V",
            at = @At(
                    value = "TAIL"
            )
    )
    private void apostle$updateDataKeys(T livingEntity, S livingEntityRenderState, float f, CallbackInfo ci) {
        UpdateRenderStateCallback.EVENT.invoker().updateRenderState(livingEntity, livingEntityRenderState);

        if (Boolean.TRUE.equals(livingEntity.getAttached(ApostleAttachmentTypes.IS_ARCHMAGE))) {
            livingEntityRenderState.setData(ApostleClient.IS_ARCHMAGE, true);
        }
    }
}
