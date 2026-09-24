package org.apostasy.apostle.core.cca.entity.tick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.cca.TickDownComponent;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class PyromaniacComponent extends TickDownComponent<LivingEntity, PyromaniacComponent> {
    public static final ComponentKey<PyromaniacComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("pyromaniac"), PyromaniacComponent.class
    );

    public static final Identifier SPEED_ATTRIBUTE_ID = Apostle.id("pyromaniac_speed_increase");

    public PyromaniacComponent(LivingEntity obj) {
        super(KEY, obj, 30 * 20, PyromaniacComponent::onFinished);
    }

    public static PyromaniacComponent get(LivingEntity obj) {
        return obj.getComponent(KEY);
    }

    @Override
    public void tick() {
        super.tick();
    }

    public static void onFinished(LivingEntity obj) {
        EntityAttributeInstance instance = obj.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED);

        if (instance != null) {
            if (instance.hasModifier(SPEED_ATTRIBUTE_ID)) {
                instance.removeModifier(SPEED_ATTRIBUTE_ID);
            }
        }
    }
}
