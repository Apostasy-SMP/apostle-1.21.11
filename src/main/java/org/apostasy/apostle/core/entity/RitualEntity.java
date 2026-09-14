package org.apostasy.apostle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracked;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.Item;
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
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.item.TomeItem;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.ApostleRegistries;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class RitualEntity extends Entity implements DataTracked {
    public static final TrackedData<ItemStack> HELD_TOME = DataTracker.registerData(RitualEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    public static final TrackedData<List<ItemStack>> HELD_STACKS = DataTracker.registerData(RitualEntity.class, Apostle.ITEM_STACK_LIST);

    private int ticksTillCast = 0;

    public RitualEntity(World world) {
        super(ApostleEntityTypes.RITUAL, world);
        this.setHeldTome(ItemStack.EMPTY);
        this.setHeldStacks(new ArrayList<>());
    }

    public RitualEntity(EntityType<RitualEntity> entityType, World world) {
        super(entityType, world);
        this.setHeldTome(ItemStack.EMPTY);
        this.setHeldStacks(new ArrayList<>());
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(HELD_TOME, ItemStack.EMPTY);
        builder.add(HELD_STACKS, new ArrayList<>());
    }

    public void tick() {
        super.tick();

        World world = this.getEntityWorld();

        if (this.getHeldTome() != null) {
            if (this.getHeldStacks().size() < 9) {
                Box detect = new Box(this.getBlockPos()).expand(4, 1, 4);

                for (ItemEntity itemEntity : world.getEntitiesByClass(ItemEntity.class, detect, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR)) {
                    this.pushStack(itemEntity.getStack().split(1));
                    world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 1, 1);
                    itemEntity.discard();
                }
            }

            if (this.getHeldStacks().size() >= 9) {
                if (ticksTillCast < (3 * 20)) {
                    ticksTillCast++;
                    if (ticksTillCast >= (3 * 20)) {
                        this.onCast();
                        this.discard();
                    }
                }
            }

            if (this.getHeldStacks().isEmpty() && this.age >= (10 * 20)) {
                this.discard();
            }
        } else {
            this.discard();
        }
    }

    private void onCast() {
        World world = this.getEntityWorld();
        List<ItemStack> stacks = new ArrayList<>(this.getHeldStacks());
        ItemStack first = stacks.getFirst();

        if (!first.isEmpty() && first.getItem() instanceof TomeItem tome) {
            stacks.removeFirst();

            for (Spell spell : ApostleRegistries.SPELL) {
                if (!spell.isUnobtainable()) {
                    List<Item> spellIngredients = new ArrayList<>(spell.getIngredients());
                    List<Item> possibleIngredients = new ArrayList<>();

                    for (ItemStack stack : stacks) {
                        possibleIngredients.add(stack.getItem());
                    }

                    if (new HashSet<>(possibleIngredients).containsAll(spellIngredients)) {
                        ItemStack spellScroll = new ItemStack(ApostleItems.SPELL_SCROLL);
                        spellScroll.set(ApostleComponentTypes.STORED_SPELL, new StoredSpellComponent(spell));

                        ItemEntity spawnedScroll = new ItemEntity(EntityType.ITEM, world);
                        spawnedScroll.setStack(spellScroll);
                        spawnedScroll.setPos(this.getX(), this.getY() + 6, this.getZ());
                        world.spawnEntity(spawnedScroll);
                    }
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
        return false;
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    protected void readCustomData(ReadView view) {
        ticksTillCast = view.getInt("TicksTillCast", 0);
    }

    protected void writeCustomData(WriteView view) {
        view.putInt("TicksTillCast", ticksTillCast);
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
        List<ItemStack> stacks = new ArrayList<>(this.getHeldStacks());
        stacks.add(stack);
        this.setHeldStacks(stacks);
    }

    public void setHeldTome(ItemStack stack) {
        this.dataTracker.set(HELD_TOME, stack);
    }

    public List<ItemStack> getHeldStacks() {
        return this.dataTracker.get(HELD_STACKS);
    }

    public void setHeldStacks(List<ItemStack> heldStacks) {
        this.dataTracker.set(HELD_STACKS, heldStacks);
    }

    public ItemStack getPrimaryStack() {
        return this.getHeldStacks().getFirst();
    }
}
