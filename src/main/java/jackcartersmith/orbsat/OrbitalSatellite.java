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

@Mod(modid = OrbitalSatellite.MODID, name = OrbitalSatellite.NAME, version = OrbitalSatellite.VERSION, modLanguage = "java", 
certificateFingerprint = "30f9f06606e9ab799c59ec743cab264c8310531d", dependencies = "required-after:forge@[14.23.5.2838,)")
public class OrbitalSatellite {
    public static final String MODID = "orbsat";
    public static final String NAME = "Orbital Satellite";
    public static final String VERSION = "${version}";
    
    @Mod.Instance(MODID)
	public static OrbitalSatellite instance = new OrbitalSatellite();
	@SidedProxy(clientSide = "jackcartersmith.orbsat.client.ClientProxy", serverSide = "jackcartersmith.orbsat.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	LogHelper.logger = event.getModLog();
    	ConfigManager.preInit(event);
    	proxy.init();
    	
    	OrbsatContents.preInit();
    	//IEAdvancements.preInit();
    	OrbsatCompatModule.doModulesPreInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
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
    }
    
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		OrbsatContents.postInit();
		proxy.postInit();
		OrbsatCompatModule.doModulesPostInit();
		proxy.postInitEnd();
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
	public void modIDMapping(FMLModIdMappingEvent event) {}
	
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
	public void wrongSignature(FMLFingerprintViolationEvent event)
	{
		if (!event.isDirectory())
			System.out.println("[ORBSAT][CAUTION] THIS IS NOT AN OFFICIAL BUILD OF ORBITAL SATELLITE! Unvalid file " + event.getSource().getName());
	}
}
