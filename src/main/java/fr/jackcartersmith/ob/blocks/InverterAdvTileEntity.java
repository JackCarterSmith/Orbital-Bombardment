package fr.jackcartersmith.ob.blocks;

import fr.jackcartersmith.ob.interfaces.PhotonProducing;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import net.minecraft.nbt.NBTTagCompound;

public class InverterAdvTileEntity extends PhotonProducing
{   
    public InverterAdvTileEntity()
    {
        this.setChargeRate(OSConstants.AdvPhotonInvChargeRate);
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
}
