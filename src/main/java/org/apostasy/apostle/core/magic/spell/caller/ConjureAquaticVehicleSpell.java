package org.apostasy.apostle.core.magic.spell.caller;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class ConjureAquaticVehicleSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d spawnPos = caster.raycast(120, 0, true).getPos();
        BoatEntity boat = new BoatEntity(EntityType.OAK_BOAT, world, () -> Items.OAK_BOAT);
        boat.setPosition(spawnPos);
        world.spawnEntity(boat);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.OAK_BOAT,
                Items.ACACIA_BOAT,
                Items.BIRCH_BOAT,
                Items.DARK_OAK_BOAT,
                Items.SPRUCE_BOAT,
                Items.JUNGLE_BOAT,
                Items.MANGROVE_BOAT,
                Items.PALE_OAK_BOAT
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.CALLER;
    }

    public String getName() {
        return "Conjure Aquatic Vehicle";
    }

    public int getCastTime() {
        return (5 * 20);
    }

    public int getCooldown() {
        return (10 * 20);
    }
}
