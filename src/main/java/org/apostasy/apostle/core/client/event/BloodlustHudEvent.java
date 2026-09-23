package org.apostasy.apostle.core.client.event;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ColorHelper;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;

/**
 * @author Chemthunder
 */
public class BloodlustHudEvent implements HudElement {
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        PlayerEntity player = client.player;
        if (player == null) return;

        BloodlustComponent lust = BloodlustComponent.KEY.get(player);

        if (lust.getDuration() > 0) {
            context.drawGuiTexture(
                    RenderPipelines.GUI_TEXTURED,
                    Apostle.id("overlays/bloodlust"),
                    0,
                    0,
                    context.getScaledWindowWidth(),
                    context.getScaledWindowHeight(),
                    ColorHelper.withAlpha(lust.getModifier() * 40, 0xFFFFFF)
            );
        }
    }
}
