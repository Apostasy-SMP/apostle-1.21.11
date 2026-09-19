package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.core.cca.entity.TransComponent;
import org.apostasy.apostle.core.index.ApostleItems;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class TransIdolItem extends Item {
    public TransIdolItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (stack.contains(DataComponentTypes.PROFILE)) {
            ProfileComponent profileComponent = stack.get(DataComponentTypes.PROFILE);

            if (profileComponent != null) {
                TransComponent trans = TransComponent.KEY.get(user);

                trans.setDuration((15 * 20));
                trans.setProfile(profileComponent.getGameProfile());
            }
        }
        return super.use(world, user, hand);
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(ApostleItems.TRANS_IDOL)) {
                if (stack.contains(DataComponentTypes.PROFILE)) {
                    ProfileComponent profileComponent = stack.get(DataComponentTypes.PROFILE);

                    if (profileComponent != null) {
                        lines.accept(Text.empty()
                                .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                                .append(Text.literal(profileComponent.getGameProfile().name()).formatted(Formatting.RED))
                        );
                    }
                }
            }
        }
    }
}
