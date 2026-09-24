package org.apostasy.apostle.core.item.artifact;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.abs.ArtifactItem;

import java.util.List;

/**
 * @author Chemthunder
 */
public class SturdyStoneItem extends ArtifactItem {
    public SturdyStoneItem(Settings settings) {
        super(settings);
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return ActionResult.PASS;
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.SMOOTH_STONE,
                Items.AMETHYST_SHARD,
                Items.AMETHYST_SHARD,
                Items.PRISMARINE_SHARD,
                Items.PRISMARINE_SHARD
        );
    }

    public List<Text> getDescription(ItemStack stack) {
        return List.of(
                Text.literal("Negates staves being reset when").formatted(Formatting.DARK_GRAY),
                Text.literal("taking damage whilst in the hotbar.").formatted(Formatting.DARK_GRAY)
        );
    }

    public MagicSchool getSchool() {
        return Schools.WASTE;
    }

    public int getCooldownTime() {
        return 0;
    }
}
