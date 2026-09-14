package org.apostasy.apostle.core.index;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.util.math.BlockPos;
import org.apostasy.apostle.core.Apostle;

/**
 * @author Chemthunder
 */
public interface ApostleAttachmentTypes {
    AttachmentType<BlockPos> WAYPOINT = AttachmentRegistry.create(
            Apostle.id("waypoint"),
            builder -> builder
                    .syncWith(BlockPos.PACKET_CODEC, AttachmentSyncPredicate.all())
                    .persistent(BlockPos.CODEC)
                    .initializer(() -> new BlockPos(0, 0, 0))
                    .buildAndRegister(Apostle.id("waypoint"))
    );

    static void init() {}
}
