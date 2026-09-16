package org.apostasy.apostle.core.cca.entity.data;

import com.mojang.serialization.Codec;
import net.minecraft.entity.LightningEntity;
import org.apostasy.apostle.api.cca.DataStoringComponent;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;

/**
 * @author Chemthunder
 */
public class ThunderBoltComponent extends DataStoringComponent<Boolean, LightningEntity> {
    public static final ComponentKey<ThunderBoltComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("thunderbolt"),
            ThunderBoltComponent.class
    );

    public ThunderBoltComponent(LightningEntity obj) {
        super(Codec.BOOL, obj);
    }

    public void sync() {
        KEY.sync(this.obj);
    }
}
