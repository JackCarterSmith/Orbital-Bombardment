package jackcartersmith.orbsat;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import jackcartersmith.orbsat.common.CommonProxy;
import jackcartersmith.orbsat.common.OrbsatBlocks;
import jackcartersmith.orbsat.common.utils.LogHelper;

@Mod(modid = OrbitalSatellite.MODID, name = OrbitalSatellite.NAME, version = OrbitalSatellite.VERSION, modLanguage = "java", guiFactory = OrbitalSatellite.GUI_FACTORY, updateJSON = OrbitalSatellite.UPDATE_JSON,
certificateFingerprint = OrbitalSatellite.FINGERPRINT, dependencies = OrbitalSatellite.DEPENDENCIES)
public class OrbitalSatellite {
    public static final String MODID = "orbsat";
    public static final String NAME = "Orbital Satellite";
    public static final String VERSION = "${version}";
    public static final String GUI_FACTORY = "com.raoulvdberge.refinedstorage.gui.config.ModGuiFactory";
    public static final String UPDATE_JSON = "https://mcmods.jcsmith.fr/orbsat/versions.json";
    public static final String FINGERPRINT = "30f9f06606e9ab799c59ec743cab264c8310531d";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.3.2847,);";
    
    @Mod.Instance(MODID)
	public static OrbitalSatellite INSTANCE = new OrbitalSatellite();
	@SidedProxy(clientSide = "jackcartersmith.orbsat.client.ClientProxy", serverSide = "jackcartersmith.orbsat.common.CommonProxy")
	public static CommonProxy PROXY;
	
    public OrbsatConfig config;
	public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
	
	public static CreativeTabs creativeTab = new CreativeTabs(MODID)
	{
		@Override
		public ItemStack createIcon()
		{
			return ItemStack.EMPTY;
		}


		/*@Override
		public ItemStack getIcon()
		{
			return new ItemStack(IEContent.blockMetalDecoration0, 1, 0);
		}*/
	};

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	LogHelper.logger = event.getModLog();
    	config = new OrbsatConfig(null, event.getSuggestedConfigurationFile());
    	PROXY.preInit(event);
    	
    	
    	/*
    	ConfigManager.preInit(event);
    	proxy.init();
    	
    	OrbsatContents.preInit();
    	//IEAdvancements.preInit();
    	OrbsatCompatModule.doModulesPreInit();
    	*/
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	PROXY.init(event);
    	
    	/*
        proxy.preInitEnd();
        OrbsatContents.init();
        
        MinecraftForge.EVENT_BUS.register(new EventHandler());
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.init();
		
		//IESounds.init();
		
		OrbsatCompatModule.doModulesInit();
		proxy.initEnd();
		
		int messageId = 0;
		packetHandler.registerMessage(MessageTileSync.HandlerServer.class, MessageTileSync.class, messageId++, Side.SERVER);
		packetHandler.registerMessage(MessageTileSync.HandlerClient.class, MessageTileSync.class, messageId++, Side.CLIENT);
		packetHandler.registerMessage(MessageRequestBlockUpdate.Handler.class, MessageRequestBlockUpdate.class, messageId++, Side.SERVER);
		*/
    }
    
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		PROXY.postInit(event);
		
		/*
		OrbsatContents.postInit();
		proxy.postInit();
		OrbsatCompatModule.doModulesPostInit();
		proxy.postInitEnd();
		*/
	}
	
	/*
	@Mod.EventHandler
	public void loadComplete(FMLLoadCompleteEvent event)
	{
		OrbsatCompatModule.doModulesLoadComplete();
	}
	*/
	
	/*
	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		proxy.serverStarting();
		event.registerServerCommand(new CommandHandler(false));
	}
	
	@Mod.EventHandler
	public void serverStarted(FMLServerStartedEvent event)
	{
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
		{
			World world = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld();
			if(!world.isRemote)
			{
				LogHelper.info("WorldData loading");

				//Clear out any info from previous worlds
				//for(int dim : ImmersiveNetHandler.INSTANCE.getRelevantDimensions())
				//	ImmersiveNetHandler.INSTANCE.clearAllConnections(dim);
				
				OrbsatSaveData worldData = (OrbsatSaveData)world.loadData(OrbsatSaveData.class, OrbsatSaveData.dataName);

				if(worldData==null)
				{
					LogHelper.info("WorldData not found");
					worldData = new OrbsatSaveData(OrbsatSaveData.dataName);
					world.setData(OrbsatSaveData.dataName, worldData);
				}
				else
					LogHelper.info("WorldData retrieved");
				OrbsatSaveData.setInstance(world.provider.getDimension(), worldData);
			}
		}
	}
	*/
	
	@Mod.EventHandler
	public void wrongSignature(FMLFingerprintViolationEvent event) {
		System.out.println("[ORBSAT][WARN] THIS IS NOT AN OFFICIAL BUILD OF ORBITAL SATELLITE! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported!");
	}
}
