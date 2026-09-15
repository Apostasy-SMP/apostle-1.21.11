package org.apostasy.apostle.core.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import org.apostasy.apostle.core.index.ApostleItems;

/**
 * @author Chemthunder
 */
public class SpellHudElement implements HudElement {
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        PlayerEntity player = client.player;
        if (player == null) return;

        if (player.getMainHandStack().isOf(ApostleItems.ARCANE_STAFF) || player.getMainHandStack().isOf(ApostleItems.MAGIC_STAFF)) {
            /////////
        }
    }
}
