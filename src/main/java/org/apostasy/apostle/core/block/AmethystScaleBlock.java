package org.apostasy.apostle.core.block;

import net.minecraft.block.Block;
import org.apostasy.apostle.api.magic.MagicSchool;

/**
 * @author Chemthunder
 */
public class AmethystScaleBlock extends Block {
    private final MagicSchool school;

    public AmethystScaleBlock(Settings settings, MagicSchool school) {
        super(settings);
        this.school = school;
    }

    public MagicSchool getSchool() {
        return school;
    }
}
