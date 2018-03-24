package fr.jackcartersmith.orbsat.common.tileentities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

//public class TileDefender extends TileObjectSync implements IEnergyReceiver, ISidedInventory {
public class TileDefender extends TileObjectSync {
    public float modelIllumination = 1F;
    public int facingDirection = ForgeDirection.UP.ordinal();
    public boolean isValid = false;
    public int tick = 0;

    //@SideOnly(Side.CLIENT)
    //private ParticleReactorBeam beam;
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        //masterLocation.writeToNBT(compound, "Master");
        compound.setInteger("Facing", facingDirection);
        //compound.setBoolean("IsValid", isValid);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, compound);
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound compound = pkt.func_148857_g();
        //masterLocation.readFromNBT(compound, "Master");
        facingDirection = compound.getInteger("Facing");
        //isValid = compound.getBoolean("IsValid");
        super.onDataPacket(net, pkt);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        /*
        masterLocation.writeToNBT(compound, "Master");
        compound.setInteger("Facing", facingDirection);
        compound.setBoolean("IsValid", isValid);
        compound.setInteger("RedstoneMode", redstoneMode);
        */
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        /*
        masterLocation.readFromNBT(compound, "Master");
        facingDirection = compound.getInteger("Facing");
        isValid = compound.getBoolean("IsValid");
        redstoneMode = compound.getInteger("RedstoneMode");
        */
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
    }
}
