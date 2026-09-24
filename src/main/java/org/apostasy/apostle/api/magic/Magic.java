package org.apostasy.apostle.api.magic;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.apostasy.apostle.core.index.ApostleRegistries;

/**
 * @author Chemthunder
 */
public class Magic {
    public static final Codec<Spell> SPELL_CODEC = ApostleRegistries.SPELL.getCodec();
    public static final PacketCodec<ByteBuf, Spell> SPELL_PACKET_CODEC = PacketCodecs.codec(SPELL_CODEC);

    public static final Codec<Ritual> RITUAL_CODEC = ApostleRegistries.RITUAL.getCodec();
    public static final PacketCodec<ByteBuf, Ritual> RITUAL_PACKET_CODEC = PacketCodecs.codec(RITUAL_CODEC);
}
