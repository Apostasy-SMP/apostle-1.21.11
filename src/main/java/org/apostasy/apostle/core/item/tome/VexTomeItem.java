package org.apostasy.apostle.core.item.tome;

import net.minecraft.item.ItemConvertible;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.item.TomeItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class VexTomeItem extends TomeItem {
    public VexTomeItem(String id, List<ItemConvertible> ritualIngredients, MagicSchool school) {
        super(id, ritualIngredients, school);
    }
}
