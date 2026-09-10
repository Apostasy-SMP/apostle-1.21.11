package org.apostasy.apostle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracked;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.text.Text;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public class RitualEntity extends Entity implements DataTracked {
    public static final TrackedData<ItemStack> HELD_TOME = DataTracker.registerData(RitualEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);

    private List<ItemStack> heldStacks = new ArrayList<>();

    public RitualEntity(World world) {
        super(ApostleEntityTypes.RITUAL, world);
        this.setHeldTome(null);
    }

    public RitualEntity(EntityType<RitualEntity> entityType, World world) {
        super(entityType, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(HELD_TOME, ItemStack.EMPTY);
    }

    public void tick() {
        super.tick();

        if (this.getHeldTome() != null) {
            TomeItem tome = this.getHeldTome();
            tome.tickRitual(this.getEntityWorld(), this);


            Box detect = new Box(this.getBlockPos()).expand(4, 1, 4);

            for (Entity entity : getEntityWorld().getEntitiesByClass(Entity.class, detect, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR)) {
                if (entity instanceof ItemEntity itemEntity) {
                    this.pushStack(itemEntity.getStack().split(1));
                    this.getEntityWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 1, 1);
                    itemEntity.discard();
                }
            }
        }
    }

    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    public Text getDisplayName() {
        return Text.of(this.getHeldTome() != null ? this.getHeldTome().getId() : "NULL" + " - ");
    }

    public Text getName() {
        return this.getDisplayName();
    }

    public boolean shouldRenderName() {
        return true;
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    protected void readCustomData(ReadView view) {
        heldStacks = view.read("HeldStacks", ItemStack.CODEC.listOf()).orElse(new ArrayList<>());
    }

    protected void writeCustomData(WriteView view) {
        if (!heldStacks.isEmpty()) {
            view.put("HeldStacks", ItemStack.CODEC.listOf(), heldStacks);
        }
    }

    @Nullable
    public TomeItem getHeldTome() {
        ItemStack stack = this.dataTracker.get(HELD_TOME);

        if (stack.getItem() instanceof TomeItem tome) {
            return tome;
        }

        return null;
    }

    public ItemStack getHeldTomeStack() {
        return this.dataTracker.get(HELD_TOME);
    }

    public void pushStack(ItemStack stack) {
        List<ItemStack> stacks = new ArrayList<>(this.heldStacks);
        stacks.add(stack);
        this.setHeldStacks(stacks);
    }

    public void setHeldTome(ItemStack stack) {
        this.dataTracker.set(HELD_TOME, stack);
    }

    public List<ItemStack> getHeldStacks() {
        return heldStacks;
    }

    public void setHeldStacks(List<ItemStack> heldStacks) {
        this.heldStacks = heldStacks;
    }
}
