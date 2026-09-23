package org.apostasy.apostle.core.cca.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
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
    private final LivingEntity player;

    private int duration = 0;
    private int modifier = 0;

    public BloodlustComponent(LivingEntity player) {
        this.player = player;
    }

    public void tick() {
        if (duration > 0) {
            duration--;
            if (duration == 0) {
                modifier = 0;
                sync();
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
