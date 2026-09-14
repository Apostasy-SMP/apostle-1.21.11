package org.apostasy.apostle.core.item.tome;

import net.minecraft.item.ItemConvertible;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.item.TomeItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class WildTomeItem extends TomeItem {
    public WildTomeItem(String id, List<ItemConvertible> ritualIngredients, MagicSchool school) {
        super(id, ritualIngredients, school);
    }

    public void tickRitual(World world, RitualEntity ritual) {
        super.tickRitual(world, ritual);
    }
}
