package org.apostasy.apostle.core.magic.spell.wind;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.cca.entity.data.ThunderBoltComponent;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.index.magic.Schools;
import org.joml.Quaternionf;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class ThunderstrikeSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        Vec3d spawnPos = caster.raycast(120, 0, true).getPos();

        LightningEntity entity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);

        ThunderBoltComponent.KEY.get(entity).setValue(true);
        ThunderBoltComponent.KEY.get(entity).sync();

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

    public void createChargeParticles(World world, LivingEntity user, int progress) {
        Vec3d spawnPos = user.raycast(120, 0, true).getPos();

        for (int i = 0; i < 10; i++) {
            Random random = new Random();

            float bound = 0.75F;

            world.addImportantParticleClient(
                    new MagicParticleEffect(
                            Schools.WIND.color(),
                            new Quaternionf(
                                    random.nextFloat(-5, 5),
                                    random.nextFloat(-5, 5),
                                    random.nextFloat(-5, 5),
                                    1
                            )
                    ),
                    spawnPos.x + random.nextFloat(-bound, bound),
                    spawnPos.y + random.nextFloat(-bound, bound),
                    spawnPos.z + random.nextFloat(-bound, bound),
                    0,
                    random.nextFloat(1),
                    0
            );
        }
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
