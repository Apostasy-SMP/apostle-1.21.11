package org.apostasy.apostle.core.index;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.utilities.ExtraCodecs;

import java.util.List;

/**
 * @author Chemthunder
 */
public interface ApostleTrackedData {
    TrackedDataHandler<List<ItemStack>> ITEM_STACK_LIST = TrackedDataHandler.create(ItemStack.OPTIONAL_LIST_PACKET_CODEC);
    TrackedDataHandler<RegistryKey<DamageType>> DAMAGE_TYPE_KEY = TrackedDataHandler.create(ExtraCodecs.DAMAGE_TYPE_REGISTRY_KEY_PACKET_CODEC);
    TrackedDataHandler<ParticleEffect> PARTICLE_EFFECT = TrackedDataHandler.create(ParticleTypes.PACKET_CODEC);
    TrackedDataHandler<byte[]> BYTE_LIST = TrackedDataHandler.create(PacketCodecs.BYTE_ARRAY);

    static void init() {
        FabricTrackedDataRegistry.register(Apostle.id("item_stack_list"), ITEM_STACK_LIST);
        FabricTrackedDataRegistry.register(Apostle.id("damage_type_registry_key"), DAMAGE_TYPE_KEY);
        FabricTrackedDataRegistry.register(Apostle.id("particle_effect"), PARTICLE_EFFECT);
        FabricTrackedDataRegistry.register(Apostle.id("byte_list"), BYTE_LIST);
    }
}
