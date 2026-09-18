package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.render.CallerVexEntityRenderer;
import org.apostasy.apostle.core.client.entity.render.HolyNetEntityRenderer;
import org.apostasy.apostle.core.client.entity.render.CrowEntityRenderer;
import org.apostasy.apostle.core.client.entity.render.RitualEntityRenderer;
import org.apostasy.apostle.core.entity.CallerVexEntity;
import org.apostasy.apostle.core.entity.HolyNetEntity;
import org.apostasy.apostle.core.entity.ParticleEntity;
import org.apostasy.apostle.core.entity.CrowEntity;
import org.apostasy.apostle.core.entity.RitualEntity;

/**
 * @author Chemthunder
 */
@SuppressWarnings("DataFlowIssue")
public interface ApostleEntityTypes {
    EntityTypeRegistrant plugin = new EntityTypeRegistrant(Apostle.MOD_ID);

    EntityType<RitualEntity> RITUAL = plugin.register("ritual", EntityType.Builder.<RitualEntity>create(
            RitualEntity::new,
            SpawnGroup.MISC
    ).dimensions(3.0F, 1.2F));

    EntityType<CallerVexEntity> CALLER_VEX = plugin.register("caller_vex", EntityType.Builder.create(
            CallerVexEntity::new,
            SpawnGroup.MISC
    ).dimensions(0.4F, 0.8F).disableSummon());

    EntityType<CrowEntity> CROW = plugin.register("crow", EntityType.Builder.create(
            CrowEntity::new,
            SpawnGroup.CREATURE
    ).dimensions(0.6F, 0.6F).eyeHeight(0.37F));

    EntityType<ParticleEntity> PARTICLE_DAMAGE = plugin.register("particle_damage", EntityType.Builder.create(
            ParticleEntity::new,
            SpawnGroup.MISC
    ).dimensions(1.0F, 1.0F).disableSummon());

    EntityType<HolyNetEntity> HOLY_NET = plugin.register("holy_net", EntityType.Builder.create(
            HolyNetEntity::new,
            SpawnGroup.MISC
    ).dimensions(0.3F, 0.3F).makeFireImmune());

    static void init() {
        FabricDefaultAttributeRegistry.register(CALLER_VEX, CallerVexEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(CROW, CrowEntity.createAttributes());
    }

    static void clinit() {
        EntityRendererFactories.register(RITUAL, RitualEntityRenderer::new);
        EntityRendererFactories.register(CALLER_VEX, CallerVexEntityRenderer::new);
        EntityRendererFactories.register(PARTICLE_DAMAGE, EmptyEntityRenderer::new);
        EntityRendererFactories.register(HOLY_NET, HolyNetEntityRenderer::new);

        EntityRendererFactories.register(CROW, CrowEntityRenderer::new);
    }
}
