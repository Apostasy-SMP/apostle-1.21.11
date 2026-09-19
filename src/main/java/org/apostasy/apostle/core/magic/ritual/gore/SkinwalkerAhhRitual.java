package org.apostasy.apostle.core.magic.ritual.gore;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Ritual;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.magic.Schools;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * @author Chemthunder
 */
public class SkinwalkerAhhRitual implements Ritual {
    public void cast(World world, RitualEntity ritual, @Nullable Entity owner) {
        List<ItemStack> stacks = ritual.getHeldStacks();

        for (ItemStack stack : stacks) {
            if (stack.isOf(Items.PLAYER_HEAD) && stack.contains(DataComponentTypes.PROFILE)) {
                ProfileComponent profileComponent = stack.get(DataComponentTypes.PROFILE);

                ItemEntity idol = new ItemEntity(EntityType.ITEM, world);
                idol.setStack(Apostle.createStackWithComponent(ApostleItems.TRANS_IDOL, DataComponentTypes.PROFILE, profileComponent));
                idol.setPosition(new Vec3d(ritual.getX(), ritual.getY() + 3, ritual.getZ()));
                world.spawnEntity(idol);
            }
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                ApostleItems.PURE_BLOOD,
                ApostleItems.PURE_BLOOD,
                Items.PLAYER_HEAD
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.GORE;
    }
}
