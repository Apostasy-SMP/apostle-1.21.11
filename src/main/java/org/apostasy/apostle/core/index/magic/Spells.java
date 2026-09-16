package org.apostasy.apostle.core.index.magic;

import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.apostasy.apostle.core.magic.spell.abyssal.OpenEnderChestSpell;
import org.apostasy.apostle.core.magic.spell.abyssal.WaypointSpell;
import org.apostasy.apostle.core.magic.spell.caller.VexxedSpell;
import org.apostasy.apostle.core.magic.spell.gore.BloodlustSpell;
import org.apostasy.apostle.core.magic.spell.wick.CataclysmSpell;
import org.apostasy.apostle.core.magic.spell.wick.FireballSpell;
import org.apostasy.apostle.core.magic.spell.wick.RocketSpell;
import org.apostasy.apostle.core.magic.spell.wild.NurtureSpell;
import org.apostasy.apostle.core.magic.spell.wind.DashSpell;
import org.apostasy.apostle.core.magic.spell.wind.ThunderstrikeSpell;

/**
 * @author Chemthunder
 */
public interface Spells {
    /// WASTE

    /// WAVE

    /// WICK
    Spell CATACLYSM = register("cataclysm", new CataclysmSpell());
    Spell FIREBALL = register("fireball", new FireballSpell());
    Spell ROCKET = register("rocket", new RocketSpell());

    /// WILD
    Spell NURTURE = register("nuture", new NurtureSpell());

    /// WIND
    Spell DASH = register("dash", new DashSpell());
    Spell THUNDERSTRIKE = register("thunderstrike", new ThunderstrikeSpell());

    /// WORSHIP

    /// ABYSSAL
    Spell OPEN_ENDER_CHEST = register("open_ender_chest", new OpenEnderChestSpell());
    Spell WAYPOINT = register("waypoint", new WaypointSpell());

    /// CALLER
    Spell VEXXED = register("vexxed", new VexxedSpell());

    /// GORE
    Spell BLOODLUST = register("bloodlust", new BloodlustSpell());

    /// VEX

    private static Spell register(String name, Spell spell) {
        return Registry.register(ApostleRegistries.SPELL, Apostle.id(name), spell);
    }

    static void init() {}
}
