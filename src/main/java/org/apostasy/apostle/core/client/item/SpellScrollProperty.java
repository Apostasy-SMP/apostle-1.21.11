package org.apostasy.apostle.core.client.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.item.SpellScrollItem;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.component.StoredSpellComponent;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class SpellScrollProperty implements SelectProperty<MagicSchool> {
    public static final MapCodec<SpellScrollProperty> CODEC = MapCodec.unit(SpellScrollProperty::new);
    public static final Identifier ID = Apostle.id("spell_scroll");

    public static final Type<SpellScrollProperty, MagicSchool> TYPE = Type.create(
            CODEC,
            MagicSchool.CODEC
    );

    public @Nullable MagicSchool getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ItemDisplayContext displayContext) {
        if (stack.getItem() instanceof SpellScrollItem) {
            if (stack.contains(ApostleComponentTypes.STORED_SPELL)) {
                StoredSpellComponent component = stack.get(ApostleComponentTypes.STORED_SPELL);

                if (component != null) {
                    return component.spell().getMagicSchool();
                }
            }
        }
        return null;
    }

    public Codec<MagicSchool> valueCodec() {
        return MagicSchool.CODEC;
    }

    public Type<? extends SelectProperty<MagicSchool>, MagicSchool> getType() {
        return TYPE;
    }
}
