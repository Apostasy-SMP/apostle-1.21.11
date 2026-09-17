package org.apostasy.apostle.core.utilities;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

/**
 * @author Chemthunder
 */
public class ExtraCodecs {
    public static final PacketCodec<ByteBuf, RegistryKey<DamageType>> DAMAGE_TYPE_REGISTRY_KEY_PACKET_CODEC = RegistryKey.createPacketCodec(RegistryKeys.DAMAGE_TYPE);
}
