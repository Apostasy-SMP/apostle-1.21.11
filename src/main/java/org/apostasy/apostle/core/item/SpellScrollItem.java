package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class SpellScrollItem extends Item {
    public SpellScrollItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return Text.literal("Scroll");
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(ApostleItems.SPELL_SCROLL)) {
                if (stack.contains(ApostleComponentTypes.STORED_SPELL)) {
                    if (stack.get(ApostleComponentTypes.STORED_SPELL) != null) {
                        var spellComp = stack.get(ApostleComponentTypes.STORED_SPELL);

                        if (spellComp != null) {
                            Spell spell = spellComp.spell();

                            lines.accept(Text.empty()
                                    .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                                    .append(spell.getMagicSchool().name().copy().withColor(spell.getMagicSchool().color()))
                            );

                            lines.accept(Text.empty()
                                    .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                                    .append(Text.literal(spell.getName()).withColor(spell.getMagicSchool().color()))
                            );
                        }
                    }
                }
            }
        }
    }
}
