package org.apostasy.apostle.core.magic.spell.wick;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.cca.entity.tick.PyromaniacComponent;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

public class PyromaniacSpell implements Spell {

    @Override
    public void cast(World world, LivingEntity caster) {
        PyromaniacComponent component = PyromaniacComponent.get(caster);
        component.trigger();
    }

    @Override
    public List<Item> getIngredients() {
        return List.of(
                Items.FIRE_CHARGE,
                Items.BLAZE_ROD,
                Items.BLAZE_POWDER,
                Items.BLACKSTONE
        );
    }

    @Override
    public MagicSchool getMagicSchool() {
        return Schools.WICK;
    }

    @Override
    public String getName() {
        return "Pyromaniac";
    }

    @Override
    public int getCastTime() {
        return 12 * 20; // 12 seconds
    }

    @Override
    public int getCooldown() {
        return 600 * 20; // 600 seconds
    }
}
