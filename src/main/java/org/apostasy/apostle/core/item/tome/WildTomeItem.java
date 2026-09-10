package org.apostasy.apostle.core.item.tome;

import net.minecraft.item.ItemConvertible;
import net.minecraft.world.World;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.entity.RitualEntity;

import java.util.List;

/**
 * @author Chemthunder
 */
public class WildTomeItem extends TomeItem {
    public WildTomeItem(String id, List<ItemConvertible> ritualIngredients) {
        super(id, ritualIngredients);
    }

    public void tickRitual(World world, RitualEntity ritual) {
        super.tickRitual(world, ritual);
    }
}
