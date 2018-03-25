package fr.jackcartersmith.orbsat.common.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

//public class TileDefender extends TileObjectSync implements IEnergyReceiver, ISidedInventory {
public class TileDefender extends TileEntity {
    public float modelIllumination = 30F;
    public int facingDirection = ForgeDirection.UP.ordinal();
    public boolean running = false;
    public boolean runningCach = false;
    private int tick = 0;
    public float rotation = 0;
    public float rotationSpeed = 0;
    private float targetSpeed = 0;

    //@SideOnly(Side.CLIENT)
    //private ParticleReactorBeam beam;
    
    @Override
    public void updateEntity() {
        //rotation += 1.0E-2F * (float)this.getCurrentCharge();
    	rotation += 1.0E-1F;
    	
        if ((double)rotation >= Math.PI)
        {
            rotation = (float)(-Math.PI);
        }
    	
    	/*
        targetSpeed = 1F;

        if (rotationSpeed < targetSpeed) rotationSpeed += 0.05F;
        else if (rotationSpeed > targetSpeed) rotationSpeed -= 0.05F;
        if (targetSpeed == 0 && rotationSpeed < 0) rotationSpeed = 0;
        rotation += rotationSpeed;
        */
    }
    
    /*
    private boolean tryStartOrStop() {
        if (items[0] != null && items[0].stackSize == 1 && items[0] != null && items[0].getItem() instanceof IEnergyContainerItem) {
            IEnergyContainerItem item = (IEnergyContainerItem) items[0].getItem();
            if (item.getEnergyStored(items[0]) < item.getMaxEnergyStored(items[0])) {
                running = true;
            } else {
                running = false;
            }
        } else {
            running = false;
        }

        return running;
    }
    */
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        this.writeToNBT(tagCompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());
    }
    
    /*
    public void detectAndSendChanges(boolean sendAnyway) {
        if (runningCach != running || sendAnyway) {
            runningCach = (Boolean) sendObjectToClient(OSRefs.BOOLEAN_ID, 0, running);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void receiveObjectFromServer(int index, Object object) {
        if (index == 0) running = (Boolean) object;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        masterLocation.writeToNBT(compound, "Master");
        compound.setInteger("Facing", facingDirection);
        compound.setBoolean("IsValid", isValid);
        compound.setInteger("RedstoneMode", redstoneMode);
    }
	*/

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
