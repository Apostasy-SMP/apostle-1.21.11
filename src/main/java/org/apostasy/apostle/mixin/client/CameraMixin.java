package org.apostasy.apostle.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.apostasy.apostle.api.client.ScreenShaker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Shadow protected abstract void setRotation(float yaw, float pitch);

    @Shadow private float yaw;
    @Shadow private float pitch;

    @Inject(method = "update", at = @At("TAIL"))
    private void nitrogen$update(World area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickProgress, CallbackInfo ci){
        float y = this.yaw;
        float p = this.pitch;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        if (player != null){
            if (player instanceof ScreenShaker shaker){
                float mult = 1.0F;
                float swayFactor = 0.5F;
                float intensity = shaker.getScreenShakeIntensity();
                if (intensity > 0.01F){
                    float shakeSwayYaw = (float) ((Math.random() * 2 - 1) * mult * swayFactor * intensity);
                    float shakeSwayPitch = (float) ((Math.random() * 2 - 1) * mult * swayFactor * intensity);

                    this.setRotation(y + shakeSwayYaw, p + shakeSwayPitch);
                }
            }
        }
    }
}