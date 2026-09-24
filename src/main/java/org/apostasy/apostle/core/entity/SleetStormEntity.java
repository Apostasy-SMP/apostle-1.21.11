package org.apostasy.apostle.core.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

/**
 * @author Chemthunder
 */
public class SleetStormEntity extends Entity {
    public static final float SIZE = 25.0F;

    public SleetStormEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    protected void initDataTracker(DataTracker.Builder builder) {}

    public void tick() {
        super.tick();

        Box DETECTION = new Box(this.getBlockPos()).expand(SIZE);
    }

    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        return false;
    }

    public boolean canUsePortals(boolean allowVehicles) {
        return false;
    }

    protected void readCustomData(ReadView view) {

    }

    protected void writeCustomData(WriteView view) {

    }
}
