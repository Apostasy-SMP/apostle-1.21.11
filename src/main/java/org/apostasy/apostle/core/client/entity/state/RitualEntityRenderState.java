package org.apostasy.apostle.core.client.entity.state;

import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.item.ItemStack;
import org.apostasy.apostle.api.item.TomeItem;
import org.apostasy.apostle.core.entity.RitualEntity;

import java.util.List;

/**
 * @author Chemthunder
 */
public class RitualEntityRenderState extends EntityRenderState {
    public RitualEntity entity; // way too used to this so fuck you skynotthelimit and your damn render states

    public List<ItemStack> stacksToRender;
    public TomeItem heldTome;
}
