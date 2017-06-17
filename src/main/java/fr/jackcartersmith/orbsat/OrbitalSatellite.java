package fr.jackcartersmith.orbsat;

import fr.jackcartersmith.orbsat.common.CommonProxy;
import fr.jackcartersmith.orbsat.common.util.OSLogger;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

@Mod(modid=OSRefs.MODID, name=OSRefs.NAME, version=OSRefs.VERSION, dependencies = "required-after:Forge@[12.18.3.2185,)")
public class OrbitalSatellite {	
	
	@Mod.Instance(OSRefs.MODID)
	public static OrbitalSatellite INSTANCE;
	
	@SidedProxy(clientSide=OSRefs.CLIENT_PROXY_CLASS, serverSide=OSRefs.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	public static final SimpleNetworkWrapper packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(OSRefs.MODID);
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		OSLogger.info("[OrbSAT] Connection of primary laser chamber...");
		OSItems.init();
		proxy.preInit();
	}
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		OSLogger.info("[OrbSAT] Sending satellite in orbit...");
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		OSLogger.info("[OrbSAT] Charging satellite for first laser strike...");
	}
	/* 
	 * It's a begin for support 1.10 and 1.11 simultaly
	 * 
	 * 
    @Mod.EventHandler
    public void onMissingMapping(FMLMissingMappingsEvent event) {
        OSLogger.warn("[OrbSAT] Repairing missing mappings...");
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            String resourcePath = mapping.resourceLocation.getResourcePath().toLowerCase();
            if (mapping.type == GameRegistry.Type.BLOCK) {
                if ("newblockname".equals(resourcePath)) {
                    mapping.remap(OSBlocks.myNewBlock);
                }
            } else if (mapping.type == GameRegistry.Type.ITEM) {
                if ("newitemname".equals(resourcePath)) {
                    mapping.remap(OSItems.myNewItem);
                } else if ("newblockname".equals(resourcePath)) {
                    mapping.remap(Item.getItemFromBlock(OSBlocks.myNewBlock));
                }
            }
        }
    }
    */
	
	public static <T extends IForgeRegistryEntry<?>> T register(T object, String name)
	{
		return registerByFullName(object, OSRefs.MODID+":"+name);
	}
	public static <T extends IForgeRegistryEntry<?>> T registerByFullName(T object, String name)
	{
		object.setRegistryName(new ResourceLocation(name));
		OSLogger.debug("[OrbSAT] Register item "+object.getRegistryName());
		return GameRegistry.register(object);
	}
	public static Block registerBlockByFullName(Block block, ItemBlock itemBlock, String name)
	{
		block = registerByFullName(block, name);
		registerByFullName(itemBlock, name);
		return block;
	}
	public static Block registerBlockByFullName(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		try{
			return registerBlockByFullName(block, itemBlock.getConstructor(Block.class).newInstance(block), name);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	public static Block registerBlock(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		try{
			return registerBlockByFullName(block, itemBlock.getConstructor(Block.class).newInstance(block), OSRefs.MODID+":"+name);
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	
	public static CreativeTabs creativeTab = new CreativeTabs(OSRefs.MODID)
	{
		@Override
		public Item getTabIconItem()
		{
			return null;
		}
		
		@Override
		public ItemStack getIconItemStack()
		{
			return new ItemStack(OSItems.satelliteItem,1,0);
		}
	};
}
