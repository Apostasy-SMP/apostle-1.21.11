package org.apostasy.apostle.core.cca.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.dynamic.Codecs;
import org.apostasy.apostle.core.Apostle;
import org.jspecify.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

/**
 * @author Chemthunder
 */
public class TransComponent implements AutoSyncedComponent, CommonTickingComponent {
    public static final ComponentKey<TransComponent> KEY = ComponentRegistry.getOrCreate(
            Apostle.id("trans"),
            TransComponent.class
    );
    private final PlayerEntity player;

    private @Nullable GameProfile profile = null;
    private int duration = 0;

    public TransComponent(PlayerEntity player) {
        this.player = player;
    }

    public void tick() {
        if (profile != null) {
            if (duration > 0) {
                duration--;
                if (duration == 0) {
                    profile = null;
                    sync();
                }
            }
        } else {
            if (duration > 0) {
                duration = 0;
                sync();
            }
        }
    }

    public void sync() {
        KEY.sync(player);
    }

    public void readData(ReadView readView) {
        profile = readView.read("Profile", Codecs.GAME_PROFILE_CODEC).orElse(null);
    }

    public void writeData(WriteView writeView) {
        if (profile != null) {
            writeView.put("Profile", Codecs.GAME_PROFILE_CODEC, profile);
        }
    }

    @Nullable
    public GameProfile getProfile() {
        return profile;
    }

    public void setProfile(GameProfile profile) {
        this.profile = profile;
        sync();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        sync();
    }
}
