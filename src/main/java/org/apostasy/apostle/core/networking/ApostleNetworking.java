package org.apostasy.apostle.core.networking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.apostasy.apostle.core.networking.s2c.OpenGrimoirePayload;
import org.apostasy.apostle.core.networking.s2c.UseSpellPayload;

/**
 * @author Chemthunder
 */
public interface ApostleNetworking {
    static void init() {
        PayloadTypeRegistry.playS2C().register(UseSpellPayload.ID, UseSpellPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenGrimoirePayload.ID, OpenGrimoirePayload.CODEC);
    }

    static void c2s() {}

    @Environment(EnvType.CLIENT)
    static void s2c() {
        ClientPlayNetworking.registerGlobalReceiver(UseSpellPayload.ID, new UseSpellPayload.Receiver());
        ClientPlayNetworking.registerGlobalReceiver(OpenGrimoirePayload.ID, new OpenGrimoirePayload.Receiver());
    }
}
