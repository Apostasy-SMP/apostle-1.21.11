package org.apostasy.apostle.core.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CrowMoveControl extends MoveControl {
    private int takeoffTick = 0;

    private final int maxPitchChange;
    private final float upwardsMul;
    private final boolean noGravity;

    public CrowMoveControl(MobEntity entity, int maxPitchChange, float upwardsMul, boolean noGravity) {
        super(entity);
        this.maxPitchChange = maxPitchChange;
        this.upwardsMul = upwardsMul;
        this.noGravity = noGravity;
    }

    public void tick() {
        if (this.state == MoveControl.State.MOVE_TO) {
            if (this.takeoffTick < 5) {
                this.takeoffTick++;
            } else if (this.entity.isOnGround()) {
                this.takeoffTick = 0;
            }

            this.state = MoveControl.State.WAIT;
            this.entity.setNoGravity(true);
            double d = this.targetX - this.entity.getX();
            double e = this.targetY - this.entity.getY();
            double f = this.targetZ - this.entity.getZ();
            double g = d * d + e * e + f * f;
            if (g < 2.5000003E-7) {
                this.entity.setUpwardSpeed(0.0F);
                this.entity.setForwardSpeed(0.0F);
                return;
            }

            float h = (float) (MathHelper.atan2(f, d) * (180F / (float) Math.PI)) - 90.0F;
            this.entity.setYaw(this.wrapDegrees(this.entity.getYaw(), h, 90.0F));
            float i;
            if (this.entity.isOnGround()) {
                i = (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.MOVEMENT_SPEED));
            } else {
                i = (float)(this.speed * this.entity.getAttributeValue(EntityAttributes.FLYING_SPEED));
            }

            this.entity.setMovementSpeed(i * (this.takeoffTick / 5F));
            double j = Math.sqrt(d * d + f * f);
            if (Math.abs(e) > (double)1.0E-5F || Math.abs(j) > 1.0E-5) {
                float k = (float) (-(MathHelper.atan2(e, j) * (180F / (float) Math.PI)));
                this.entity.setPitch(this.wrapDegrees(this.entity.getPitch(), k, (float)this.maxPitchChange));
                this.entity.setUpwardSpeed(e > 0.0 ? i : -i);
            }
        } else {
            this.takeoffTick = 0;
            if (!this.noGravity) {
                this.entity.setNoGravity(false);
            }

            this.entity.setUpwardSpeed(0.0F);
            this.entity.setForwardSpeed(0.0F);
        }

    }
}
