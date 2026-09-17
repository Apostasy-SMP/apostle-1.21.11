package org.apostasy.apostle.core.magic.spell.wave;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class SurgeSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        caster.useRiptide(20, 0, new ItemStack(Items.TRIDENT));
        caster.setVelocity(caster.getRotationVec(0).multiply(3));
    }

    public void createChargeParticles(World world, LivingEntity user, int progress) {
        Spell.super.createChargeParticles(world, user, progress);

        Vec3d pos = user.raycast(2, 0, false).getPos();
        Vec3d velocity = user.getRotationVec(0).multiply(-0F - (progress / 40F));
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            world.addImportantParticleClient(
                    ParticleTypes.END_ROD,
                    pos.x + random.nextFloat(-1.5F, 1.5F),
                    pos.y + random.nextFloat(-1.5F, 1.5F),
                    pos.z + random.nextFloat(-1.5F, 1.5F),
                    velocity.x,
                    velocity.y,
                    velocity.z
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.TRIDENT,
                Items.PRISMARINE,
                Items.SEA_LANTERN,
                Items.NAUTILUS_SHELL
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WAVE;
    }

    public String getName() {
        return "Surge";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (6 * 20);
    }
}
