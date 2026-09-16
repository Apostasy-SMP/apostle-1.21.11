package org.apostasy.apostle.core.magic.spell.wind;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.cca.entity.data.ThunderBoltComponent;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class ThunderstrikeSpell implements Spell {
    public void cast(World world, PlayerEntity caster) {
        Vec3d spawnPos = caster.raycast(120, 0, true).getPos();

        LightningEntity entity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);

        ThunderBoltComponent.KEY.get(entity).setValue(true);

        entity.setPosition(spawnPos);

        world.spawnEntity(entity);
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.LIGHTNING_ROD,
                Items.COPPER_INGOT,
                Items.COPPER_INGOT,
                Items.PAPER
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WIND;
    }

    public String getName() {
        return "Thunderstrike";
    }

    public int getCastTime() {
        return (4 * 20);
    }

    public int getCooldown() {
        return (35 * 20);
    }
}
