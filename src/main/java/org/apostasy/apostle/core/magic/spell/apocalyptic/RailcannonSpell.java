package org.apostasy.apostle.core.magic.spell.apocalyptic;

import dev.rbn.bleedylib.api.screenshake.ScreenshakeHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.attribute.timeline.EasingType;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.entity.ParticleEntity;
import org.apostasy.apostle.core.index.magic.Schools;

import java.util.List;

/**
 * @author Chemthunder
 */
public class RailcannonSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d ray = caster.raycast(1.3, 0, true).getPos();

        ParticleEntity par = ParticleEntity.create(world, DamageTypes.DRAGON_BREATH, ParticleTypes.SONIC_BOOM, 15);
        par.setAmountOfSpawn(1);
        par.setOwner(caster);
        par.setPosition(ray.x, ray.y, ray.z);
        par.setVelocity(caster, caster.getPitch(), caster.getYaw(), 0, 3, 0);
        world.spawnEntity(par);

        ScreenshakeHandler.screenshakeAroundPoint(
                world,
                20,
                1.4F,
                20,
                ray,
                EasingType.OUT_EXPO
        );
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.CROSSBOW,
                Items.CROSSBOW,
                Items.NETHERITE_SWORD,
                Items.NETHER_STAR
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.APOCALYPTIC;
    }

    public String getName() {
        return "Railcannon";
    }

    public int getCastTime() {
        return (3 * 20);
    }

    public int getCooldown() {
        return (120 * 20);
    }
}
