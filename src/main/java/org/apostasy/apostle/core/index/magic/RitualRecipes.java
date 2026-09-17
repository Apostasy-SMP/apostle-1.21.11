package org.apostasy.apostle.core.index.magic;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registry;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.RitualRecipe;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface RitualRecipes {
    RitualRecipe ECHO_SHARD = register("echo_shard", new RitualRecipe() {
        public List<Item> getIngredients() {
            return List.of(
                    Items.AMETHYST_SHARD,
                    Items.SCULK,
                    Items.SCULK,
                    Items.SCULK,
                    Items.SCULK,
                    Items.SCULK,
                    Items.SCULK,
                    Items.SCULK
            );
        }

        public ItemStack getOutput() {
            return new ItemStack(Items.ECHO_SHARD);
        }

        public MagicSchool getSchool() {
            return Schools.ABYSSAL;
        }
    });

    RitualRecipe SKELETON_SKULL = register("skeleton_skull", new RitualRecipe() {
        public List<Item> getIngredients() {
            return List.of(
                    Items.WITHER_SKELETON_SKULL,
                    Items.BONE,
                    Items.BONE,
                    Items.BONE,
                    Items.BONE
            );
        }

        public ItemStack getOutput() {
            return new ItemStack(Items.SKELETON_SKULL);
        }

        public MagicSchool getSchool() {
            return Schools.WORSHIP;
        }
    });

    RitualRecipe ZOMBIE_SKULL = register("zombie_skull", new RitualRecipe() {
        public List<Item> getIngredients() {
            return List.of(
                    Items.SKELETON_SKULL,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH,
                    Items.ROTTEN_FLESH
            );
        }

        public ItemStack getOutput() {
            return new ItemStack(Items.ZOMBIE_HEAD);
        }

        public MagicSchool getSchool() {
            return Schools.WORSHIP;
        }
    });

    private static RitualRecipe register(String name, RitualRecipe craft) {
        return Registry.register(ApostleRegistries.RITUAL_RECIPE, Apostle.id(name), craft);
    }

    static void init() {}
}
