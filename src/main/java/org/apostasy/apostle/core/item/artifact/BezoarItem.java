package org.apostasy.apostle.core.item.artifact;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.abs.ArtifactItem;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class BezoarItem extends ArtifactItem {
    public BezoarItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking()) {
            LivingEntity target = selfOrAlly(user);

            if (target != null) {
                target.getStatusEffects().removeIf(instance -> !instance.getEffectType().value().isBeneficial());
                target.heal((2.5F * 2));
                user.getStackInHand(hand).split(1);

                for (int i = 0; i < 15; i++) {
                    Random random = new Random();

                    float xBound = 0.9F;
                    float yBound = 1.0F;

                    world.addParticleClient(
                            new MagicParticleEffect(Schools.WILD),
                            user.getX() + random.nextFloat(-xBound, xBound),
                            user.getY() + random.nextFloat(-yBound, yBound),
                            user.getZ() + random.nextFloat(-xBound, xBound),
                            0,
                            random.nextFloat(0.3F, 0.9F),
                            0
                    );
                }
            }
        }
        return super.use(world, user, hand);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.BONE,
                Items.BONE,
                Items.CLAY,
                Items.CLAY,
                Items.FLOWERING_AZALEA
        );
    }

    public MagicSchool getSchool() {
        return Schools.WILD;
    }

    public int getCooldownTime() {
        return (10 * 20);
    }
}
