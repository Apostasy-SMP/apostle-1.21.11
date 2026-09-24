package org.apostasy.apostle.datagen.providers.client;

import net.acoyt.acornlib.data.provider.resources.AcornParticleGen;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleParticleTypes;

/**
 * @author Chemthunder
 */
public class ApostleParticleProvider extends AcornParticleGen {
    public ApostleParticleProvider(FabricDataOutput output) {
        super(output);
    }

    public void generate(ParticleDataConsumer consumer) {
        consumer.accept(ApostleParticleTypes.MAGIC_EFFECT, Apostle.id("magic"));

        consumer.accept(ApostleParticleTypes.ROOT, Apostle.id("wild_particle_1"));
        consumer.accept(ApostleParticleTypes.WAVE, rangeBetween(Apostle.id("wave"), 0, 2));
    }
}
