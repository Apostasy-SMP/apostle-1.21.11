package org.apostasy.apostle.core.magic.ritual.vex;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.OminousBottleAmplifierComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Ritual;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class CreateOminousBottleRitual implements Ritual {
    public void cast(World world, RitualEntity ritual) {
        ItemStack bottleStack = new ItemStack(Items.OMINOUS_BOTTLE);
        bottleStack.set(DataComponentTypes.OMINOUS_BOTTLE_AMPLIFIER, new OminousBottleAmplifierComponent(world.random.nextBetween(1, 4)));

        ItemEntity itemEntity = new ItemEntity(EntityType.ITEM, world);

        itemEntity.setPosition(new Vec3d(ritual.getX(), ritual.getY() + 3, ritual.getZ()));
        itemEntity.setStack(bottleStack);

        world.spawnEntity(itemEntity);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.GLASS_BOTTLE,
                Items.ROTTEN_FLESH,
                Items.ROTTEN_FLESH,
                Items.WEEPING_VINES,
                Items.WEEPING_VINES
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.VEX;
    }
}
