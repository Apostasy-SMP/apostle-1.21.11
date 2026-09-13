package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.EntityTypeRegistrant;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricTrackedDataRegistry;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.client.entity.render.RitualEntityRenderer;
import org.apostasy.apostle.core.entity.RitualEntity;

/**
 * @author Chemthunder
 */
public interface ApostleEntityTypes {
    EntityTypeRegistrant plugin = new EntityTypeRegistrant(Apostle.MOD_ID);

    EntityType<RitualEntity> RITUAL = plugin.register("ritual", EntityType.Builder.<RitualEntity>create(
            RitualEntity::new,
            SpawnGroup.MISC
    ).dimensions(3.0F, 1.2F));

    static void init() {
        FabricTrackedDataRegistry.register(Apostle.id("item_stack_list"), Apostle.ITEM_STACK_LIST);
    }

    static void clinit() {
        EntityRendererFactories.register(RITUAL, RitualEntityRenderer::new);
    }
}
