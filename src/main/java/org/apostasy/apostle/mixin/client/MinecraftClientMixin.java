package org.apostasy.apostle.mixin.client;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.Ownable;
import net.minecraft.entity.Tameable;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.tag.ApostleEntityTypeTags;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Objects;

/**
 * @author Chemthunder
 */
@Mixin(value = MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @WrapMethod(method = "hasOutline")
    private boolean apostle$distinguishOwnedAssMobs(Entity entity, Operation<Boolean> original) {
        if (!entity.getType().isIn(ApostleEntityTypeTags.HIDE_OUTLINE)) {
            if (entity instanceof Ownable ownable && !(entity instanceof Tameable)) {
                if (ownable.getOwner() == MinecraftClient.getInstance().player) {
                    return true;
                }
            }

            if (entity.getAttached(ApostleAttachmentTypes.OWNER) != null) {
                if (Objects.equals(entity.getAttached(ApostleAttachmentTypes.OWNER), LazyEntityReference.of(MinecraftClient.getInstance().player))) {
                    return true;
                }
            }
        }
        return original.call(entity);
    }
}
