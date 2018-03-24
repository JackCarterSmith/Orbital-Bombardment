package fr.jackcartersmith.ob.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.jackcartersmith.ob.blocks.InverterAdvTileEntity;
import fr.jackcartersmith.orbsat.OBNetworkClient;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.tileentity.TileEntity;

public class PhotonProducing extends TileEntity
{
    int chargeRate = 0;
    int currentCharge = 0;
    public long time = 0L;
    public int powerIncoming;
    public int scaledOutput;
    private int tickCount = 0;
    public float rotate = -3.141F;

    public List<PhotonConsuming> getSurroundingPhotonConsumers()
    {
        ArrayList surroundingConsumers = new ArrayList();

        try
        {
            if (this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord));
            }

            if (this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord));
            }

            if (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord));
            }

            if (this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord));
            }

            if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1));
            }

            if (this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1) instanceof PhotonConsuming)
            {
                surroundingConsumers.add((PhotonConsuming)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1));
            }
        }
        catch (Exception var3)
        {
            System.out.println("ORBITAL BOMBARDMENT::Cannot cast entity into PhotonConsuming");
            var3.printStackTrace();
        }

        return surroundingConsumers;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        if (this.getPowerIncoming() > 0)
        {
            this.rotate += 1.0E-4F * (float)this.getPowerIncoming();
        }

        if (this.rotate >= 3.141F)
        {
            this.rotate = -3.141F;
        }

        if (!this.worldObj.isRemote)
        {
            List surroundingPhotonConsumers = this.getSurroundingPhotonConsumers();
            this.addCharge(this.getChargeRate());
            int howMany = surroundingPhotonConsumers.size();
            boolean scaledPowerOutput = false;
            int var8;

            if (howMany == 0)
            {
                howMany = 1;
                var8 = this.getCurrentCharge() / howMany;
            }
            else
            {
                var8 = this.getCurrentCharge() / howMany;
            }

            if (this.tickCount == 10)
            {
                this.sendChangeToClient13();
                this.sendChangeToClient15();
                
                this.tickCount = 0;
            }
            else
            {
                ++this.tickCount;
            }

            this.setScaledOutput(var8);
            this.setPowerIncoming(this.getCurrentCharge());

            if (var8 > 0)
            {
                Iterator i$ = surroundingPhotonConsumers.iterator();

                while (i$.hasNext())
                {
                    PhotonConsuming entity = (PhotonConsuming)i$.next();

                    try
                    {
                        if (entity.getCurrentCharge() < entity.getMaxCharge())
                        {
                            entity.addCharge(var8);
                        }

                        var8 = this.getCurrentCharge() / howMany;
                        entity.currentCharge += var8;
                    }
                    catch (Exception var7)
                    {
                        ;
                    }
                }

                this.removeCharge(this.getCurrentCharge());
            }
        }

        this.time = this.worldObj.getWorldTime();
        this.time %= 24000L;
    }

    public void addCharge(int incomingCharge)
    {
        this.currentCharge += incomingCharge;
    }

    public void removeCharge(int outgoingCharge)
    {
        this.currentCharge -= outgoingCharge;
    }

    public int getChargeRate()
    {
        return this.chargeRate;
    }

    public void setChargeRate(int chargeRate)
    {
        this.chargeRate = chargeRate;
    }

    public int getCurrentCharge()
    {
        return this.currentCharge;
    }

    public void setCurrentCharge(int currentCharge)
    {
        this.currentCharge = currentCharge;
    }

    public long getTime()
    {
        return this.time;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public int getPowerIncoming()
    {
        return this.powerIncoming;
    }

    public void setPowerIncoming(int powerIncoming)
    {
        this.powerIncoming = powerIncoming;
    }

    public int getScaledOutput()
    {
        return this.scaledOutput;
    }

    public void setScaledOutput(int scaledOutput)
    {
        this.scaledOutput = scaledOutput;
    }

    public void sendChangeToClient13()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(13);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getScaledOutput());
        
        OrbitalSatellite.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToClient15()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(15);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getPowerIncoming());
        
        OrbitalSatellite.obNetwork.sendToAll(new OBNetworkClient(bos));
    }
}
