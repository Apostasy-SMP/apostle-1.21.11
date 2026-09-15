package org.apostasy.apostle.core.index.magic;

import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.Ritual;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.apostasy.apostle.core.magic.ritual.vex.CreateOminousBottleRitual;

/**
 * @author Chemthunder
 */
public interface Rituals {
    Ritual CREATE_OMINOUS_BOTTLE = register("create_ominous_bottle", new CreateOminousBottleRitual());

    private static Ritual register(String name, Ritual ritual) {
        return Registry.register(ApostleRegistries.RITUAL, Apostle.id(name), ritual);
    }

    static void init() {}
}
