package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.ParticleTypeRegistrant;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.client.particle.MagicParticleType;

/**
 * @author Chemthunder
 */
public interface ApostleParticleTypes {
    ParticleTypeRegistrant plugin = new ParticleTypeRegistrant(Apostle.MOD_ID);

    ParticleType<MagicParticleEffect> MAGIC_EFFECT = plugin.register("magic", FabricParticleTypes.complex(true, MagicParticleEffect.CODEC, MagicParticleEffect.PACKET_CODEC));

    SimpleParticleType ROOT = plugin.register("root", FabricParticleTypes.simple(true));
    SimpleParticleType WAVE = plugin.register("wave", FabricParticleTypes.simple(true));

    static void init() {}

    static void clinit() {
        ParticleFactoryRegistry.getInstance().register(MAGIC_EFFECT, MagicParticleType.Factory::new);
        ParticleFactoryRegistry.getInstance().register(ROOT, EndRodParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(WAVE, EndRodParticle.Factory::new);
    }
}
