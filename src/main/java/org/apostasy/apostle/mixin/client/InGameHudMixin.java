package org.apostasy.apostle.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.item.SpellScrollItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * @author Chemthunder
 */
@Mixin(value = InGameHud.class)
public abstract class InGameHudMixin {

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
}
