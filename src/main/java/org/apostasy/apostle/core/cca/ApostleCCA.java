package org.apostasy.apostle.core.cca;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.apostasy.apostle.core.cca.entity.TransComponent;
import org.apostasy.apostle.core.cca.entity.data.ThunderBoltComponent;
import org.apostasy.apostle.core.cca.entity.data.WaypointComponent;
import org.apostasy.apostle.core.cca.entity.tick.RocketComponent;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentFactory;
import org.ladysnake.cca.api.v3.component.ComponentKey;
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
        module.registerForPlayers(TransComponent.KEY, TransComponent::new, RespawnCopyStrategy.NEVER_COPY);

        module.beginRegistration(
                LivingEntity.class,
                RocketComponent.KEY
        ).respawnStrategy(RespawnCopyStrategy.CHARACTER).end(RocketComponent::new);
    }

    public static void registerData(EntityComponentFactoryRegistry module) {
        ApostleCCA.registerPersistentData(
                module,
                LightningEntity.class,
                ThunderBoltComponent.KEY,
                ThunderBoltComponent::new
        );

        ApostleCCA.registerPersistentData(
                module,
                LivingEntity.class,
                WaypointComponent.KEY,
                WaypointComponent::new
        );
    }

    public static <C extends Component, E extends Entity> void registerPersistentData(EntityComponentFactoryRegistry module, Class<E> targetClass, ComponentKey<C> key, ComponentFactory<E, C> factory) {
        module.beginRegistration(
                targetClass,
                key
        ).respawnStrategy(RespawnCopyStrategy.ALWAYS_COPY).end(factory);
    }
}
