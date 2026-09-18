package org.apostasy.apostle.core.cca.entity.data;

import net.minecraft.entity.LivingEntity;
import org.apostasy.apostle.api.cca.DataStoringComponent;
import org.apostasy.apostle.api.magic.data.Waypoint;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

/**
 * @author Chemthunder
 */
public class WaypointComponent extends DataStoringComponent<Waypoint, LivingEntity, WaypointComponent> {
    public static final ComponentKey<WaypointComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("waypoint"),
            WaypointComponent.class
    );

    public WaypointComponent(LivingEntity player) {
        super(Waypoint.CODEC, KEY, player);
    }
}
