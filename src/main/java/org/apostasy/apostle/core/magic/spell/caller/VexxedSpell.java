package org.apostasy.apostle.core.magic.spell.caller;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.entity.CallerVexEntity;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.index.tag.ApostleItemTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class VexxedSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        for (int i = 0; i < 3; i++) {
            CallerVexEntity entity = new CallerVexEntity(ApostleEntityTypes.CALLER_VEX, world);
            entity.setPosition(
                    new Vec3d(
                            caster.getX(),
                            caster.getY() + 0.5F,
                            caster.getZ()
                    )
            );
            entity.setOwner(caster);
            entity.setStackInHand(Hand.MAIN_HAND, Items.IRON_SWORD.getDefaultStack());
            world.spawnEntity(entity);
        }
    }

    private ItemStack getAcceptableItemStack() {
        List<Item> compiler = new ArrayList<>();

        for (Item item : Registries.ITEM) {
            if (item.getDefaultStack().isIn(ApostleItemTags.CALLER_VEX_ITEMS)) {
                compiler.add(item);
            }
        }

        return compiler.get(new Random().nextInt(compiler.size())).getDefaultStack();
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.TOTEM_OF_UNDYING,
                Items.PHANTOM_MEMBRANE,
                Items.IRON_SWORD,
                Items.OMINOUS_BOTTLE,
                Items.ROTTEN_FLESH
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.CALLER;
    }

    public String getName() {
        return "Vexxed";
    }

    public int getCastTime() {
        return (5 * 20);
    }

    public int getCooldown() {
        return (45 * 20);
    }
}
