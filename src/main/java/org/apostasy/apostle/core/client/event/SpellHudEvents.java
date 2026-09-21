package org.apostasy.apostle.core.client.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Easing;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.joml.Matrix3x2fStack;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
@Environment(EnvType.CLIENT)
public class SpellHudEvents {
    public static @Nullable Spell lastSpell = null;
    public static float opacity = 0.0F;
    public static float expansion = 0.0F;

    public static void init() {
        HudElementRegistry.addFirst(Apostle.id("spell_hud_render"), new Hud());
        ClientTickEvents.START_CLIENT_TICK.register(new Tick());
    }

    public static class Tick implements ClientTickEvents.StartTick {
        public void onStartTick(MinecraftClient client) {
            PlayerEntity player = client.player;
            if (player == null) return;

            ItemStack main = player.getMainHandStack();
            ItemStack off = player.getOffHandStack();

            opacity = Math.clamp(opacity, 0.0F, 0.99F);

            if (expansion > 0.0F) {
                expansion -= 0.15F;
            }

            if (main.isOf(ApostleItems.ARCANE_STAFF) || main.isOf(ApostleItems.MAGIC_STAFF)) {
                Spell spell = SpellScrollItem.getSpellStack(off);

                if (spell != null) {
                    lastSpell = spell;
                    opacity = 1.0F;
                    return;
                }
            }

            if (opacity > 0.0F) {
                opacity -= 0.25F;
            }
        }
    }

    public static class Hud implements HudElement {
        public void render(DrawContext context, RenderTickCounter tickCounter) {
            MinecraftClient client = MinecraftClient.getInstance();

            PlayerEntity player = client.player;
            if (player == null) return;

            if (lastSpell != null && opacity > 0.001F) {
                Matrix3x2fStack matrices = context.getMatrices();

                context.drawTexture(
                        RenderPipelines.GUI_TEXTURED,
                        Apostle.id("textures/entity/ritual_" + lastSpell.getMagicSchool().name().getString().toLowerCase() + ".png"),
                        context.getScaledWindowWidth() / 2 - 24,
                        context.getScaledWindowHeight() - 70,
                        0, 0,
                        48,
                        48,
                        48,
                        48,
                        ColorHelper.withAlpha(opacity / 2F, lastSpell.getMagicSchool().color())
                );

                matrices.pushMatrix();

                matrices.translate(
                        context.getScaledWindowWidth() / 2F,
                        context.getScaledWindowHeight() - 50F
                );

                matrices.scale(
                        1.35F + Easing.outExpo(expansion)
                );

                context.drawCenteredTextWithShadow(
                        client.textRenderer,
                        Text.literal(lastSpell.getName()),
                        0, 0,
                        ColorHelper.withAlpha(opacity, lastSpell.getMagicSchool().color())
                );

                matrices.popMatrix();
            }
        }
    }
}
