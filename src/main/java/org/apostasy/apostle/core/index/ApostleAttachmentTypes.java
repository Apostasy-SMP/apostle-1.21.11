package org.apostasy.apostle.core.index;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.fabricmc.fabric.impl.attachment.AttachmentRegistryImpl;
import net.minecraft.entity.LazyEntityReference;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import org.apostasy.apostle.core.Apostle;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
@SuppressWarnings("UnstableApiUsage")
public interface ApostleAttachmentTypes {
    AttachmentType<Boolean> IS_ARCHMAGE = register(
            "is_archmage",
            builder -> builder
                    .syncWith(PacketCodecs.BOOLEAN, AttachmentSyncPredicate.all())
                    .persistent(Codec.BOOL)
                    .initializer(() -> false)
    );

    AttachmentType<LazyEntityReference<LivingEntity>> OWNER = register("owner",
            builder -> builder
                    .persistent(LazyEntityReference.createCodec())
                    .syncWith(LazyEntityReference.createPacketCodec(), AttachmentSyncPredicate.all())
    );

    static void init() {}

    static <T> AttachmentType<T> register(String name, Consumer<AttachmentRegistry.Builder<T>> consumer) {
        return AttachmentRegistry.create(Apostle.id(name), consumer);
    }
}
