package org.apostasy.apostle.core.item.artifact;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.abs.ArtifactItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class QuenchingAshItem extends ArtifactItem {
    public QuenchingAshItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        LivingEntity target = selfOrAlly(user);

        if (target != null) {
            target.extinguishWithSound();
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

    public int getCooldownTime() {
        return (2 * 20);
    }

    public SoundEvent getConsumeEvent() {
        return SoundEvents.BLOCK_FIRE_EXTINGUISH;
    }
}
