package org.apostasy.apostle.core.cca.entity.tick;

import net.minecraft.entity.LivingEntity;
import org.apostasy.apostle.api.cca.TickDownComponent;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

public class PyromaniacComponent extends TickDownComponent<LivingEntity, PyromaniacComponent> {
    public static final ComponentKey<PyromaniacComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("pyromaniac"), PyromaniacComponent.class
    );

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

    }
}
