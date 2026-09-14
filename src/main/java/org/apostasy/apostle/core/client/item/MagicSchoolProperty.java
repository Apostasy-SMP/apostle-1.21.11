package org.apostasy.apostle.core.client.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.render.item.property.select.SelectProperty;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemDisplayContext;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleComponentTypes;
import org.jspecify.annotations.Nullable;

/**
 * @author Chemthunder
 */
public class MagicSchoolProperty implements SelectProperty<MagicSchool> {
    public static final MapCodec<MagicSchoolProperty> CODEC = MapCodec.unit(MagicSchoolProperty::new);
    public static final Identifier ID = Apostle.id("magic_school");

    public static final Type<MagicSchoolProperty, MagicSchool> TYPE = Type.create(
            CODEC,
            MagicSchool.CODEC
    );

    public @Nullable MagicSchool getValue(ItemStack stack, @Nullable ClientWorld world, @Nullable LivingEntity user, int seed, ItemDisplayContext displayContext) {
        if (stack.contains(ApostleComponentTypes.SCHOOL)) {
            return stack.get(ApostleComponentTypes.SCHOOL);
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
