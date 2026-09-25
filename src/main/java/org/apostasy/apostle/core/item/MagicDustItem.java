package org.apostasy.apostle.core.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleCriterions;

/**
 * @author Chemthunder
 */
public class MagicDustItem extends Item {
    public MagicDustItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Apostle.grantAchievement(ApostleCriterions.EAT_DUST, user);
        return super.finishUsing(stack, world, user);
    }
}
