package org.apostasy.apostle.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.SkinTextures;
import org.apostasy.apostle.core.cca.entity.TransComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @ModifyReturnValue(method = "getSkin", at = @At(value = "RETURN"))
    private SkinTextures apostle$swapSkinForTrans(SkinTextures original) {
        TransComponent trans = TransComponent.KEY.get(this);
        if (trans.getDuration() > 0) {
            if (trans.getProfile() != null) {
                return DefaultSkinHelper.getSkinTextures(trans.getProfile());
            }
        }
        return original;
    }
}
