package org.apostasy.apostle.core.index.magic;

import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.apostasy.apostle.core.magic.spell.abyssal.OpenEnderChestSpell;
import org.apostasy.apostle.core.magic.spell.abyssal.WaypointSpell;
import org.apostasy.apostle.core.magic.spell.caller.ConjureAquaticVehicleSpell;
import org.apostasy.apostle.core.magic.spell.caller.VexxedSpell;
import org.apostasy.apostle.core.magic.spell.gore.BloodlustSpell;
import org.apostasy.apostle.core.magic.spell.gore.VeinpiercerSpell;
import org.apostasy.apostle.core.magic.spell.vex.EvokeSpell;
import org.apostasy.apostle.core.magic.spell.wave.TidewaySpell;
import org.apostasy.apostle.core.magic.spell.wick.*;
import org.apostasy.apostle.core.magic.spell.wild.NurtureSpell;
import org.apostasy.apostle.core.magic.spell.wild.RootsSpell;
import org.apostasy.apostle.core.magic.spell.wind.DashSpell;
import org.apostasy.apostle.core.magic.spell.wind.ThunderstrikeSpell;
import org.apostasy.apostle.core.magic.spell.worship.HolyNetSpell;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public interface Spells {
    /// WASTE

    /// WAVE
    Spell TIDEWAY = register("surge", new TidewaySpell());

    /// WICK
    Spell DINO_DESTROYER = register("dino_destroyer", new DinoDestroyerSpell());
    Spell FIREBALL = register("fireball", new FireballSpell());
    Spell ROCKET = register("rocket", new RocketSpell());
    Spell SOUL_STEALER = register("soul_stealer", new SoulStealerSpell());
    Spell PYROMANIAC = register("pyromaniac", new PyromaniacSpell());

    /// WILD
    Spell NURTURE = register("nuture", new NurtureSpell());
    Spell ROOTS = register("roots", new RootsSpell());

    /// WIND
    Spell DASH = register("dash", new DashSpell());
    Spell THUNDERSTRIKE = register("thunderstrike", new ThunderstrikeSpell());

    /// WORSHIP
    Spell HOLY_NET = register("holy_net", new HolyNetSpell());

    /// ABYSSAL
    Spell OPEN_ENDER_CHEST = register("open_ender_chest", new OpenEnderChestSpell());
    Spell WAYPOINT = register("waypoint", new WaypointSpell());

    /// CALLER
    Spell VEXXED = register("vexxed", new VexxedSpell());
    Spell CONJURE_AQUATIC_VEHICLE = register("conjure_aquatic_vehicle", new ConjureAquaticVehicleSpell());

    /// GORE
    Spell BLOODLUST = register("bloodlust", new BloodlustSpell());
    Spell VEINPIERCER = register("veinpiercer", new VeinpiercerSpell());

    /// VEX
    Spell EVOKE = register("evoke", new EvokeSpell());

    private static Spell register(String name, Spell spell) {
        return Registry.register(ApostleRegistries.SPELL, Apostle.id(name), spell);
    }

    static void init() {}
}
