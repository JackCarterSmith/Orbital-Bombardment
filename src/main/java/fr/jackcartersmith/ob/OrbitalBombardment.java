package fr.jackcartersmith.ob;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.jackcartersmith.ob.blocks.OBBlocks;
import fr.jackcartersmith.ob.entities.LaserLightEntity;
import fr.jackcartersmith.ob.entities.MeteorEntity;
import fr.jackcartersmith.ob.gui.GuiHandlerForOb;
import fr.jackcartersmith.ob.items.OBItems;
import fr.jackcartersmith.ob.libs.OBConstants;
import fr.jackcartersmith.ob.libs.OBCreativeTabs;
import fr.jackcartersmith.ob.proxy.CommonProxy;

@Mod(modid = OrbitalBombardment.MODID, version = OrbitalBombardment.VERSION)
public class OrbitalBombardment
{
	@Instance("ob")
	public static OrbitalBombardment instance;
	
	@SidedProxy(clientSide = "fr.jackcartersmith.ob.proxy.ClientProxy", serverSide = "fr.jackcartersmith.ob.proxy.CommonProxy")
	public static CommonProxy proxy;

    public static SimpleNetworkWrapper obNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("OB");
    //private GuiHandlerForOb guiHandlerForOb = new GuiHandlerForOb();
    
    public static int sateliteModelId;
    
    public int lastChargerX;
    public int lastChargerY;
    public int lastChargerZ;
    public int lastExtenderX;
    public int lastExtenderY;
    public int lastExtenderZ;
    public int lastGeneralX;
    public int lastGeneralY;
    public int lastGeneralZ;
    
    public static int mobid = 0;
	
    public static final String MODID = "ob";
    public static final String VERSION = "2.0";
    
    public static CreativeTabs OBCreativeTabs = new OBCreativeTabs("ob_creative_tabs");
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
	    //Init conf file
		OBConstants.init(event.getSuggestedConfigurationFile());
		
		//Create packet channel for OB and register packets
		obNetwork = NetworkRegistry.INSTANCE.newSimpleChannel("OB");
		obNetwork.registerMessage(OBNetwork.Handler.class, OBNetwork.class, 1, Side.SERVER);
		obNetwork.registerMessage(OBNetworkClient.Handler.class, OBNetworkClient.class, 2, Side.CLIENT);
		
		//Init Items and Blocks
		OBItems.init();
		OBBlocks.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
	    EntityRegistry.registerModEntity(MeteorEntity.class, "meteor", 1, instance, 80, 3, true);
	    EntityRegistry.registerModEntity(LaserLightEntity.class, "LaserLightEntity", 2, instance, 80, 3, true);
	    
	    //Register GuiHandler for all GUI
	    NetworkRegistry.INSTANCE.registerGuiHandler(OrbitalBombardment.instance, new GuiHandlerForOb());
	    
		proxy.registerRender();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{

	}
}
