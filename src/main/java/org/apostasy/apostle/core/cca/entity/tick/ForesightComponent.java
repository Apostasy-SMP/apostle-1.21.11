package org.apostasy.apostle.core.cca.entity.tick;

import net.minecraft.entity.LivingEntity;
import org.apostasy.apostle.api.cca.TickDownComponent;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class ForesightComponent extends TickDownComponent<LivingEntity, ForesightComponent> {
    public static final ComponentKey<ForesightComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("foresight"),
            ForesightComponent.class
    );

    public ForesightComponent(LivingEntity obj) {
        super(KEY, obj, (3 * 20), (living -> {}));
    }
}
