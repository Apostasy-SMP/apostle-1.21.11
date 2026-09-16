package org.apostasy.apostle.api.cca;

import com.mojang.serialization.Codec;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jspecify.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
public class DataStoringComponent<T, P> implements AutoSyncedComponent {
    protected final Codec<T> codec;
    protected final P obj;

    private @Nullable T value = null;

    public DataStoringComponent(Codec<T> codec, P obj) {
        this.codec = codec;
        this.obj = obj;
    }

    public void readData(ReadView readView) {
        value = readView.read("Value", codec).orElse(null);
    }

    public void writeData(WriteView writeView) {
        if (value != null) {
            writeView.put("Value", codec, value);
        }
    }

    public @Nullable T getValue() {
        return value;
    }

    public void setValue(@Nullable T value) {
        this.value = value;
    }
}
