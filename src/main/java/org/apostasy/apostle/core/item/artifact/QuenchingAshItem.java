package org.apostasy.apostle.core.item.artifact;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.ArtifactItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class QuenchingAshItem extends ArtifactItem {
    public QuenchingAshItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            LivingEntity target = selfOrAlly(user);

            if (target != null) {
                target.extinguishWithSound();
                user.getStackInHand(hand).split(1);
            }
        }
        return super.use(world, user, hand);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.BLAZE_POWDER,
                Items.BLAZE_POWDER,
                Items.BLAZE_POWDER,
                Items.CLAY,
                Items.CLAY,
                Items.STICK,
                Items.STICK,
                Items.STICK,
                Items.STICK
        );
    }

    public MagicSchool getSchool() {
        return Schools.WICK;
    }
}
