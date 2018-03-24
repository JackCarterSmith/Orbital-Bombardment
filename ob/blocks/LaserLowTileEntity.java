package fr.jackcartersmith.ob.blocks;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class LaserLowTileEntity extends TileEntity
{
    public float rotate = -3.141F;
    public float insideCounter = 8.0F;
    public float color = 0.0F;
    public float grow = 1.0F;
    public boolean low = false;
    public boolean med = false;
    public boolean high = false;
    public double speed;
    boolean down = true;

    public LaserLowTileEntity(String color)
    {
        if (color == "blue")
        {
            this.low = true;
        }

        if (color == "orange")
        {
            this.med = true;
        }

        if (color == "red")
        {
            this.high = true;
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.speed += 0.5D;
        Random random = new Random();
        float f1 = (float)this.xCoord + 0.5F;
        float f2 = (float)this.yCoord + 1.0F;
        float f3 = (float)this.zCoord + 0.5F;
        int id;

        if (this.low)
        {
            for (id = 0; id <= 150; ++id)
            {
                this.worldObj.spawnParticle("reddust", (double)f1 + random.nextDouble() + 0.3D - 0.6D, (double)f2 + random.nextDouble() + 0.3D - 0.6D + (double)id, (double)f3 + random.nextDouble() + 0.3D - 0.6D, 20.0D - this.speed, 20.0D - this.speed, 20.0D + this.speed);
            }
        }

        if (this.med)
        {
            for (id = 0; id <= 300; ++id)
            {
                this.worldObj.spawnParticle("reddust", (double)f1 + random.nextDouble() + 0.3D - 0.6D, (double)f2 + random.nextDouble() + 0.3D - 0.6D + (double)id, (double)f3 + random.nextDouble() + 0.3D - 0.6D, 20.0D + this.speed, 20.0D + this.speed, 20.0D - this.speed);
            }
        }

        if (this.high)
        {
            for (id = 0; id <= 600; ++id)
            {
                this.worldObj.spawnParticle("reddust", (double)f1 + random.nextDouble() + 0.3D - 0.6D, (double)f2 + random.nextDouble() + 0.3D - 0.6D + (double)id, (double)f3 + random.nextDouble() + 0.3D - 0.6D, 20.0D + this.speed, 20.0D - this.speed, 20.0D - this.speed);
            }
        }

        this.rotate += 0.05F;

        if (this.rotate > 3.141F)
        {
            this.rotate = -3.141F;
        }

        if (this.insideCounter > 0.5F)
        {
            this.insideCounter -= 0.1F;
        }

        ++this.color;

        if (this.insideCounter < 0.5F)
        {
            this.grow += 0.05F;
        }
    }
}
