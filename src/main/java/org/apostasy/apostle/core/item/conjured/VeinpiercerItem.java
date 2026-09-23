package org.apostasy.apostle.core.item.conjured;

import net.acoyt.acornlib.api.item.ShieldBreaker;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.index.magic.Spells;
import org.apostasy.apostle.core.item.abs.ConjuredItem;

/**
 * @author Chemthunder
 */
public class VeinpiercerItem extends ConjuredItem implements ShieldBreaker {
    public VeinpiercerItem(Settings settings) {
        super(settings, Spells.VEINPIERCER.getCooldown() / 20, Schools.GORE);
    }

    public static AttributeModifiersComponent createAttributes() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                9.0F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID,
                                -3.0F,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public float getShieldCooldown(ItemStack stack) {
        return (10 * 20);
    }
}
