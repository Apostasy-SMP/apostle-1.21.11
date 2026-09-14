package org.apostasy.apostle.core.index.client;

import net.minecraft.client.data.Model;
import net.minecraft.client.data.TextureKey;
import org.apostasy.apostle.core.Apostle;

import java.util.Optional;

/**
 * @author Chemthunder
 */
public interface ApostleModels {
    Model STAFF_IN_HAND = create("staff_in_hand_template");
    Model STAFF_IN_HAND_USING = create("staff_in_hand_using_template");

    private static Model create(String parent) {
        return new Model(Optional.of(Apostle.id("item/" + parent)), Optional.empty(), TextureKey.LAYER0);
    }
}
