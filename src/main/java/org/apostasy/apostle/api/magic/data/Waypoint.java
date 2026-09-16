package org.apostasy.apostle.api.magic.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public record Waypoint(RegistryKey<World> dimension, BlockPos position) {
    public static final Codec<Waypoint> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            World.CODEC.fieldOf("dimension").forGetter(Waypoint::dimension),
            BlockPos.CODEC.fieldOf("position").forGetter(Waypoint::position)
    ).apply(codec, Waypoint::new));

    public static final PacketCodec<ByteBuf, Waypoint> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
