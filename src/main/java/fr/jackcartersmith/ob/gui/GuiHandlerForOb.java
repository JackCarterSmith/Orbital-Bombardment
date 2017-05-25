package fr.jackcartersmith.ob.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandlerForOb implements IGuiHandler
{
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            default:
                return null;
        }
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case 0:
                return new OverriderBlockGui(player);

            case 1:
                return new PhotonInverterGui(player);

            case 2:
                return new PhotonInverterAdvGui(player);

            case 3:
                return new ExtenderGui(player);

            case 4:
                return new PhotonAcceleratorGui(player);

            case 5:
                return new PhotonDeceleratorGui(player);

            default:
                return null;
        }
    }
}
