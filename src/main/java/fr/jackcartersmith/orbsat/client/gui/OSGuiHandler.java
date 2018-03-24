package fr.jackcartersmith.orbsat.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class OSGuiHandler implements IGuiHandler{
	
    public static final int GUIID_OVERRIDER = 0;
    public static final int GUIID_PHOTON_ACCELERATOR = 1;
    public static final int GUIID_PHOTON_DECELERATOR = 2;
    public static final int GUIID_INVERTER = 3;
    public static final int GUIID_INVERTER_ADV = 4;
	
	public OSGuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(OrbitalSatellite.instance, this);
	}
	
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
        case GUIID_OVERRIDER:
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null && te instanceof TileWeatherController) {
                return new GUIWeatherController(player.inventory, (TileWeatherController) te);
            }
            break;
        case GUIID_PHOTON_ACCELERATOR:
            TileEntity te1 = world.getTileEntity(x, y, z);
            if (te1 != null && te1 instanceof TileSunDial) {
                return new GUISunDial(player.inventory, (TileSunDial) te1);
            }
            break;
        case GUIID_PHOTON_DECELERATOR:
            return new GUITeleporter(player);
        case GUIID_INVERTER:
            TileEntity te2 = world.getTileEntity(x, y, z);
            if (te2 != null && te2 instanceof TileGrinder) {
                return new GUIGrinder(player.inventory, (TileGrinder) te2);
            }
            break;
        case GUIID_INVERTER_ADV:
            TileEntity te3 = world.getTileEntity(x, y, z);
            if (te3 != null && te3 instanceof TileSunDial) {
                return new GUISunDial(player.inventory, (TileSunDial) te3);
            }
            break;
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		switch (id) {
        case GUIID_OVERRIDER:
            TileEntity te = world.getTileEntity(x, y, z);
            if (te != null && te instanceof TileWeatherController) {
                return new ContainerWeatherController(player.inventory, (TileWeatherController) te);
            }
            break;
        case GUIID_PHOTON_ACCELERATOR:
            TileEntity te1 = world.getTileEntity(x, y, z);
            if (te1 != null && te1 instanceof TileSunDial) {
                return new ContainerSunDial(player.inventory, (TileSunDial) te1);
            }
            break;
        case GUIID_PHOTON_DECELERATOR:
            TileEntity te2 = world.getTileEntity(x, y, z);
            if (te2 != null && te2 instanceof TileSunDial) {
                return new ContainerSunDial(player.inventory, (TileSunDial) te2);
            }
            break;
        case GUIID_INVERTER:
            TileEntity te3 = world.getTileEntity(x, y, z);
            if (te3 != null && te3 instanceof TileSunDial) {
                return new ContainerSunDial(player.inventory, (TileSunDial) te3);
            }
            break;
        case GUIID_INVERTER_ADV:
            TileEntity te4 = world.getTileEntity(x, y, z);
            if (te4 != null && te4 instanceof TileSunDial) {
                return new ContainerSunDial(player.inventory, (TileSunDial) te4);
            }
            break;
		}
		return null;
	}

}
