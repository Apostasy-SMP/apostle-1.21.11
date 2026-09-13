package org.apostasy.apostle.api.magic;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;

/**
 * @author Chemthunder
 */
public record MagicSchool(Text name, int color) {
    public static final Codec<MagicSchool> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            TextCodecs.CODEC.fieldOf("name").forGetter(MagicSchool::name),
            Codec.INT.fieldOf("color").forGetter(MagicSchool::color)
    ).apply(codec, MagicSchool::new));

    public static final PacketCodec<ByteBuf, MagicSchool> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
