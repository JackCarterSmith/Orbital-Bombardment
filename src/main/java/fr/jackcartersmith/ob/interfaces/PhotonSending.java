package fr.jackcartersmith.ob.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.jackcartersmith.ob.OBNetworkClient;
import fr.jackcartersmith.ob.OrbitalBombardment;
import fr.jackcartersmith.ob.libs.OBConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C17PacketCustomPayload;

public class PhotonSending extends PhotonConsuming
{
    public int outputRate;
    public int listSize;
    public int scaledOutput;
    private int tickCount = 0;
    public float rotate = -3.141F;

    public List<PhotonRecieving> getNearbyPhotonRecievingEntities()
    {
        int radius = OBConstants.PhotonSendingRadius;
        ArrayList entities = new ArrayList();
        int xC = this.xCoord;
        int yC = this.yCoord;
        int zC = this.zCoord;

        for (int x = radius / 2 * -1; x <= radius / 2; ++x)
        {
            for (int y = radius / 2 * -1; y <= radius / 2; ++y)
            {
                for (int z = radius / 2 * -1; z <= radius / 2; ++z)
                {
                    if (this.worldObj.getTileEntity(xC + x, yC + y, zC + z) instanceof PhotonRecieving && entities.size() <= 3)
                    {
                        entities.add((PhotonRecieving)this.worldObj.getTileEntity(xC + x, yC + y, zC + z));
                    }
                }
            }
        }

        return entities;
    }

    public void sendPhotonsTo(PhotonRecieving entity, int power)
    {
        PhotonRecieving photonRecievingEntity = (PhotonRecieving)this.worldObj.getTileEntity(entity.xCoord, entity.yCoord, entity.zCoord);

        if (photonRecievingEntity != null)
        {
            photonRecievingEntity.recievePhotons(power);
        }
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.rotate += 0.01F;

        if (this.rotate >= 3.141F)
        {
            this.rotate = -3.141F;
        }

        if (!this.worldObj.isRemote)
        {
            List entitiesForPowerRecieving = this.getNearbyPhotonRecievingEntities();
            int scaledPowerOutput = 0;
            int howMany = entitiesForPowerRecieving.size();

            if (howMany != 0)
            {
                scaledPowerOutput = this.getCurrentCharge() / howMany;
            }
            else
            {
                this.scaledOutput = this.getCurrentCharge();
            }

            if (entitiesForPowerRecieving.size() > 0 && this.getCurrentCharge() > 0)
            {
                Iterator i$ = entitiesForPowerRecieving.iterator();

                while (i$.hasNext())
                {
                    PhotonRecieving entity = (PhotonRecieving)i$.next();
                    this.sendPhotonsTo(entity, scaledPowerOutput);
                }
            }

            this.setScaledOutput(scaledPowerOutput);
            this.setOutputRate(scaledPowerOutput * howMany);
            this.setListSize(howMany);

            if (this.tickCount == 10)
            {
                this.sendChangeToClient7();
                this.sendChangeToClient9();
                this.sendChangeToClient11();
                this.tickCount = 0;
            }
            else
            {
                ++this.tickCount;
            }

            this.setCurrentCharge(this.getCurrentCharge() - scaledPowerOutput * howMany);
        }
    }

    public int getOutputRate()
    {
        return this.outputRate;
    }

    public void setOutputRate(int outputRate)
    {
        this.outputRate = outputRate;
    }

    public int getListSize()
    {
        return this.listSize;
    }

    public void setListSize(int listSize)
    {
        this.listSize = listSize;
    }

    public int getScaledOutput()
    {
        return this.scaledOutput;
    }

    public void setScaledOutput(int scaledOutput)
    {
        this.scaledOutput = scaledOutput;
    }

    public void sendChangeToClient7()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(7);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getOutputRate());

        OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToClient9()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(9);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getScaledOutput());

        OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
    }

    public void sendChangeToClient11()
    {
        /*
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);

        try
        {
            outputStream.writeInt(11);
            outputStream.writeInt(this.xCoord);
            outputStream.writeInt(this.yCoord);
            outputStream.writeInt(this.zCoord);
            outputStream.writeInt(this.getListSize());
        }
        catch (Exception var4)
        {
            var4.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "OB";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        PacketDispatcher.sendPacketToAllPlayers(packet);
        */
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(11);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.getListSize());
        
        OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
    }
}
