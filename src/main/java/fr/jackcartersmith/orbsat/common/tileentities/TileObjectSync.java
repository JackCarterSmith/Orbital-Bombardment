package fr.jackcartersmith.orbsat.common.tileentities;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.network.TileObjectPacket;
import net.minecraft.tileentity.TileEntity;

public class TileObjectSync extends TileEntity{
	/**
     * Sends a primitive to the client in the form of an object
     */
    public Object sendObjectToClient(byte dataType, int index, Object object) {
        return sendObjectToClient(dataType, index, object, new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 64));
    }

    /**
     * Sends a primitive to the client in the form of an object
     */
    public Object sendObjectToClient(byte dataType, int index, Object object, TargetPoint point) {
        OrbitalSatellite.network.sendToAllAround(new TileObjectPacket(this, dataType, index, object), point);
        return object;
    }

    public Object sendObjectToServer(byte dataType, int index, Object object) {
    	OrbitalSatellite.network.sendToServer(new TileObjectPacket(this, dataType, index, object));
        return object;
    }

    /**
     * Receives an object from the server
     */
    public void receiveObjectFromClient(int index, Object object) {
    }

    /**
     * Receives an object from the server
     */
    public void receiveObjectFromServer(int index, Object object) {
    }
}
