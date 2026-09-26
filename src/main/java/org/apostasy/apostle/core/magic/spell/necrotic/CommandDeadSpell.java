package org.apostasy.apostle.core.magic.spell.necrotic;

import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.utilities.ModUtil;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CommandDeadSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        for (LivingEntity living : ModUtil.getNearbyLiving(world, caster.getEntityPos(), 15, living -> living.getType().isIn(EntityTypeTags.UNDEAD))) {
            if (living.getAttached(ApostleAttachmentTypes.OWNER) == null) {
                living.setAttached(ApostleAttachmentTypes.OWNER, LazyEntityReference.of(caster));
            }
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.BONE,
                Items.BONE,
                Items.BONE,
                Items.ROTTEN_FLESH,
                Items.ROTTEN_FLESH,
                Items.ROTTEN_FLESH,
                Items.SKELETON_SKULL
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.NECROTIC;
    }

    public String getName() {
        return "Command Dead";
    }

    public int getCastTime() {
        return 0;
    }

    public int getCooldown() {
        return (4 * 20);
    }
}
