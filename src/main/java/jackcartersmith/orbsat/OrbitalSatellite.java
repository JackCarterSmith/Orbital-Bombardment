package jackcartersmith.orbsat;

import jackcartersmith.orbsat.common.util.ConfigManager;
import jackcartersmith.orbsat.common.CommonProxy;
import jackcartersmith.orbsat.common.EventHandler;
import jackcartersmith.orbsat.common.OrbsatContents;
import jackcartersmith.orbsat.common.OrbsatSaveData;
import jackcartersmith.orbsat.common.commands.CommandHandler;
import jackcartersmith.orbsat.common.compat.OrbsatCompatModule;
import jackcartersmith.orbsat.common.util.LogHelper;
import jackcartersmith.orbsat.common.util.network.MessageRequestBlockUpdate;
import jackcartersmith.orbsat.common.util.network.MessageTileSync;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = OrbitalSatellite.MODID, name = OrbitalSatellite.NAME, version = OrbitalSatellite.VERSION, modLanguage = "java", 
		certificateFingerprint = "3ce4f2265ef35e83d8d32f87fa6394a56e9b0e49", dependencies = "required-after:forge@[14.23.5.2768,)")
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
	
	@Mod.EventHandler
	public void loadComplete(FMLLoadCompleteEvent event)
	{
		OrbsatCompatModule.doModulesLoadComplete();
	}
	
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

				/*
				//Clear out any info from previous worlds
				for(int dim : ImmersiveNetHandler.INSTANCE.getRelevantDimensions())
					ImmersiveNetHandler.INSTANCE.clearAllConnections(dim);
				*/
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
		System.out.println("[OrbSat/Error] THIS IS NOT AN OFFICIAL BUILD OF ORBITAL SATELLITE! Found these fingerprints: "+event.getFingerprints());
	}
}
