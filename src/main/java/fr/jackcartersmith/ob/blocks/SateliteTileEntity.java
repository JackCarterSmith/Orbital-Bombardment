package fr.jackcartersmith.ob.blocks;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class SateliteTileEntity extends TileEntity
{
    public float height = 0.0F;
    public float speed = 0.01F;
    public boolean shouldLaunch = false;
    public int ticksAlive = 0;
    public Boolean startedTicking = Boolean.valueOf(false);

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setBoolean("shouldLaunch", this.shouldLaunch);
        par1.setBoolean("startedTicking", this.startedTicking.booleanValue());
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        this.shouldLaunch = par1.getBoolean("shouldLaunch");
        this.startedTicking = Boolean.valueOf(par1.getBoolean("startedTicking"));
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (this.startedTicking.booleanValue())
        {
            ++this.ticksAlive;
        }

        if (this.shouldLaunch)
        {
            this.height += this.speed;
            this.speed *= 1.1F;
            Random random = new Random();
            this.startedTicking = Boolean.valueOf(true);

            for (int i = 0; i <= 25; ++i)
            {
                this.worldObj.spawnParticle("cloud", (double)this.xCoord + random.nextDouble() + 0.3D - 0.6D, (double)((float)this.yCoord + this.height) + random.nextDouble() + 0.3D - 0.6D, (double)this.zCoord + random.nextDouble() + 0.3D - 0.6D, 0.0D, -2.0D, 0.0D);
            }
        }

        if (this.ticksAlive >= 160)
        {
            this.worldObj.setBlockToAir(this.xCoord, this.xCoord, this.xCoord);
            this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
        }
    }
}
