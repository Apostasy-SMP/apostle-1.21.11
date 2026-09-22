package org.apostasy.apostle.core.client.index;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderSetup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.function.Function;

public interface ApostleRenderLayers {

    Function<Identifier, RenderLayer> ENTITY_PYROMANIAC = Util.memoize(tex ->
            RenderLayer.of("pyromaniac_player", RenderSetup.builder(ApostleRenderPipelines.ENTITY_PYROMANIAC)
                    .texture("Sampler0", tex)
                    .build())
    );

    static void init() {}

    static RenderLayer entityPyromaniac(Identifier texture) {
        return ENTITY_PYROMANIAC.apply(texture);
    }
}
