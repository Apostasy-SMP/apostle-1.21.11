package org.apostasy.apostle.datagen.providers.client;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.index.ApostleBlocks;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.ApostleItems;
import org.apostasy.apostle.core.index.data.ApostleDamageTypes;

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
        ApostleBlocks.plugin.registerLang(registryLookup, translationBuilder);

        ApostleDamageTypes.translate(registryLookup, translationBuilder);

        translationBuilder.add("advancements.apostle.cast_ritual.title", "Abracadabra");
        translationBuilder.add("advancements.apostle.cast_ritual.desc", "Cast & create a Ritual for your benefit.");

        translationBuilder.add("advancements.apostle.cast_spell.title", "FUCK JK ROWLING!!");
        translationBuilder.add("advancements.apostle.cast_spell.desc", "Cast a Spell!");

        translationBuilder.add("advancements.apostle.eat_dust.title", "Do you Feel the Magic?");
        translationBuilder.add("advancements.apostle.eat_dust.desc", "Inhale some Magic Dust, and feel the magic coursing through your veins... literally.");

        translationBuilder.add(ApostleEntityTypes.CROW, "Crow");
        translationBuilder.add(ApostleEntityTypes.RITUAL, "Ritual");
    }
}
