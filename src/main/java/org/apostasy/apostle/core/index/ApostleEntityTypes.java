package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.render.CallerVexEntityRenderer;
import org.apostasy.apostle.core.client.entity.render.RitualEntityRenderer;
import org.apostasy.apostle.core.entity.RitualEntity;
import org.apostasy.apostle.core.entity.spell.CallerVexEntity;

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

    static void init() {
        FabricTrackedDataRegistry.register(Apostle.id("item_stack_list"), Apostle.ITEM_STACK_LIST);

        FabricDefaultAttributeRegistry.register(CALLER_VEX, CallerVexEntity.createAttributes());
    }

    static void clinit() {
        EntityRendererFactories.register(RITUAL, RitualEntityRenderer::new);

        EntityRendererFactories.register(CALLER_VEX, CallerVexEntityRenderer::new);
    }
}
