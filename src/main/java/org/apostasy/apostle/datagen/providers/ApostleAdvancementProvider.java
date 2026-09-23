package org.apostasy.apostle.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.item.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.apostasy.apostle.core.index.ApostleCriterions;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.magic.Spells;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.apostasy.apostle.core.Apostle.createStackWithComponent;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class ApostleAdvancementProvider extends FabricAdvancementProvider {
    public ApostleAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry root = Advancement.Builder.createUntelemetered()
                .display(
                        ApostleItems.WILD_TOME,
                        Text.literal("Apostle"),
                        Text.literal("Wield magic from beyond the very cosmos themselves!"),
                        Apostle.id("gui/advancements/backgrounds/apostle"),
                        AdvancementFrame.TASK,
                        false,
                        false,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("tick")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("tick", TickCriterion.Conditions.createTick())
                .build(Apostle.id("root"));

        consumer.accept(root);

        AdvancementEntry castRitual = generateBasicAdvancement(
                consumer,
                root,
                new AdvancementContext(
                        ApostleItems.WIND_TOME.getDefaultStack(),
                        "cast_ritual",
                        ApostleCriterions.CAST_RITUAL.create(new TickCriterion.Conditions(Optional.empty()))
                )
        );

        AdvancementEntry castSpell = generateBasicAdvancement(
                consumer,
                castRitual,
                new AdvancementContext(
                        createStackWithComponent(
                                ApostleItems.SPELL_SCROLL,
                                ApostleComponentTypes.STORED_SPELL,
                                new StoredSpellComponent(Spells.DASH)
                        ),
                        "cast_spell",
                        ApostleCriterions.CAST_SPELL.create(new TickCriterion.Conditions(Optional.empty()))
                )
        );

        AdvancementEntry popWoodenTotem = generateBasicAdvancement(
                consumer,
                castRitual,
                new AdvancementContext(
                        ApostleItems.WOODEN_TOTEM.getDefaultStack(),
                        "pop_wooden_totem",
                        ApostleCriterions.POP_WOODEN_TOTEM.create(new TickCriterion.Conditions(Optional.empty()))
                )
        );
    }

    private AdvancementEntry generateBasicAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry root, AdvancementContext context) {
        AdvancementEntry generated = Advancement.Builder.createUntelemetered()
                .parent(root)
                .display(
                        context.displayStack,
                        Text.translatable("advancements.apostle." + context.title + ".title"),
                        Text.translatable("advancements.apostle." + context.title + ".desc"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                ).requirements(AdvancementRequirements.allOf(List.of("e")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("e", context.criterion)
                .build(Apostle.id(context.title));

        consumer.accept(generated);
        return generated;
    }

    private AdvancementEntry generateHiddenAdvancement(Consumer<AdvancementEntry> consumer, AdvancementEntry root, AdvancementContext context) {
        AdvancementEntry generated = Advancement.Builder.createUntelemetered()
                .parent(root)
                .display(
                        context.displayStack,
                        Text.translatable("advancements.apostle." + context.title + ".title"),
                        Text.translatable("advancements.apostle." + context.title + ".desc"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        true
                ).requirements(AdvancementRequirements.allOf(List.of("e")))
                .criteriaMerger(AdvancementRequirements.CriterionMerger.AND)
                .criterion("e", context.criterion)
                .build(Apostle.id(context.title));

        consumer.accept(generated);
        return generated;
    }

    private record AdvancementContext(ItemStack displayStack, String title, AdvancementCriterion<?> criterion) {}
}
