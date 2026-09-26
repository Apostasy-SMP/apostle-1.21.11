package org.apostasy.apostle.core.networking.s2c;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.screen.GrimoireScreen;

/**
 * @author Chemthunder
 */
public record OpenGrimoirePayload(ItemStack stack) implements CustomPayload {
    public static final Id<OpenGrimoirePayload> ID = new Id<>(Apostle.id("open_grimoire"));

    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static final PacketCodec<RegistryByteBuf, OpenGrimoirePayload> CODEC = PacketCodec.tuple(
            ItemStack.PACKET_CODEC, OpenGrimoirePayload::stack,
            OpenGrimoirePayload::new
    );

    public static class Receiver implements ClientPlayNetworking.PlayPayloadHandler<OpenGrimoirePayload> {
        public void receive(OpenGrimoirePayload payload, ClientPlayNetworking.Context context) {
            context.client().execute(() -> context.client().setScreen(new GrimoireScreen(payload.stack)));
        }
    }
}
