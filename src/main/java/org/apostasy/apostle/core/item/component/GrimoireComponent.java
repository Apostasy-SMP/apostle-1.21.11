package org.apostasy.apostle.core.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public record GrimoireComponent(List<ItemStack> list) {
    public static final GrimoireComponent DEFAULT = new GrimoireComponent(new ArrayList<>());

    public static final Codec<GrimoireComponent> CODEC = RecordCodecBuilder.create(codec -> codec.group(
            ItemStack.CODEC.listOf().optionalFieldOf("list", new ArrayList<>()).forGetter(GrimoireComponent::list)
    ).apply(codec, GrimoireComponent::new));

    public static final PacketCodec<ByteBuf, GrimoireComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
