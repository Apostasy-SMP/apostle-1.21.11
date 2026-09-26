package org.apostasy.apostle.core.index;

import net.acoyt.acornlib.api.registrants.BlockRegistrant;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.core.Apostle;
import org.apostasy.apostle.core.block.AmethystScaleBlock;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chemthunder
 */
public interface ApostleBlocks {
    BlockRegistrant plugin = new BlockRegistrant(Apostle.MOD_ID);
    List<Block> SCALES = new ArrayList<>();

    Block AMETHYST_SCALE_BLOCK = plugin.registerWithItem("amethyst_scale_block", Block::new, AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK));

    Block WASTE_SCALE = createScales(Schools.WASTE);
    Block WAVE_SCALE = createScales(Schools.WAVE);
    Block WICK_SCALE = createScales(Schools.WICK);
    Block WILD_SCALE = createScales(Schools.WILD);
    Block WIND_SCALE = createScales(Schools.WIND);
    Block WORSHIP_SCALE = createScales(Schools.WORSHIP);

    Block ABYSSAL_SCALE = createScales(Schools.ABYSSAL);
    Block CALLER_SCALE = createScales(Schools.CALLER);
    Block GORE_SCALE = createScales(Schools.GORE);
    Block VEX_SCALE = createScales(Schools.VEX);
    Block APOCALYPTIC_SCALE = createScales(Schools.APOCALYPTIC);

    private static Block createScales(MagicSchool school) {
        Block block = plugin.registerWithItem(school.name().getString().toLowerCase() + "_scale_block", (settings -> new AmethystScaleBlock(settings, school)), AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
                .emissiveLighting(((state, world, pos) -> true))
                .luminance((value -> 4))
        );

        SCALES.add(block);
        return block;
    }

    static void init() {}
}
