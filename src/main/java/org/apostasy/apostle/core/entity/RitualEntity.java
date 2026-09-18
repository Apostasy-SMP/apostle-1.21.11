package org.apostasy.apostle.core.entity;

import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracked;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Ritual;
import org.apostasy.apostle.api.magic.RitualRecipe;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.*;
import org.apostasy.apostle.core.item.TomeItem;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class RitualEntity extends Entity implements DataTracked, Ownable {
    public static final TrackedData<ItemStack> HELD_TOME = DataTracker.registerData(RitualEntity.class, TrackedDataHandlerRegistry.ITEM_STACK);
    public static final TrackedData<List<ItemStack>> HELD_STACKS = DataTracker.registerData(RitualEntity.class, ApostleTrackedData.ITEM_STACK_LIST);

    private int ticksTillCast = 0;
    private boolean casting = false;

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

    public ActionResult interact(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        if (!casting) {
            if (stack.isEmpty()) {
                casting = true;
                return ActionResult.PASS;
            }
        }
        return super.interact(player, hand);
    }

    public boolean isInteractable() {
        return true;
    }

    public void tick() {
        super.tick();

        World world = this.getEntityWorld();

        if (this.getHeldTome() != null) {
            if (this.getHeldStacks().size() < 9) {
                Box detect = new Box(this.getBlockPos()).expand(2.6F, 1, 2.6F);

                for (ItemEntity itemEntity : world.getEntitiesByClass(ItemEntity.class, detect, EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR)) {
                    this.pushStack(itemEntity.getStack().split(1));
                    world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.PLAYERS, 1, 1);
                    itemEntity.discard();
                }
            }

            if (this.getHeldStacks().size() >= 9) {
                casting = true;
            }

            if (casting) {
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
        if (this.getOwner() instanceof LivingEntity living) {
            Apostle.grantAchievement(ApostleCriterions.CAST_RITUAL, living);
        }

        World world = this.getEntityWorld();
        List<ItemStack> stacks = new ArrayList<>(this.getHeldStacks());
        ItemStack first = stacks.getFirst();

        List<Item> items = new ArrayList<>();

        for (ItemStack stack : stacks) {
            items.add(stack.getItem());
        }

        if (!first.isEmpty() && first.getItem() instanceof TomeItem tome) {
            stacks.removeFirst();

            for (Spell spell : ApostleRegistries.SPELL) {
                if (!spell.isUnobtainable()) {
                    List<Item> spellIngredients = new ArrayList<>(spell.getIngredients());

                    do {
                        spellIngredients.add(ApostleItems.MAGIC_DUST);
                    } while (spellIngredients.size() != 9);

                    if (new HashSet<>(items).containsAll(spellIngredients)) {
                        ItemStack spellScroll = new ItemStack(ApostleItems.SPELL_SCROLL);
                        spellScroll.set(ApostleComponentTypes.STORED_SPELL, new StoredSpellComponent(spell));

                        ItemEntity spawnedScroll = new ItemEntity(EntityType.ITEM, world);
                        spawnedScroll.setStack(spellScroll);
                        spawnedScroll.setPos(this.getX(), this.getY() + 3, this.getZ());
                        world.spawnEntity(spawnedScroll);
                        break;
                    }
                }
            }
        }

        for (Ritual ritual : ApostleRegistries.RITUAL) {
            if (ritual.getMagicSchool() == this.getSchool()) {
                List<Item> ritualIngredients = new ArrayList<>(ritual.getIngredients());

                do {
                    ritualIngredients.add(ApostleItems.MAGIC_DUST);
                } while (ritualIngredients.size() < 9);

                if (new HashSet<>(items).containsAll(ritualIngredients)) {
                    ritual.cast(world, this, this.getOwner() != null ? this.getOwner() : null);
                    break;
                }
            }
        }

        for (RitualRecipe craft : ApostleRegistries.RITUAL_RECIPE) {
            if (craft.getSchool() == this.getSchool()) {
                List<Item> craftIngredients = new ArrayList<>(craft.getIngredients());

                do {
                    craftIngredients.add(ApostleItems.MAGIC_DUST);
                } while (craftIngredients.size() < 9);

                if (new HashSet<>(items).containsAll(craftIngredients)) {
                    ItemEntity spawnedItem = new ItemEntity(EntityType.ITEM, world);
                    spawnedItem.setStack(craft.getOutput());
                    spawnedItem.setPos(this.getX(), this.getY() + 3, this.getZ());
                    world.spawnEntity(spawnedItem);
                    break;
                }
            }
        }

        world.playSound(
                this,
                this.getX(),
                this.getY() + 3,
                this.getZ(),
                SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT,
                SoundCategory.PLAYERS,
                1,
                0.3F
        );

        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    ParticleTypes.TOTEM_OF_UNDYING,
                    this.getX(),
                    this.getY() + 3,
                    this.getZ(),
                    40,
                    0,
                    0,
                    0,
                    0.6F
            );
        }
    }

    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    protected void readCustomData(ReadView view) {
        ticksTillCast = view.getInt("TicksTillCast", 0);
        casting = view.getBoolean("Casting", false);
    }

    protected void writeCustomData(WriteView view) {
        view.putInt("TicksTillCast", ticksTillCast);
        view.putBoolean("Casting", casting);
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

    @Nullable
    public MagicSchool getSchool() {
        if (this.getHeldTome() != null) {
            return this.getHeldTome().getSchool();
        }
        return null;
    }

    @Nullable
    public Entity getOwner() {
        return LazyEntityReference.getLivingEntity(this.getAttached(ApostleAttachmentTypes.OWNER), this.getEntityWorld());
    }
}