package org.apostasy.apostle.datagen.providers;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleItems;

import java.util.concurrent.CompletableFuture;

/**
 * @author Chemthunder
 */
public class ApostleLanguageProvider extends FabricLanguageProvider {
    public ApostleLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        ApostleItems.plugin.registerLang(registryLookup, translationBuilder);

        translationBuilder.add("advancements.apostle.cast_ritual.title", "Abracadabra");
        translationBuilder.add("advancements.apostle.cast_ritual.desc", "Cast & create a Ritual for your benefit.");

        translationBuilder.add("advancements.apostle.cast_spell.title", "FUCK JK ROWLING!!");
        translationBuilder.add("advancements.apostle.cast_spell.desc", "Cast a Spell!");

        translationBuilder.add(ApostleEntityTypes.CROW, "Crow");
        translationBuilder.add(ApostleEntityTypes.RITUAL, "Ritual");
    }
}
