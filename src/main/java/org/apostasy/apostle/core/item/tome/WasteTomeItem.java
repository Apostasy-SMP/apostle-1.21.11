package org.apostasy.apostle.core.item.tome;

import net.minecraft.item.ItemConvertible;
import org.apostasy.apostle.api.item.TomeItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class WasteTomeItem extends TomeItem {
    public WasteTomeItem(String id, List<ItemConvertible> ritualIngredients) {
        super(id, ritualIngredients);
    }
}
