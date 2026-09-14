package org.apostasy.apostle.api.magic;

import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.apostasy.apostle.core.entity.RitualEntity;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface Ritual {
    void cast(World world, RitualEntity ritual);

    List<Item> getIngredients();

    MagicSchool getMagicSchool();
}
