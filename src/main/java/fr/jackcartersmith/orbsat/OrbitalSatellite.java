package fr.jackcartersmith.orbsat;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import fr.jackcartersmith.orbsat.client.creativetabs.OSCreativeTabs;
import fr.jackcartersmith.orbsat.common.CommonProxy;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import fr.jackcartersmith.orbsat.common.utils.OSLogHelper;

@Mod(modid = OSRefs.MODID, name = OSRefs.MODNAME, version = OSRefs.VERSION, canBeDeactivated = false)
public class OrbitalSatellite
{
	//============== DEBUG MODE ===============//
    public static boolean debug = false;
    //=========================================//
	
	@Mod.Instance(OSRefs.MODID)
	public static OrbitalSatellite instance;
	
	@SidedProxy(clientSide = OSRefs.CLIENTPROXYLOCATION, serverSide = OSRefs.SERVERPROXYLOCATION)
	public static CommonProxy proxy;
	
    public static CreativeTabs OBCreativeTabs = new OSCreativeTabs(CreativeTabs.getNextID(),OSRefs.MODID);

    public static final String networkChannelName = OSRefs.MODID;
    public static SimpleNetworkWrapper network;
    //private GuiHandlerForOb guiHandlerForOb = new GuiHandlerForOb();
    
    /*
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
   
    public DraconicEvolution() {
        LogHelper.info("Hello Minecraft!!!");
    }
    */
    
    public OrbitalSatellite() {
        OSLogHelper.info("Starting OB.sys ...");
    }
    
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		if (debug) OSLogHelper.info("PreInit FML");
		
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
        if (debug) System.out.println("init()");
        
        proxy.init(event);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
        if (debug) System.out.println("postInit()");

        proxy.postInit(event);
	}
}
