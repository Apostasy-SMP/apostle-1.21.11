package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.MobEffectRegistrant;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.status_effect.RootedStatusEffect;

/**
 * @author Chemthunder
 */
public interface ApostleStatusEffects {
    MobEffectRegistrant plugin = new MobEffectRegistrant(Apostle.MOD_ID);

    RegistryEntry<StatusEffect> ROOTED = plugin.registerRef("rooted", new RootedStatusEffect());

    static void init() {}
}
