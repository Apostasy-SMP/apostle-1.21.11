package org.apostasy.apostle.core.item.abs;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public abstract class ArtifactItem extends Item {
    public ArtifactItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        user.getItemCooldownManager().set(stack, this.getCooldownTime());
        world.playSound(null, user.getX(), user.getY(), user.getZ(), this.getConsumeEvent(), SoundCategory.PLAYERS);
        stack.split(1);
        return super.use(world, user, hand);
    }

    @Nullable
    protected LivingEntity selfOrAlly(LivingEntity user) {
        LivingEntity target = null;
        if (MinecraftClient.getInstance().targetedEntity != null) {
            if (MinecraftClient.getInstance().targetedEntity instanceof LivingEntity living) target = living;
        } else {
            target = user;
        }
        return target;
    }

    public List<Text> getDescription(ItemStack stack) {
        return new ArrayList<>();
    }

    public abstract List<Item> getIngredients();

    public abstract MagicSchool getSchool();

    public abstract int getCooldownTime();

    public SoundEvent getConsumeEvent() {
        return SoundEvents.ENTITY_GENERIC_EAT.value();
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.getItem() instanceof ArtifactItem artifact) {
                List<Text> texts = artifact.getDescription(stack);
                if (!texts.isEmpty()) {
                    texts.forEach(lines);
                }
            }
        }
    }
}
