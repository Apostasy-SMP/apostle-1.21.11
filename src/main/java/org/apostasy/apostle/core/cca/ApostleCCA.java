package org.apostasy.apostle.core.cca;

import net.minecraft.entity.LightningEntity;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.apostasy.apostle.core.cca.entity.data.ThunderBoltComponent;
import org.apostasy.apostle.core.cca.entity.data.WaypointComponent;
import org.ladysnake.cca.api.v3.entity.EntityComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.entity.EntityComponentInitializer;
import org.ladysnake.cca.api.v3.entity.RespawnCopyStrategy;

/**
 * @author Chemthunder
 */
public class ApostleCCA implements EntityComponentInitializer {
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry module) {
        ApostleCCA.registerData(module);

        module.registerForPlayers(BloodlustComponent.KEY, BloodlustComponent::new, RespawnCopyStrategy.NEVER_COPY);
    }

    public static void registerData(EntityComponentFactoryRegistry module) {
        module.registerForPlayers(
                WaypointComponent.KEY,
                WaypointComponent::new,
                RespawnCopyStrategy.NEVER_COPY
        );

        module.registerFor(
                LightningEntity.class,
                ThunderBoltComponent.KEY,
                ThunderBoltComponent::new
        );
    }
}
