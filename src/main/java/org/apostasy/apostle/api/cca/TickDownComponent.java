package org.apostasy.apostle.api.cca;

import net.minecraft.entity.Entity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.CommonTickingComponent;

import java.util.function.Consumer;

/**
 * @author Chemthunder
 */
public abstract class TickDownComponent<ComponentTarget extends Entity, ComponentClass extends Component> implements AutoSyncedComponent, CommonTickingComponent {
    protected final ComponentKey<ComponentClass> key;
    protected final ComponentTarget obj;
    protected final Consumer<ComponentTarget> consumer;

    protected final int time;

    protected int tickDown;
    protected int tickUp;

    protected boolean active = false;

    public TickDownComponent(ComponentKey<ComponentClass> key, ComponentTarget obj, int time, Consumer<ComponentTarget> consumer) {
        this.key = key;
        this.obj = obj;
        this.consumer = consumer;

        this.time = time;

        this.tickDown = time;
        this.tickUp = 0;
    }

    public void tick() {
        if (active) {
            if (tickDown > 0) {
                tickDown--;
                tickUp++;
                if (tickDown == 0) {
                    active = false;
                    tickDown = time;
                    tickUp = 0;

                    consumer.accept(obj);
                    sync();
                }
            }
        }
    }

    public void sync() {
        key.sync(obj);
    }

    public void readData(ReadView readView) {
        active = readView.getBoolean(format("Active"), false);

        tickDown = readView.getInt(format("TickDown"), this.time);
        tickUp = readView.getInt(format("TickUp"), 0);
    }

    public void writeData(WriteView writeView) {
        writeView.putBoolean(format("Active"), active);

        writeView.putInt(format("TickDown"), tickDown);
        writeView.putInt(format("TickUp"), tickUp);
    }

    protected String format(String input) {
        return this.key.getId().getPath() + "#" + input;
    }

    public void trigger() {
        this.active = true;
        this.tickDown = this.time;
        this.tickUp = 0;
        this.sync();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        sync();
    }

    public int getTickDown() {
        return tickDown;
    }

    public void setTickDown(int tickDown) {
        this.tickDown = tickDown;
        sync();
    }

    public int getTickUp() {
        return tickUp;
    }

    public void setTickUp(int tickUp) {
        this.tickUp = tickUp;
        sync();
    }
}
