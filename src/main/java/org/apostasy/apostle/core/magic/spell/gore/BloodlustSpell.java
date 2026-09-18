package org.apostasy.apostle.core.magic.spell.gore;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.cca.entity.BloodlustComponent;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class BloodlustSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        BloodlustComponent lust = BloodlustComponent.KEY.get(caster);

        lust.setDuration(getCooldown());
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.IRON_SWORD,
                Items.PHANTOM_MEMBRANE,
                Items.ROTTEN_FLESH,
                Items.SHIELD,
                Items.GOLDEN_APPLE
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.GORE;
    }

    public String getName() {
        return "Bloodlust";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (40 * 20);
    }
}
