package org.apostasy.apostle.core.index.tag;

import net.acoyt.acornlib.api.builder.TagBuilder;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleEntityTypeTags {
    TagBuilder<EntityType<?>> tag = new TagBuilder<>(Apostle.MOD_ID, RegistryKeys.ENTITY_TYPE);

    TagKey<EntityType<?>> HIDE_OUTLINE = tag.register("conjured");
}
