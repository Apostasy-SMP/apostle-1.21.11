package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.entity.ParticleDamageEntity;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class SoulStealerSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        ParticleDamageEntity entity = ParticleDamageEntity.create(world, DamageTypes.IN_FIRE, ParticleTypes.SOUL_FIRE_FLAME, 5);
        entity.setPosition(caster.getX(), caster.getY() + 1.5F, caster.getZ());
        entity.setVelocity(caster.getRotationVec(0).multiply(1));
        entity.setOwner(caster);
        world.spawnEntity(entity);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.SOUL_SAND,
                Items.SOUL_SOIL,
                Items.SOUL_TORCH,
                Items.FIRE_CHARGE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Soul Stealer";
    }

    public int getCastTime() {
        return (0 * 20);
    }

    public int getCooldown() {
        return (40 * 20);
    }
}
