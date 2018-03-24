package fr.jackcartersmith.ob.blocks;

import fr.jackcartersmith.ob.interfaces.PhotonRecieving;
import fr.jackcartersmith.orbsat.OBNetworkClient;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;

public class DefenderTileEntity extends PhotonRecieving
{  
    int ticksCount = 0;

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.rotate += 1.0E-6F * (float)this.getCurrentCharge();

        if ((double)this.rotate >= 3.141D)
        {
            this.rotate = -3.141F;
        }

        if (!this.worldObj.isRemote && this.getCurrentCharge() > 500)
        {
            ++this.ticksCount;

            if (this.ticksCount > 35)
            {
                this.sendChangeToClient17();
                this.ticksCount = 0;
                List entities = this.getNearByEntities();
                int thisX = this.xCoord;
                int thisY = this.yCoord;
                int thisZ = this.zCoord;
                Random random = new Random();

                if (entities.size() > 0)
                {
                    this.worldObj.playSoundEffect((double)thisX, (double)thisY, (double)thisZ, "ob:defenderLock", 1.0F, 1.0F);
                    EntityMob mob = (EntityMob)entities.get(random.nextInt(entities.size()));

                    if (this.getCurrentCharge() > 500)
                    {
                        this.removeCharge(500);
                        int x = (int)mob.lastTickPosX;
                        int y = (int)mob.lastTickPosY;
                        int z = (int)mob.lastTickPosZ;
                        this.worldObj.setBlock(x, y, z, OSBlocks.laserDef);
                        this.worldObj.setBlock(thisX, thisY + 1, thisZ, OSBlocks.laserDef);
                        this.removeCharge(500);
                    }
                }
            }
        }

        if (this.getCurrentCharge() > 5000)
        {
            this.setCurrentCharge(5000);
        }
    }

    public List getNearByEntities()
    {
        int radius = OSConstants.DefenderRadius;
        List entities = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)(this.xCoord - radius), (double)(this.yCoord - radius), (double)(this.zCoord - radius), (double)(this.xCoord + radius), (double)(this.yCoord + radius), (double)(this.zCoord + radius)));
        return entities;
    }

    public void sendChangeToClient17()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(17);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getCurrentCharge());

        OrbitalSatellite.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToClient19()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(19);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(0);

        OrbitalSatellite.obNetwork.sendToAll(new OBNetworkClient(bos));
    }
}
