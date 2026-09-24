package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.acoyt.acornlib.api.item.SprintUsableItem;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleCriterions;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.component.StoredSpellComponent;
import org.apostasy.apostle.core.networking.s2c.UseSpellPayload;
import org.jspecify.annotations.Nullable;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class StaffItem extends Item implements SprintUsableItem {
    private static final float GUI_EXPAND_SIZE = 0.6F;

    public StaffItem(Settings settings) {
        super(settings);
    }

    public Text getName(ItemStack stack) {
        return Text.literal("Arcane Staff");
    }

    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        ItemStack offStack = user.getOffHandStack();
        ItemCooldownManager cooldown = user.getItemCooldownManager();

        if (!cooldown.isCoolingDown(stack) && offStack.getOrDefault(ApostleComponentTypes.SCROLL_COOLDOWN, 0) <= 0) {
            if (offStack.isOf(ApostleItems.SPELL_SCROLL)) {
                if (offStack.contains(ApostleComponentTypes.STORED_SPELL)) {
                    StoredSpellComponent component = offStack.get(ApostleComponentTypes.STORED_SPELL);

                    if (component != null) {
                        Spell spell = component.spell();

                        if (spell.canCast(world, user, user.getMainHandStack())) {
                            if (!cooldown.isCoolingDown(offStack)) {
                                if (spell.getCastTime() <= 0) {
                                    spell.cast(world, user);

                                    if (user instanceof ServerPlayerEntity serverPlayer) {
                                        ServerPlayNetworking.send(serverPlayer, new UseSpellPayload(GUI_EXPAND_SIZE));
                                    }

                                    if (!user.isCreative()) {
                                        ItemCooldownManager manager = user.getItemCooldownManager();

                                        offStack.set(ApostleComponentTypes.SCROLL_COOLDOWN, spell.getCooldown());
                                        manager.set(stack, spell.getStaffCooldown());
                                    }

                                    Apostle.grantAchievement(ApostleCriterions.CAST_SPELL, user);

                                    user.swingHand(hand);
                                } else {
                                    if (spell.canCast(world, user, stack)) {
                                        user.setCurrentHand(hand);
                                        return ActionResult.CONSUME;
                                    }
                                    return ActionResult.FAIL;
                                }
                                return ActionResult.CONSUME;
                            }
                        }
                    }
                }
            }

            if (!user.getOffHandStack().isOf(ApostleItems.SPELL_SCROLL)) {
                user.setCurrentHand(hand);
            }
        }
        return super.use(world, user, hand);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        Spell spell = getOffhandSpell(user);
        if (spell != null && spell.canCast(user.getEntityWorld(), user, stack)) {
            return spell.getCastTime();
        }
        return 720000;
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        int time = this.getMaxUseTime(stack, user) - remainingUseTicks;
        Spell spell = getOffhandSpell(user);

        if (spell != null) {
            if (user.getOffHandStack().isOf(ApostleItems.SPELL_SCROLL) && !spell.canCast(world, user, stack)) {
                user.stopUsingItem();
            }
        }

        if (spell != null && spell.canCast(world, user, stack) && spell.getMagicSchool() != Schools.NONE) {
            spell.createChargeParticles(world, user, time);
            spell.tickCharge(world, user);
        } else {
            Vec3d particlePos = user.raycast(1.3, 0, false).getPos();
            ParticleEffect effect = null;

            if (stack.contains(ApostleComponentTypes.SCHOOL) && stack.get(ApostleComponentTypes.SCHOOL) != null && stack.get(ApostleComponentTypes.SCHOOL) != Schools.NONE) {
                MagicSchool school = stack.get(ApostleComponentTypes.SCHOOL);

                if (school != null) {
                    if (school == Schools.NONE) {
                        effect = ParticleTypes.END_ROD;
                    } else {
                        effect = new MagicParticleEffect(school);
                    }
                }
            } else {
                effect = ParticleTypes.END_ROD;
            }

            if (effect != null) {
                world.addParticleClient(
                        effect,
                        particlePos.x,
                        particlePos.y,
                        particlePos.z,
                        0,
                        0,
                        0
                );
            }
        }
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        Spell spell = getOffhandSpell(user);

        if (spell != null && spell.canCast(world, user, stack)) {
            if (user instanceof PlayerEntity player) {
                ItemCooldownManager manager = player.getItemCooldownManager();

                Apostle.grantAchievement(ApostleCriterions.CAST_SPELL, user);

                if (!player.isCreative()) {
                    manager.set(stack, spell.getStaffCooldown());
                }
            }

            if (!user.isInCreativeMode()) {
                user.getOffHandStack().set(ApostleComponentTypes.SCROLL_COOLDOWN, spell.getCooldown());
            }

            spell.cast(world, user);

            if (user instanceof ServerPlayerEntity serverPlayer) {
                ServerPlayNetworking.send(serverPlayer, new UseSpellPayload(GUI_EXPAND_SIZE));
            }
        }
        return super.finishUsing(stack, world, user);
    }

    @Nullable
    public Spell getOffhandSpell(LivingEntity user) {
        ItemStack off = user.getOffHandStack();

        if (off.isOf(ApostleItems.SPELL_SCROLL)) {
            if (off.contains(ApostleComponentTypes.STORED_SPELL)) {
                StoredSpellComponent spellComponent = off.get(ApostleComponentTypes.STORED_SPELL);

                if (spellComponent != null) {
                    return spellComponent.spell();
                }
            }
        }
        return null;
    }

    public static boolean isActive(LivingEntity user) {
        return (user.getMainHandStack().isOf(ApostleItems.STAFF)
                && user.getOffHandStack().isOf(ApostleItems.SPELL_SCROLL) && user.isUsingItem());
    }

    public ItemStack getDefaultStack() {
        ItemStack s = super.getDefaultStack();
        s.set(ApostleComponentTypes.SCHOOL, Schools.NONE);
        return s;
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(ApostleItems.STAFF)) {
                if (stack.contains(ApostleComponentTypes.SCHOOL)) {
                    MagicSchool school = stack.get(ApostleComponentTypes.SCHOOL);

                    if (school != null && school != Schools.NONE) {
                        if (school.name() != Schools.NONE.name()) {
                            lines.accept(Text.empty()
                                    .append(Text.literal("- ").formatted(Formatting.DARK_GRAY))
                                    .append(school.name().copy().withColor(school.color()))
                            );
                        }
                    }
                }
            }
        }
    }
}
