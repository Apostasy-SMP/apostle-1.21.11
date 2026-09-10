package org.apostasy.apostle.api.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.entity.RitualEntity;

import java.util.List;

/**
 * @author Chemthunder
 */
public class TomeItem extends Item {
    private final String id;
    private final List<ItemConvertible> ritualIngredients;

    public TomeItem(String id, List<ItemConvertible> ritualIngredients) {
        super(new Settings()
                .maxCount(1)
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Apostle.id(id + "_tome")))
        );

        this.id = id;
        this.ritualIngredients = ritualIngredients;
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
                entity.setHeldTome(stack);

                world.spawnEntity(entity);

                player.swingHand(context.getHand());
                player.getItemCooldownManager().set(stack, 20);
            }
        }
        return super.useOnBlock(context);
    }

    public void tickRitual(World world, RitualEntity ritual) {}

    public String getId() {
        return id;
    }

    public List<ItemConvertible> getRitualIngredients() {
        return ritualIngredients;
    }
}
