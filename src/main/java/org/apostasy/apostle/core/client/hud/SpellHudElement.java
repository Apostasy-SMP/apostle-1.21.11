package org.apostasy.apostle.core.client.hud;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.joml.Matrix3x2fStack;

/**
 * @author Chemthunder
 */
public class SpellHudElement implements HudElement {
    public void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        PlayerEntity player = client.player;
        if (player == null) return;

        ItemStack main = player.getMainHandStack();
        ItemStack off = player.getOffHandStack();

        if (main.isOf(ApostleItems.ARCANE_STAFF) || main.isOf(ApostleItems.MAGIC_STAFF)) {
            Spell spell = SpellScrollItem.getSpellStack(off);

            if (spell != null) {
                Matrix3x2fStack matrices = context.getMatrices();

                matrices.pushMatrix();

                matrices.rotateAbout(
                        (tickCounter.getDynamicDeltaTicks() + player.age) / 2,
                        context.getScaledWindowWidth() / 2F,
                        context.getScaledWindowHeight() / 2F
                );

                matrices.rotation(0);

                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        Text.literal(spell.getName()).withColor(spell.getMagicSchool().color()),
                        context.getScaledWindowWidth() / 2,
                        context.getScaledWindowHeight() / 2 - 40,
                        spell.getMagicSchool().color()
                );

                matrices.popMatrix();
            }
        }
    }
}
