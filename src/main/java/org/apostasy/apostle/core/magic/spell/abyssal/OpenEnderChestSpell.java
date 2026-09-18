package org.apostasy.apostle.core.magic.spell.abyssal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class OpenEnderChestSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        if (caster instanceof PlayerEntity player) {
            EnderChestInventory enderChestInventory = player.getEnderChestInventory();

            player.openHandledScreen(
                    new SimpleNamedScreenHandlerFactory(
                            (
                                    syncId,
                                    playerInventory,
                                    playerx
                            ) ->
                                    GenericContainerScreenHandler.createGeneric9x3(
                                            syncId,
                                            playerInventory,
                                            enderChestInventory
                                    ),
                            Text.translatable("container.enderchest")
                    )
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.ENDER_CHEST,
                Items.ENDER_EYE,
                Items.ENDER_PEARL,
                Items.OBSIDIAN
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.ABYSSAL;
    }

    public String getName() {
        return "Access Ethereal";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (4 * 20);
    }
}
