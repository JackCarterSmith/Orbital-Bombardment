package jackcartersmith.orbsat;

import jackcartersmith.orbsat.common.CommonProxy;
import jackcartersmith.orbsat.common.util.LogHelper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = OrbitalSatellite.MODID, name = OrbitalSatellite.NAME, version = OrbitalSatellite.VERSION, dependencies = "required-after:forge@[14.23.5.2768,)")
public class OrbitalSatellite {
    public static final String MODID = "orbsat";
    public static final String NAME = "Orbital Satellite";
    public static final String VERSION = "${version}";
    
    @Mod.Instance(MODID)
	public static OrbitalSatellite instance = new OrbitalSatellite();
	@SidedProxy(clientSide = "jackcartersmith.orbsat.client.ClientProxy", serverSide = "jackcartersmith.orbsat.common.CommonProxy")
	public static CommonProxy proxy;
	
	public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	LogHelper.logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
    	LogHelper.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
    
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
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
}
