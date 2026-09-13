package org.apostasy.apostle.api.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.entity.RitualEntity;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class TomeItem extends Item {
    private final String id;
    private final List<ItemConvertible> ritualIngredients;
    private final MagicSchool school;

    public TomeItem(String id, List<ItemConvertible> ritualIngredients, MagicSchool school) {
        super(new Settings()
                .maxCount(1)
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Apostle.id(id + "_tome")))
        );

        this.id = id;
        this.ritualIngredients = ritualIngredients;
        this.school = school;
    }

    public Text getName(ItemStack stack) {
        return Text.literal("Arcane Tome");
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();

        if (player != null && world != null && stack != null) {
            if (player.isSneaking()) {
                RitualEntity entity = new RitualEntity(world);

                entity.setPosition(pos.toCenterPos());
                entity.setHeldTome(new ItemStack(this));

                world.spawnEntity(entity);

                player.swingHand(context.getHand());
                player.getItemCooldownManager().set(stack, 20);
            }
        }
        return super.useOnBlock(context);
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.getItem() instanceof TomeItem tome) {
                lines.accept(Text.literal("- ").formatted(Formatting.DARK_GRAY).append(tome.school.name().copy().withColor(tome.school.color())));
            }
        }
    }

    public void tickRitual(World world, RitualEntity ritual) {}

    public String getId() {
        return id;
    }

    public List<ItemConvertible> getRitualIngredients() {
        return ritualIngredients;
    }

    public MagicSchool getSchool() {
        return school;
    }
}
