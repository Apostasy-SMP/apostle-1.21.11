package org.apostasy.apostle.core.index;

import com.mojang.serialization.Codec;
import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodecs;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.item.component.StoredSpellComponent;

/**
 * @author Chemthunder
 */
public interface ApostleComponentTypes {
    DataComponentTypeRegistrant plugin = new DataComponentTypeRegistrant(Apostle.MOD_ID);

    ComponentType<StoredSpellComponent> STORED_SPELL = plugin.register("stored_spell",
            StoredSpellComponent.CODEC,
            StoredSpellComponent.PACKET_CODEC
    );

    ComponentType<MagicSchool> SCHOOL = plugin.register("magic_school",
            MagicSchool.CODEC,
            MagicSchool.PACKET_CODEC
    );

    ComponentType<Integer> SCROLL_COOLDOWN = plugin.register("spell_cooldown",
            Codec.INT,
            PacketCodecs.INTEGER
    );

    ComponentType<Integer> ITEM_DURATION = plugin.register("item_duration",
            Codec.INT,
            PacketCodecs.INTEGER
    );

    ComponentType<Boolean> MODIFIER_LIFESTEAL = plugin.register("modifier_lifesteal",
            Codec.BOOL,
            PacketCodecs.BOOLEAN
    );

    static void init() {}
}
