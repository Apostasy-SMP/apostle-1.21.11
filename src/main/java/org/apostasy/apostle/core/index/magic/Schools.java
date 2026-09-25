package org.apostasy.apostle.core.index.magic;

import net.acoyt.acornlib.api.util.MiscUtils;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.index.ApostleRegistries;

/**
 * @author Chemthunder
 */
public interface Schools {
    MagicSchool NONE = register("none", 0xFFffffff);

    MagicSchool WASTE = register("waste", 0xFF8b6965);
    MagicSchool WAVE = register("wave", 0xFF63d4df);
    MagicSchool WICK = register("wick", 0xFFee8632);
    MagicSchool WILD = register("wild", 0xFFa8d15b);
    MagicSchool WIND = register("wind", 0xFFbdc9ff);
    MagicSchool WORSHIP = register("worship", 0xFFfdf8a1);

    MagicSchool ABYSSAL = register("abyssal", 0xFF7ac39a);
    MagicSchool CALLER = register("caller", 0xFFb76693);
    MagicSchool GORE = register("gore", 0xFFde4b57);
    MagicSchool VEX = register("vex", 0xFFcd9840);
    MagicSchool APOCALYPTIC = register("apocalyptic", 0xFF6d559a);

    private static MagicSchool register(String name, int color) {
        return Registry.register(ApostleRegistries.MAGIC_SCHOOL, Apostle.id(name), new MagicSchool(Text.literal(MiscUtils.formatString(name)), color));
    }

    static void init() {}
}
