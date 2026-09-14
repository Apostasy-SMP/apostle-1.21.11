package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.DataComponentTypeRegistrant;
import net.minecraft.component.ComponentType;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.component.StoredSpellComponent;

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

    static void init() {}
}
