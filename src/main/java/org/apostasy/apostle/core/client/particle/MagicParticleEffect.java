package org.apostasy.apostle.core.client.particle;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.util.dynamic.Codecs;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.index.ApostleParticleTypes;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;

/**
 * @author Chemthunder
 */
public record MagicParticleEffect(int color, Quaternionfc rotation) implements ParticleEffect {
    public MagicParticleEffect(MagicSchool school) {
        this(school.color(), new Quaternionf(0, 0, 0, 0));
    }

    public MagicParticleEffect(int color) {
        this(color, new Quaternionf(0, 0, 0, 0));
    }

    public static final MapCodec<MagicParticleEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codecs.RGB.fieldOf("color").forGetter(MagicParticleEffect::color),
            Codecs.QUATERNION_F.fieldOf("rotation").forGetter(MagicParticleEffect::rotation)
    ).apply(instance, MagicParticleEffect::new));

    public static final PacketCodec<RegistryByteBuf, MagicParticleEffect> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.INTEGER, MagicParticleEffect::color,
            PacketCodecs.QUATERNION_F, MagicParticleEffect::rotation,
            MagicParticleEffect::new
    );

    public ParticleType<?> getType() {
        return ApostleParticleTypes.MAGIC_EFFECT;
    }
}
