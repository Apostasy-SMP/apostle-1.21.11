package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class BloodItem extends Item {
    private final StatusEffectInstance[] instances;

    public BloodItem(Settings settings, StatusEffectInstance... instances) {
        super(settings);
        this.instances = instances;
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        for (StatusEffectInstance instance : instances) {
            user.addStatusEffect(instance);
        }
        stack.split(1);
        user.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
        return super.use(world, user, hand);
    }

    public Text getName(ItemStack stack) {
        return Text.literal("Bottle of Blood");
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            Item item = stack.getItem();

            if (item instanceof BloodItem) {
                lines.accept(Text.empty()
                        .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                        .append(Text.literal(getNameString(item)).formatted(Formatting.RED))
                );
            }
        }

        private String getNameString(Item item) {
            Identifier id = Registries.ITEM.getId(item);
            String itemName = id.getPath();

            char[] chars = itemName.toCharArray();
            int ending = 0;

            for (int i = 0; i < chars.length; i++) {
                char character = chars[i];

                if (character == '_') {
                    ending = i;
                    break;
                }
            }
            return MiscUtils.formatString(itemName.substring(0, ending));
        }
    }
}
