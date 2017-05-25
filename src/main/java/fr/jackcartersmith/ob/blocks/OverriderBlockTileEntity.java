package fr.jackcartersmith.ob.blocks;

import fr.jackcartersmith.ob.OBNetwork;
import fr.jackcartersmith.ob.OBNetworkClient;
import fr.jackcartersmith.ob.OrbitalBombardment;
import fr.jackcartersmith.ob.interfaces.PhotonConsuming;
import fr.jackcartersmith.ob.libs.OBConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class OverriderBlockTileEntity extends PhotonConsuming
{
    public boolean charging;
    public HashMap amountOfGuns = new HashMap();
    public int lightlevel;
    public int tickCount = 0;
    public float rotate;
    public boolean hasSatelite;
    public boolean hasSateliteLaunched;
    public int shotsLeft = 0;
    public int satTicksAlive = 0;

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setInteger("currentCharge", this.getCurrentCharge());
        par1.setInteger("shotsLeft", this.shotsLeft);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        this.currentCharge = par1.getInteger("currentCharge");
        this.shotsLeft = par1.getInteger("shotsLeft");
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.maxCharge = OBConstants.OverriderMaxCharge;

        if (this.currentCharge > this.maxCharge)
        {
            this.currentCharge = this.maxCharge;
        }

        if (this.hasSateliteLaunched && this.worldObj.isRemote)
        {
            ++this.satTicksAlive;

            if (this.satTicksAlive >= 100)
            {
                this.hasSateliteLaunched = false;
                this.satTicksAlive = 0;
                this.sendChangeToServer21(this.xCoord, this.yCoord + 1, this.zCoord, 0, Minecraft.getMinecraft().thePlayer);
                this.worldObj.removeTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
                this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 1, this.zCoord);
            }
        }

        if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof SateliteTileEntity)
        {
            this.hasSatelite = true;
        }
        else
        {
            this.hasSatelite = false;
        }

        if (this.tickCount == 2)
        {
            this.sendChangeToClient();
            this.sendChangeToClient5();
            this.tickCount = 0;
        }
        else
        {
            ++this.tickCount;
        }

        this.rotate += 0.05F;

        if ((double)this.rotate >= 180.0D)
        {
            this.rotate = -180.0F;
        }
    }

    public boolean isCharging()
    {
        return this.charging;
    }

    public void setCharging(boolean charging)
    {
        this.charging = charging;
    }

    public void sendChangeToClient()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(1);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getCurrentCharge());

        OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToClient5()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(5);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.shotsLeft);

        OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToServer21(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(21);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);

        OrbitalBombardment.obNetwork.sendToServer(new OBNetwork(bos));
    }
}
