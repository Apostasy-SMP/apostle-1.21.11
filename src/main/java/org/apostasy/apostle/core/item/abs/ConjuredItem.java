package org.apostasy.apostle.core.item.abs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public abstract class ConjuredItem extends Item {
    private final int seconds;
    private final MagicSchool school;

    public ConjuredItem(Settings settings, int seconds, MagicSchool school) {
        super(settings
                .component(
                        ApostleComponentTypes.ITEM_DURATION,
                        seconds
                )
        );
        this.seconds = seconds;
        this.school = school;
    }

    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, world, entity, slot);
        int time = stack.getOrDefault(ApostleComponentTypes.ITEM_DURATION, 0);

        if (time > 0) {
            if (entity.age % 20 == 0) {
                stack.set(ApostleComponentTypes.ITEM_DURATION, time - 1);
            }
        } else {
            stack.split(1);
        }
    }

    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return oldStack.getItem() != newStack.getItem();
    }

    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    public int getItemBarStep(ItemStack stack) {
        return Math.clamp(Math.round((float) stack.getOrDefault(ApostleComponentTypes.ITEM_DURATION, 0) / this.seconds * 13), 0, 13);
    }

    public int getItemBarColor(ItemStack stack) {
        return this.school.color();
    }

    public ItemStack getDefaultStack() {
        ItemStack s = super.getDefaultStack();
        s.set(ApostleComponentTypes.ITEM_DURATION, this.seconds);
        return s;
    }
}
