package fr.jackcartersmith.orbsat.common.tileentities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.client.render.particle.ParticleLaserBeam;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.interfaces.PhotonRecieving;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import fr.jackcartersmith.orbsat.common.network.PhotonPacket;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

//public class TileDefender extends TileObjectSync implements IEnergyReceiver, ISidedInventory {
public class TileDefender extends PhotonRecieving {
	public int ticksCount = 0;
    public static float modelIllumination = 30F;
    public int facingDirection = ForgeDirection.UP.ordinal();
    public boolean firing = false;
    private static int beamPow = 125;
    public float rotationSpeed = 4.0F;
    public float crystalBrightness = 1.0F;
    private float xTarget = this.xCoord + 2F;
    private float yTarget = this.yCoord;
    private float zTarget = this.zCoord + 4F;
    
    @SideOnly(Side.CLIENT)
    private ParticleLaserBeam beam_turret;
    @SideOnly(Side.CLIENT)
    private ParticleLaserBeam beam_target;
    
    @Override
    public void updateEntity() { 	
    	++ticksCount;
    	
        if (worldObj.isRemote) {
            updateBeam();
        }
        
        //if (!this.worldObj.isRemote && this.getCurrentCharge() > 500)
        if (!worldObj.isRemote)
        {        
            if (ticksCount > 75) {
            	firing = false;
            	ticksCount = 0;		//Reset tick counter
            }

            if (ticksCount > 35 && !firing)
            {
                sendPhotonPacket17();
                List<?> entities = getNearByEntities();
                int thisX = xCoord;
                int thisY = yCoord;
                int thisZ = zCoord;
                Random random = new Random();

                if (entities.size() > 0)
                {
                    worldObj.playSoundEffect((double)thisX, (double)thisY, (double)thisZ, "ob:defenderLock", 1.0F, 1.0F);
                    EntityMob mob = (EntityMob)entities.get(random.nextInt(entities.size()));

                    xTarget = (int)mob.lastTickPosX;
                    yTarget = (int)mob.lastTickPosY;
                    zTarget = (int)mob.lastTickPosZ;
                    firing = true;
                    //this.worldObj.setBlock((int)this.xTarget, (int)this.yTarget, (int)this.zTarget, OSBlocks.defender);
                    //this.worldObj.setBlock(thisX, thisY + 1, thisZ, OSBlocks.laserDef);
                    /*
                    if (this.getCurrentCharge() > 500)
                    {
                        this.removeCharge(500);
                        this.xTarget = (int)mob.lastTickPosX;
                        this.yTarget = (int)mob.lastTickPosY;
                        this.zTarget = (int)mob.lastTickPosZ;
                        this.firing = true;
                        //this.worldObj.setBlock((int)this.xTarget, (int)this.yTarget, (int)this.zTarget, OSBlocks.defender);
                        //this.worldObj.setBlock(thisX, thisY + 1, thisZ, OSBlocks.laserDef);
                        this.removeCharge(500);
                    }
                    */
                }
            }
        }
        	
    
        
        
           
        
        /*
        if (this.active && this.startUp != 1.0F)
        {
            if (this.startUp < 1.0F)
            {
                this.startUp += Math.max(this.startUp / 10.0F, 0.001F);
            }

            if ((double)this.startUp > 0.999D)
            {
                this.startUp = 1.0F;
            }
        }

        if (!this.active && this.startUp > 0.0F)
        {
            if (this.startUp > 0.0F)
            {
                this.startUp -= this.startUp / 10.0F;
            }

            if ((double)this.startUp < 0.001D)
            {
                this.startUp = 0.0F;
            }
        }
         */
        
        
    	/*
        targetSpeed = 1F;

        if (rotationSpeed < targetSpeed) rotationSpeed += 0.05F;
        else if (rotationSpeed > targetSpeed) rotationSpeed -= 0.05F;
        if (targetSpeed == 0 && rotationSpeed < 0) rotationSpeed = 0;
        rotation += rotationSpeed;
        */
    }
    
    @SideOnly(Side.CLIENT)
    private void updateBeam() {
        if (worldObj.isRemote) {
        	if (firing) {
        		crystalBrightness = 0.95F;
        	} else {
        		crystalBrightness = 0.0F;
        	}
        	
        	beam_turret = OrbitalSatellite.proxy.energyBeam(worldObj, xCoord + 0.51F, yCoord, zCoord + 0.5F, xCoord + 0.51F, yCoord + 1024, zCoord + 0.5F, beamPow, 8, beam_turret, firing, 0);
        	beam_target = OrbitalSatellite.proxy.energyBeam(worldObj, xTarget + 0.51F, yTarget + 1024, zTarget + 0.5F, xTarget + 0.51F, yTarget, zTarget + 0.5F, beamPow, 8, beam_target, firing, 0);
        }
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
    
    public List<?> getNearByEntities()
    {
        int radius = OSConstants.DefenderRadius;
        List<?> entities = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)(this.xCoord - radius), (double)(this.yCoord - radius), (double)(this.zCoord - radius), (double)(this.xCoord + radius), (double)(this.yCoord + radius), (double)(this.zCoord + radius)));
        return entities;
    }
    
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setBoolean("IsFiring", firing);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	NBTTagCompound compound = pkt.getNbtCompound();
    	firing = compound.getBoolean("IsFiring");
        super.onDataPacket(net, pkt);
    }
    
    public void sendPhotonPacket17()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(17);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getCurrentCharge());

        OrbitalSatellite.network.sendToAll(new PhotonPacket(bos));
    }

    public void sendPhotonPacket19()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(19);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(0);

        OrbitalSatellite.network.sendToAll(new PhotonPacket(bos));
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
    */
    
    @Override
    public void writeToNBT(NBTTagCompound compound) {
    	super.writeToNBT(compound);
        compound.setBoolean("IsFiring", this.firing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
    	super.readFromNBT(compound);
        this.firing = compound.getBoolean("IsFiring");
    }
    
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1);
    }
}
