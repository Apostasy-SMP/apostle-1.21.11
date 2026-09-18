package org.apostasy.apostle.core.cca.entity;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Identifier;
import org.apostasy.apostle.core.Apostle;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class BloodlustComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<BloodlustComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("bloodlust"),
            BloodlustComponent.class
    );
    private final PlayerEntity player;

    public static final Identifier STRENGTH_ID = Apostle.id("bloodlust_strength");

    private int duration = 0;
    private int modifier = 0;

    public BloodlustComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        EntityAttributeInstance ATTACK_DAMAGE = player.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE);

        if (ATTACK_DAMAGE != null) {
            if (duration > 0) {
                duration--;
                if (ATTACK_DAMAGE.hasModifier(STRENGTH_ID)) {
                    ATTACK_DAMAGE.removeModifier(STRENGTH_ID);

                    EntityAttributeModifier multiplier = new EntityAttributeModifier(
                            Apostle.id("bloodlust_strength"),
                            modifier,
                            EntityAttributeModifier.Operation.ADD_VALUE
                    );

                    ATTACK_DAMAGE.addTemporaryModifier(multiplier);
                }

                if (duration == 0) {
                    if (ATTACK_DAMAGE.hasModifier(STRENGTH_ID)) {
                        ATTACK_DAMAGE.removeModifier(STRENGTH_ID);
                    }

                    modifier = 0;
                    sync();
                }
            }
        }
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readData(ReadView readView) {
        duration = readView.getInt("Duration", 0);
        modifier = readView.getInt("Modifier", 0);
    }

    public void writeData(WriteView writeView) {
        writeView.putInt("Duration", duration);
        writeView.putInt("Modifier", modifier);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        sync();
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
        sync();
    }
}
