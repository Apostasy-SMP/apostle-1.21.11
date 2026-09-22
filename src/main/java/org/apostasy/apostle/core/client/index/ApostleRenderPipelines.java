package org.apostasy.apostle.core.client.index;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gl.RenderPipelines;
import org.apostasy.apostle.core.Apostle;

public interface ApostleRenderPipelines {

    RenderPipeline ENTITY_PYROMANIAC = RenderPipeline.builder(RenderPipelines.ENTITY_EMISSIVE_SNIPPET)
            .withLocation(Apostle.id("pipeline/pyromaniac_player"))
            .withFragmentShader(Apostle.id("core/pyromaniac"))
            .withShaderDefine("ALPHA_CUTOUT", 0.1F)
            .withShaderDefine("PER_FACE_LIGHTING")
            .withSampler("Sampler1")
            .withBlend(BlendFunction.TRANSLUCENT)
            .withCull(false)
            .withDepthWrite(false)
            .build();

    static void init() {}
}
