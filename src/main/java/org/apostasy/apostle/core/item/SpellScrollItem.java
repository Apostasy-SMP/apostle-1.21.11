package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.jspecify.annotations.Nullable;

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

    public int getItemBarColor(ItemStack stack) {
        if (getSpellStack(stack) != null) {
            Spell spell = getSpellStack(stack);

            if (spell != null) {
                return spell.getMagicSchool().color();
            }
        }
        return 0;
    }

    public int getItemBarStep(ItemStack stack) {
        if (getSpellStack(stack) != null) {
            Spell spell = getSpellStack(stack);

            if (spell != null) {
                return Math.clamp(Math.round((float) stack.getOrDefault(ApostleComponentTypes.SCROLL_COOLDOWN, 0) / (spell.getCooldown()) * 13), 0, 13);
            }
        }
        return 0;
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);

        int cooldown = stack.getOrDefault(ApostleComponentTypes.SCROLL_COOLDOWN, 0);

        if (getSpellStack(stack) != null) {
            Spell spell = getSpellStack(stack);

            if (spell != null) {
                if (cooldown > 0) {
                    if (entity.age % 20 == 0) {
                        stack.set(ApostleComponentTypes.SCROLL_COOLDOWN, cooldown - 20);
                    }
                }
            }
        }
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return stack.getOrDefault(ApostleComponentTypes.SCROLL_COOLDOWN, 0) > 0;
    }

    @Nullable
    public static Spell getSpellStack(ItemStack off) {
        if (off.isOf(ApostleItems.SPELL_SCROLL)) {
            if (off.contains(ApostleComponentTypes.STORED_SPELL)) {
                StoredSpellComponent spellComponent = off.get(ApostleComponentTypes.STORED_SPELL);

                if (spellComponent != null) {
                    return spellComponent.spell();
                }
            }
        }
        return null;
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getItem() != newStack.getItem();
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
