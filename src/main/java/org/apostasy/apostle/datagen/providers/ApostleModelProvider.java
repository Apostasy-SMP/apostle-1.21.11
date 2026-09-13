package org.apostasy.apostle.datagen.providers;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.SelectItemModel;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.item.SpellScrollProperty;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.core.Schools;

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
            if (item instanceof TomeItem) {
                itemModelGenerator.register(item, Models.GENERATED);
            }
        }

        createSpellScroll(itemModelGenerator);
    }

    private void createSpellScroll(ItemModelGenerator generator) {
        generator.output.accept(ApostleItems.SPELL_SCROLL,
                ItemModels.select(
                        new SpellScrollProperty(),
                        getSchoolCase(Schools.ABYSSAL, generator),
                        getSchoolCase(Schools.CALLER, generator),
                        getSchoolCase(Schools.GORE, generator),
                        getSchoolCase(Schools.VEX, generator),
                        getSchoolCase(Schools.WASTE, generator),
                        getSchoolCase(Schools.WAVE, generator),
                        getSchoolCase(Schools.WICK, generator),
                        getSchoolCase(Schools.WILD, generator),
                        getSchoolCase(Schools.WIND, generator),
                        getSchoolCase(Schools.WORSHIP, generator)
                )
        );
    }

    private SelectItemModel.SwitchCase<MagicSchool> getSchoolCase(MagicSchool school, ItemModelGenerator generator) {
        return ItemModels.switchCase(
                school,
                ItemModels.basic(getSpellScrollModel(school, generator))
        );
    }

    private Identifier getSpellScrollModel(MagicSchool school, ItemModelGenerator generator) {
        return Models.GENERATED.upload(
                ModelIds.getItemModelId(ApostleItems.SPELL_SCROLL).withSuffixedPath("_" + school.name().getString().toLowerCase()),
                TextureMap.layer0(Apostle.id("item/" + school.name().getString().toLowerCase() + "_scroll")),
                generator.modelCollector
        );
    }
}
