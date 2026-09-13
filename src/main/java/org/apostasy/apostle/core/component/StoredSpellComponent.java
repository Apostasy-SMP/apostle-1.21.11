package org.apostasy.apostle.core.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.index.ApostleRegistries;

/**
 * @author Chemthunder
 */
public record StoredSpellComponent(Spell spell) {
    public static final Codec<StoredSpellComponent> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            ApostleRegistries.SPELL.getCodec().fieldOf("spell").forGetter(StoredSpellComponent::spell)
    ).apply(codec, StoredSpellComponent::new));

    public static final PacketCodec<ByteBuf, StoredSpellComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
