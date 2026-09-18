package org.apostasy.apostle.core.magic.spell.wild;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleStatusEffects;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class RootsSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d pos = caster.raycast(100, 0, false).getPos();
        PlayerEntity player = world.getClosestPlayer(pos.x, pos.y, pos.z, 3, false);

        if (player != null) {
            player.addStatusEffect(new StatusEffectInstance(ApostleStatusEffects.ROOTED, (6 * 20)));
        }
    }

    public boolean canCast(World world, LivingEntity caster, ItemStack staffStack) {
        Vec3d pos = caster.raycast(100, 0, false).getPos();
        return world.getClosestPlayer(pos.x, pos.y, pos.z, 3, false) != null;
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.MOSS_BLOCK,
                Items.MOSS_BLOCK,
                Items.MOSS_BLOCK
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WILD;
    }

    public String getName() {
        return "Roots";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (50 * 20);
    }
}
