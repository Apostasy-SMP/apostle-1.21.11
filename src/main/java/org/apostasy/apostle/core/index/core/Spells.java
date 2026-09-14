package org.apostasy.apostle.core.index.core;

import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.apostasy.apostle.core.spell.wick.CataclysmSpell;
import org.apostasy.apostle.core.spell.wind.DashSpell;

/**
 * @author Chemthunder
 */
public interface Spells {
    /// WIND
    Spell DASH = register("dash", new DashSpell());

    /// WICK
    Spell FIREBALL = register("fireball", new CataclysmSpell());

    private static Spell register(String name, Spell spell) {
        return Registry.register(ApostleRegistries.SPELL, Apostle.id(name), spell);
    }

    static void init() {}
}
