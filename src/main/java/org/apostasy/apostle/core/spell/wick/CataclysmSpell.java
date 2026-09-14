package org.apostasy.apostle.core.spell.wick;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.core.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CataclysmSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        FireballEntity entity = new FireballEntity(world, caster, caster.getRotationVec(0).multiply(1), 200);
        entity.setPosition(new Vec3d(caster.getEntityPos().x, caster.getEntityPos().y + 1, caster.getEntityPos().z));
        world.spawnEntity(entity);
    }

    public List<Item> getIngredients() {
        return List.of();
    }

    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    public String getName() {
        return "Cataclysm";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (15 * 20);
    }

    public boolean isUnobtainable() {
        return true;
    }
}
