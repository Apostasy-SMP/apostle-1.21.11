package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.item.KillEffectItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.core.index.ApostleItems;

/**
 * @author Chemthunder
 */
public class SacrificialKnifeItem extends Item implements KillEffectItem {
    public SacrificialKnifeItem(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributes() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                6.0,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                -2.5,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public void killEntity(World level, ItemStack stack, LivingEntity user, LivingEntity victim) {
        if (user.getOffHandStack().isOf(Items.GLASS_BOTTLE)) {
            ItemStack bloodStack = null;

            if (victim instanceof HostileEntity) {
                bloodStack = new ItemStack(ApostleItems.VILE_BLOOD);
            } else if (!(victim instanceof PlayerEntity)) {
                bloodStack = new ItemStack(ApostleItems.PURE_BLOOD);
            }

            if (bloodStack != null) {
                user.giveOrDropStack(bloodStack);
            }
        }
    }
}
