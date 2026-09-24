package org.apostasy.apostle.core.status_effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import org.apostasy.apostle.core.index.magic.Schools;

/**
 * @author Chemthunder
 */
public class SleetriddenStatusEffect extends StatusEffect {
    public SleetriddenStatusEffect() {
        super(StatusEffectCategory.HARMFUL, Schools.WAVE.color());
    }
}
