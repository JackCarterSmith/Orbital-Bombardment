package fr.jackcartersmith.ob.entities;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.world.World;

public class LaserLightEntity extends EntitySlime
{
    int ticksAlive;
    double renderSize;
    double angleZ;
    double angleY;

    public LaserLightEntity(World par1World, double xH, double yH, double zH, double xT, double yT, double zT)
    {
        super(par1World);
        System.out.println("SPAWNED");
        double dx = xH - xT;
        double dy = yH - yT;
        double dz = zH - zT;
        this.renderSize = Math.sqrt(dx * dx + dy * dy + dz * dz);
        this.angleZ = 360.0D - (Math.atan2(dz, dx) * 180.0D / Math.PI + 180.0D);
        dx = Math.sqrt(this.renderSize * this.renderSize - dy * dy);
        this.angleY = -Math.atan2(dy, dx) * 180.0D / Math.PI;
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {}

    /**
     * Sets the entity's position and rotation. Args: posX, posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation(double par1, double par3, double par5, float par7, float par8) {}

    protected void entityInit() {}

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        ++this.ticksAlive;

        if (this.ticksAlive > 20)
        {
            this.kill();
            this.ticksAlive = 0;
        }
    }
}
