package org.apostasy.apostle.core.index;

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Ritual;
import org.apostasy.apostle.api.magic.RitualRecipe;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleRegistries {
    RegistryKey<Registry<MagicSchool>> MAGIC_SCHOOL_KEY = RegistryKey.ofRegistry(Apostle.id("magic_school"));
    Registry<MagicSchool> MAGIC_SCHOOL = FabricRegistryBuilder.createSimple(MAGIC_SCHOOL_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    RegistryKey<Registry<Spell>> SPELL_KEY = RegistryKey.ofRegistry(Apostle.id("spell"));
    Registry<Spell> SPELL = FabricRegistryBuilder.createSimple(SPELL_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    RegistryKey<Registry<Ritual>> RITUAL_KEY = RegistryKey.ofRegistry(Apostle.id("ritual"));
    Registry<Ritual> RITUAL = FabricRegistryBuilder.createSimple(RITUAL_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    RegistryKey<Registry<RitualRecipe>> RITUAL_RECIPE_KEY = RegistryKey.ofRegistry(Apostle.id("ritual_recipe"));
    Registry<RitualRecipe> RITUAL_RECIPE = FabricRegistryBuilder.createSimple(RITUAL_RECIPE_KEY)
            .attribute(RegistryAttribute.MODDED)
            .buildAndRegister();

    static void init() {}
}
