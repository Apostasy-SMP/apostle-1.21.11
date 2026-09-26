package org.apostasy.apostle.core.client.event;

import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import org.apostasy.apostle.api.client.event.UpdateRenderStateCallback;
import org.apostasy.apostle.core.entity.CallerVexEntity;
import org.apostasy.apostle.core.index.ApostleAttachmentTypes;
import org.apostasy.apostle.core.index.magic.Schools;

/**
 * @author Chemthunder
 */
public class UndeadOutlineColorEvent implements UpdateRenderStateCallback {
    public void updateRenderState(LivingEntity living, LivingEntityRenderState renderState) {
        if (living.getType().isIn(EntityTypeTags.UNDEAD)) {
            if (living.getAttached(ApostleAttachmentTypes.OWNER) != null) {
                renderState.outlineColor = Schools.NECROTIC.color();
            }
        }

        if (living instanceof CallerVexEntity) {
            if (living.getAttached(ApostleAttachmentTypes.OWNER) != null) {
                renderState.outlineColor = Schools.CALLER.color();
            }
        }
    }
}
