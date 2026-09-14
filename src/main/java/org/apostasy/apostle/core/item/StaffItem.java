package org.apostasy.apostle.core.item;

import net.acoyt.acornlib.api.event.BetterItemTooltipEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleItems;

import java.util.Random;
import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public class StaffItem extends Item {
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

        if (!cooldown.isCoolingDown(stack)) {
            if (offStack.isOf(ApostleItems.SPELL_SCROLL)) {
                if (offStack.contains(ApostleComponentTypes.STORED_SPELL)) {
                    StoredSpellComponent component = offStack.get(ApostleComponentTypes.STORED_SPELL);

                    if (component != null) {
                        Spell spell = component.spell();

                        if (!cooldown.isCoolingDown(offStack)) {
                            if (spell.getCastTime() <= 0) {
                                spell.cast(world, user);
                                user.swingHand(hand);
                            } else {
                                user.setCurrentHand(hand);
                            }
                            return ActionResult.CONSUME;
                        }
                    }
                }
            }
            user.setCurrentHand(hand);
        }
        return super.use(world, user, hand);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        ItemStack off = user.getOffHandStack();

        if (off.isOf(ApostleItems.SPELL_SCROLL)) {
            if (off.contains(ApostleComponentTypes.STORED_SPELL)) {
                StoredSpellComponent component = off.get(ApostleComponentTypes.STORED_SPELL);

                if (component != null) {
                    Spell spell = component.spell();

                    Vec3d particlePos = user.raycast(1.3, 0, false).getPos();

                    world.addParticleClient(
                            spell.getChargeParticleEffects().get(new Random().nextInt(spell.getChargeParticleEffects().size())),
                            particlePos.x,
                            particlePos.y,
                            particlePos.z,
                            0,
                            0,
                            0
                    );
                    return;
                }
            }
        }

        Vec3d particlePos = user.raycast(1.3, 0, false).getPos();

        world.addParticleClient(
                ParticleTypes.END_ROD,
                particlePos.x,
                particlePos.y,
                particlePos.z,
                0,
                0,
                0
        );
    }

    public static class Tooltip implements BetterItemTooltipEvent {
        public void getTooltip(ItemStack stack, TooltipContext tooltipContext, TooltipType tooltipFlag, Consumer<Text> lines) {
            if (stack.isOf(ApostleItems.ARCANE_STAFF)) {
                if (stack.contains(ApostleComponentTypes.SCHOOL)) {
                    MagicSchool school = stack.get(ApostleComponentTypes.SCHOOL);

                    if (school != null) {
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
