package org.apostasy.apostle.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.apostasy.apostle.api.client.event.CreateOverlayCallback;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

/**
 * @author Chemthunder
 */
@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow @Nullable protected abstract PlayerEntity getCameraPlayer();

    @Shadow
    protected abstract void renderOverlay(DrawContext context, Identifier texture, float opacity);

    @WrapOperation(
            method = "renderHeldItemTooltip",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;getName()Lnet/minecraft/text/Text;"
            )
    )
    private Text apostle$swapDisplayedText(ItemStack stack, Operation<Text> original) {
        Spell spell = SpellScrollItem.getSpellStack(stack);

        if (spell != null) {
            return Text.empty()
                    .append(Text.literal(spell.getName()).withColor(spell.getMagicSchool().color()))
                    .append(Text.literal(" "))
                    .append(Text.literal("Scroll").withColor(spell.getMagicSchool().color()));
        }
        return original.call(stack);
    }

    @Inject(method = "renderMiscOverlays", at = @At(value = "TAIL"))
    private void apostle$createOverlayCallback(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        PlayerEntity player = this.getCameraPlayer();
        Optional<Pair<Identifier, Float>> overlay = CreateOverlayCallback.EVENT.invoker().getOverlay(player);
        overlay.ifPresent(pair -> this.renderOverlay(context, pair.getLeft(), pair.getRight()));
    }
}
