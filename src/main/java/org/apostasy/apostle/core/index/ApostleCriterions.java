package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.CriterionTriggerRegistrant;
import net.minecraft.advancement.criterion.TickCriterion;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleCriterions {
    CriterionTriggerRegistrant plugin = new CriterionTriggerRegistrant(Apostle.MOD_ID);

    TickCriterion CAST_SPELL = plugin.register("cast_spell", new TickCriterion());
    TickCriterion CAST_RITUAL = plugin.register("cast_ritual", new TickCriterion());

    static void init() {}
}
