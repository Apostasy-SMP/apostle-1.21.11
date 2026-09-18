package org.apostasy.apostle.api.cca;

import com.mojang.serialization.Codec;
import net.minecraft.entity.Entity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jspecify.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

/**
 * @author Chemthunder
 */
@SuppressWarnings("unused")
public class DataStoringComponent<ValueType, ComponentTarget extends Entity, ComponentClass extends Component> implements AutoSyncedComponent {
    protected final Codec<ValueType> codec;
    protected final ComponentKey<ComponentClass> key;
    protected final ComponentTarget obj;

    private @Nullable ValueType value = null;

    public DataStoringComponent(Codec<ValueType> codec, ComponentKey<ComponentClass> key, ComponentTarget obj) {
        this.codec = codec;
        this.key = key;
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

    public void sync() {
        this.key.sync(this.obj);
    }

    @Nullable
    public ValueType getValue() {
        return value;
    }

    public void setValue(ValueType value) {
        this.value = value;
    }

    public ComponentKey<ComponentClass> getKey() {
        return key;
    }
}
