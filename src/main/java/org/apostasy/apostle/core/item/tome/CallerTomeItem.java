package org.apostasy.apostle.core.item.tome;

import net.minecraft.item.ItemConvertible;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.item.TomeItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CallerTomeItem extends TomeItem {
    public CallerTomeItem(String id, List<ItemConvertible> ritualIngredients, MagicSchool school) {
        super(id, ritualIngredients, school);
    }
}
