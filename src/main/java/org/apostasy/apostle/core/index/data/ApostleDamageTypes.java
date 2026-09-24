package org.apostasy.apostle.core.index.data;

import net.acoyt.acornlib.api.builder.KeyedBuilder;
import net.acoyt.acornlib.api.util.DataUtils;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleDamageTypes {
    KeyedBuilder<DamageType> plugin = new KeyedBuilder<>(Apostle.MOD_ID, RegistryKeys.DAMAGE_TYPE);

    RegistryKey<DamageType> ELECTRIFIED = plugin.register("elecrtrified", new DamageType("electrified", 3.0F));
    RegistryKey<DamageType> WATERBOLT = plugin.register("waterbolt", new DamageType("waterbolt", 2.5F));

    static void translate(RegistryWrapper.WrapperLookup wrapperLookup, FabricLanguageProvider.TranslationBuilder translationBuilder) {
        DataUtils.registerDamageType(
                translationBuilder,
                ELECTRIFIED,
                "%1$s was electrified",
                "%1$s was electrified whilst fighting %2$s, wielding %3$s",
                "%1$s was electrified whilst fighting %2$s"
        );

        DataUtils.registerDamageType(
                translationBuilder,
                WATERBOLT,
                "%1$s was drowned from afar",
                "%1$s was drowned from afar by %2$s, wielding %3$s",
                "%1$s was drowned from afar by %3$s"
        );
    }
}
