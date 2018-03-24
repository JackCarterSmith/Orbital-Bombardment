package fr.jackcartersmith.orbsat.common.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import fr.jackcartersmith.orbsat.common.network.TileObjectPacket;
import fr.jackcartersmith.orbsat.common.tileentities.TileObjectSync;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;

public abstract class ContainerDataSync extends Container {

    //TODO WTF? Was i sleep coding when i wrote this? In 1.8 re write this as a common container class for all mod containers and use a dedicated sender/receiver method for each data type i want to send (Unless i think of something better between now and then)

    /**
     * Sends two ints to the client-side Container. Normally the first int identifies which variable to update, and the second contains the new
     * value.
     * if tile != null the packet will be sent to the tile client tile instead of the client container.
     */
    public Object sendObjectToClient(TileObjectSync tile, int index, Object object) {
        for (Object p : crafters) {
            OrbitalSatellite.network.sendTo(new TileObjectPacket(tile, OSRefs.INT_ID, index, object), (EntityPlayerMP) p);
        }
        return object;
    }

    /**
     * Sends two ints to the server-side Container. Normally the first int identifies which variable to update, and the second contains the new
     * value.
     * if tile != null the packet will be sent to the tile client tile instead of the client container.
     */
    @SideOnly(Side.CLIENT)
    public Object sendObjectToServer(TileObjectSync tile, int index, Object object) {
        OrbitalSatellite.network.sendToServer(new TileObjectPacket(tile, OSRefs.INT_ID, index, object));
        return object;
    }

    /**
     * Called when a packet is received from ether the client or the server
     */
    public abstract void receiveSyncData(int index, int value);
}
