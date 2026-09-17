package org.apostasy.apostle.core.index;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.math.BlockPos;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleAttachmentTypes {
    AttachmentType<Boolean> IS_ARCHMAGE = AttachmentRegistry.create(
            Apostle.id("is_archmage"),
            builder -> builder
                    .syncWith(PacketCodecs.BOOLEAN, AttachmentSyncPredicate.all())
                    .persistent(Codec.BOOL)
                    .initializer(() -> false)
                    .buildAndRegister(Apostle.id("is_archmage"))
    );

    static void init() {}
}
