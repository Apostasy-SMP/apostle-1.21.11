package org.apostasy.apostle.core.magic.spell.worship;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.apostasy.apostle.api.magic.MagicSchool;
import org.apostasy.apostle.api.magic.Spell;
import org.apostasy.apostle.core.client.particle.MagicParticleEffect;
import org.apostasy.apostle.core.entity.HolyNetEntity;
import org.apostasy.apostle.core.index.ApostleEntityTypes;
import org.apostasy.apostle.core.index.magic.Schools;
import org.joml.Quaternionf;

import java.util.List;
import java.util.Random;

/**
 * @author Chemthunder
 */
public class HolyNetSpell implements Spell {
    public void cast(World world, LivingEntity caster) {
        HolyNetEntity net = new HolyNetEntity(ApostleEntityTypes.HOLY_NET, world);
        net.setPosition(caster.getX(), caster.getY() + 1.0F, caster.getZ());
        net.setSize(0.0F);
        world.spawnEntity(net);

        for (int i = 0; i < 20; i++) {
            Random random = new Random();

            float widthBound = 2.0F;
            float heightBound = 0.3F;

            world.addImportantParticleClient(
                    new MagicParticleEffect(
                            Schools.WORSHIP.color(),
                            new Quaternionf(0, 0, 0, 0)
                    ),
                    caster.getX() + random.nextFloat(-widthBound, widthBound),
                    caster.getY() + random.nextFloat(-heightBound * 2, heightBound),
                    caster.getZ() + random.nextFloat(-widthBound, widthBound),
                    0,
                    0.5F + random.nextFloat(0.0F, 0.5F),
                    0
            );
        }
    }

    public List<Item> getIngredients() {
        return List.of(
                Items.LIGHTNING_ROD,
                Items.TRIDENT,
                Items.NETHER_STAR,
                Items.IRON_SWORD
        );
    }

    public MagicSchool getMagicSchool() {
        return Schools.WORSHIP;
    }

    public String getName() {
        return "Holy Net";
    }

    public int getCastTime() {
        return (2 * 20);
    }

    public int getCooldown() {
        return (50 * 20);
    }
}
