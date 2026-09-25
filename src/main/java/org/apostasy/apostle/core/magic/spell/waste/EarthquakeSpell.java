package org.apostasy.apostle.core.magic.spell.waste;

import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.entity.EarthquakeEntity;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class EarthquakeSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        EarthquakeEntity quake = new EarthquakeEntity(ApostleEntityTypes.EARTHQUAKE, world);
        quake.setPosition(caster.getEntityPos());
        quake.setAttached(ApostleAttachmentTypes.OWNER, LazyEntityReference.of(caster));
        world.spawnEntity(quake);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.MUD,
                Items.MUD,
                Items.DIRT,
                Items.DIRT,
                Items.CLAY
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WASTE;
    }

    public String getName() {
        return "Earthquake";
    }

    public int getCastTime() {
        return (6 * 20);
    }

    public int getCooldown() {
        return (60 * 20);
    }
}
