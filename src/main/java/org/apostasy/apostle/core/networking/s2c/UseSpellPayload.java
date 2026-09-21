package org.apostasy.apostle.core.networking.s2c;

import io.netty.buffer.ByteBuf;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.event.SpellHudEvents;

/**
 * @author Chemthunder
 */
public record UseSpellPayload() implements CustomPayload {
    public static final Id<UseSpellPayload> ID = new Id<>(Apostle.id("use_spell"));

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static final PacketCodec<ByteBuf, UseSpellPayload> CODEC = PacketCodec.unit(new UseSpellPayload());

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<UseSpellPayload> {
        public void receive(UseSpellPayload payload, ClientPlayNetworking.Context context) {
            context.client().execute(() -> SpellHudEvents.expansion = 1.0F);
        }
    }
}
