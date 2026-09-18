package org.apostasy.apostle.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.client.render.item.property.bool.UsingItemProperty;
import net.minecraft.client.render.item.property.select.DisplayContextProperty;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.item.MagicSchoolProperty;
import org.apostasy.apostle.core.client.item.SpellScrollProperty;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.client.ApostleModels;
import org.apostasy.apostle.core.index.magic.Schools;
import org.apostasy.apostle.core.item.ArtifactItem;
import org.apostasy.apostle.core.item.TomeItem;

import java.util.Arrays;

/**
 * @author Chemthunder
 */
public class ApostleModelProvider extends FabricModelProvider {
    public ApostleModelProvider(FabricDataOutput output) {
        super(output);
    }

    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {}

    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : ApostleItems.plugin.toRegister) {
            if (item instanceof TomeItem || item instanceof ArtifactItem) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        }

        itemModelGenerator.register(ApostleItems.MAGIC_DUST, Models.GENERATED);

        createSpellScroll(itemModelGenerator);
        createStaff(itemModelGenerator);
        createBaseStaff(itemModelGenerator);
    }

    private void createSpellScroll(ItemModelGenerator generator) {
        generator.output.accept(ApostleItems.SPELL_SCROLL,
                ItemModels.select(
                        new SpellScrollProperty(),
                        getSpellCase(Schools.ABYSSAL, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.CALLER, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.GORE, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.VEX, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WASTE, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WAVE, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WICK, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WILD, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WIND, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED),
                        getSpellCase(Schools.WORSHIP, generator, ApostleItems.SPELL_SCROLL, Models.GENERATED)
                )
        );
    }

    private void createBaseStaff(ItemModelGenerator generator) {
        Item item = ApostleItems.MAGIC_STAFF;
        Identifier id = ModelIds.getItemModelId(item);

        Identifier gui = Models.GENERATED.upload(
                item,
                TextureMap.layer0(item),
                generator.modelCollector
        );

        generator.output.accept(item,
                ItemModels.condition(
                        new UsingItemProperty(),
                        ItemModels.select(
                                new DisplayContextProperty(),
                                ItemModels.basic(id.withSuffixedPath("_in_hand_using")),
                                ItemModels.switchCase(
                                        Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                                        ItemModels.basic(gui)
                                )
                        ),
                        ItemModels.select(
                                new DisplayContextProperty(),
                                ItemModels.basic(id.withSuffixedPath("_in_hand")),
                                ItemModels.switchCase(
                                        Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                                        ItemModels.basic(gui)
                                )
                        )
                ));
    }

    private void createStaff(ItemModelGenerator generator) {
        Item item = ApostleItems.ARCANE_STAFF;
        generator.output.accept(item,
                ItemModels.condition(
                        new UsingItemProperty(),
                        ItemModels.select( // Using
                                new MagicSchoolProperty(),
                                createStaffModel(Schools.ABYSSAL, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.CALLER, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.GORE, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.VEX, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WASTE, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WAVE, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WICK, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WILD, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WIND, generator, item, ApostleModels.STAFF_IN_HAND_USING, true),
                                createStaffModel(Schools.WORSHIP, generator, item, ApostleModels.STAFF_IN_HAND_USING, true)
                        ),
                        ItemModels.select(
                                new MagicSchoolProperty(),
                                createStaffModel(Schools.ABYSSAL, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.CALLER, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.GORE, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.VEX, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WASTE, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WAVE, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WICK, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WILD, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WIND, generator, item, ApostleModels.STAFF_IN_HAND, false),
                                createStaffModel(Schools.WORSHIP, generator, item, ApostleModels.STAFF_IN_HAND, false)
                        )
                )
        );
    }

    private SelectItemModel.SwitchCase<MagicSchool> createStaffModel(MagicSchool school, ItemModelGenerator generator, Item item, Model model, boolean using) {
        return ItemModels.switchCase(school, ItemModels.select(
                new DisplayContextProperty(),
                ItemModels.basic(
                        model.upload(
                                ModelIds.getItemModelId(item).withSuffixedPath("_" + school.name().getString().toLowerCase() + "_staff_in_hand" + (using ? "_using" : "")),
                                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_staff_in_hand")),
                                generator.modelCollector
                        )
                ),
                ItemModels.switchCase(
                        Arrays.asList(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                        ItemModels.basic(
                                Models.GENERATED.upload(
                                        ModelIds.getItemModelId(item).withSuffixedPath("_" + school.name().getString().toLowerCase() + (using ? "_using" : "")),
                                        TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_staff")),
                                        generator.modelCollector
                                )
                        )
                )
        ));
    }

    private SelectItemModel.SwitchCase<MagicSchool> getSchoolCase(MagicSchool school, ItemModelGenerator generator, Item item, Model model) {
        return ItemModels.switchCase(
                school,
                ItemModels.basic(createSchooledModel(school, generator, item, model))
        );
    }

    private SelectItemModel.SwitchCase<MagicSchool> getSpellCase(MagicSchool school, ItemModelGenerator generator, Item item, Model model) {
        return ItemModels.switchCase(
                school,
                ItemModels.basic(createSchooledModel(school, generator, item, model, "scroll"))
        );
    }

    private Identifier createSchooledModel(MagicSchool school, ItemModelGenerator generator, Item item, Model model) {
        return model.upload(
                ModelIds.getItemModelId(item).withSuffixedPath("_" + school.name().getString().toLowerCase()),
                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase())),
                generator.modelCollector
        );
    }

    private Identifier createSchooledModel(MagicSchool school, ItemModelGenerator generator, Item item, Model model, String extension) {
        return model.upload(
                ModelIds.getItemModelId(item).withSuffixedPath("_" + school.name().getString().toLowerCase() + "_" + extension),
                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_" + extension)),
                generator.modelCollector
        );
    }

    private Identifier createSchooledModel(MagicSchool school, ItemModelGenerator generator, Identifier item, Model model, String extension) {
        return model.upload(
                item.withSuffixedPath("_" + school.name().getString().toLowerCase() + "_" + extension),
                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_" + extension)),
                generator.modelCollector
        );
    }

    private Identifier createSchooledModel(MagicSchool school, ItemModelGenerator generator, Identifier item, Model model) {
        return model.upload(
                item.withSuffixedPath("_" + school.name().getString().toLowerCase()),
                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_scroll")),
                generator.modelCollector
        );
    }
}
