package org.apostasy.apostle.core.item;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * @author Chemthunder
 */
public abstract class ArtifactItem extends Item {
    public ArtifactItem(Settings settings) {
        super(settings);
    }

    @Nullable
    protected LivingEntity selfOrAlly(LivingEntity user) {
        LivingEntity target = null;
        if (MinecraftClient.getInstance().targetedEntity != null) {
            if (MinecraftClient.getInstance().targetedEntity instanceof LivingEntity living) target = living;
        } else {
            target = user;
        }
        return target;
    }

    public abstract List<Item> getIngredients();

    public abstract MagicSchool getSchool();
}
