package jackcartersmith.orbsat.common;

import java.util.LinkedList;
import java.util.List;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.common.blocks.info.IBlockInfo;
import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import jackcartersmith.orbsat.common.utils.MessageConfigSync;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	protected List<Item> itemsToRegister = new LinkedList<>();
    protected List<BlockOrbsatBase> blocksToRegister = new LinkedList<>();
    
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		
		/*
		 * REGISTER NETWORK PACKET HERE!
		 */
		/*
		int id = 0;
		
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageTileDataParameter.class, MessageTileDataParameter.class, id++, Side.CLIENT);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageTileDataParameterUpdate.class, MessageTileDataParameterUpdate.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridItemInsertHeld.class, MessageGridItemInsertHeld.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridItemPull.class, MessageGridItemPull.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridClear.class, MessageGridClear.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridTransfer.class, MessageGridTransfer.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridSettingsUpdate.class, MessageGridSettingsUpdate.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridCraftingStart.class, MessageGridCraftingStart.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridPatternCreate.class, MessageGridPatternCreate.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageCraftingMonitorCancel.class, MessageCraftingMonitorCancel.class, id++, Side.SERVER);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageCraftingMonitorElements.class, MessageCraftingMonitorElements.class, id++, Side.CLIENT);
        OrbitalSatellite.INSTANCE.packetHandler.registerMessage(MessageGridItemUpdate.class, MessageGridItemUpdate.class, id++, Side.CLIENT);
        */
		
		
		/*
		 * REGISTER THE GUI HANDLER (Dispatcher between all mod's GUI when you clic a block)
		 */
		//NetworkRegistry.INSTANCE.registerGuiHandler(OrbitalSatellite.INSTANCE, new GuiHandler());
		
		
		/*
		 * REGISTER BLOCKS HERE!
		 */
		registerBlock(OrbsatBlocks.BLOCK_CRYSTAL);
        

        /*
         * REGISTER ITEMS HERE!
         */
        //registerItem(OrbsatItems.QUARTZ_ENRICHED_IRON);
        //registerItem(OrbsatItems.PROCESSOR_BINDING);
		
	}
	
	public void init(FMLInitializationEvent event) {
		/*
		 * REGISTER SMELTING HERE!
		 */
		/*
        GameRegistry.addSmelting(Items.QUARTZ, new ItemStack(RSItems.SILICON), 0.5F);

        GameRegistry.addSmelting(new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_CUT_BASIC), new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_BASIC), 0.5F);
        GameRegistry.addSmelting(new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_CUT_IMPROVED), new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_IMPROVED), 0.5F);
        GameRegistry.addSmelting(new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_CUT_ADVANCED), new ItemStack(RSItems.PROCESSOR, 1, ItemProcessor.TYPE_ADVANCED), 0.5F);
        */

        /*
         * Integration with other mods like OC
         */
        /*
        if (IntegrationOC.isLoaded()) {
            DriverNetwork.register();
        }
        */
    }
	
	public void postInit(FMLPostInitializationEvent event) {}
	
    private void registerBlock(BlockOrbsatBase block) {
        blocksToRegister.add(block);

        registerItem(block.createItem());

        if (block.getInfo().hasTileEntity()) {
            registerTile(block.getInfo());
        }
    }

    private void registerItem(Item item) {
        itemsToRegister.add(item);
    }
	
	@SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        blocksToRegister.forEach(event.getRegistry()::register);
    }
	
    private void registerTile(IBlockInfo info) {
        Class<? extends TileEntityOrbsatBase> clazz = info.createTileEntity().getClass();

        GameRegistry.registerTileEntity(clazz, info.getId());

        /*
        try {
        	TileEntityOrbsatBase tileInstance = clazz.newInstance();

            if (tileInstance instanceof TileNode) {
                API.instance().getNetworkNodeRegistry().add(((TileNode) tileInstance).getNodeId(), (tag, world, pos) -> {
                    NetworkNode node = ((TileNode) tileInstance).createNode(world, pos);

                    node.read(tag);

                    return node;
                });
            }

            tileInstance.getDataManager().getParameters().forEach(TileDataManager::registerParameter);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        */
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        itemsToRegister.forEach(event.getRegistry()::register);

        /*
         * REGISTER NEW ORES HERE!
         */
        //OreDictionary.registerOre("itemSilicon", RSItems.SILICON);
    }
    
    @SubscribeEvent
    public void registerRecipes(RegistryEvent.Register<IRecipe> event) {
    	/*
    	 * REGISTER SPECIALS RECIPES HERE!
    	 */
    	/*
        e.getRegistry().register(new RecipeCover().setRegistryName(new ResourceLocation(RS.ID, "cover")));
        e.getRegistry().register(new RecipeHollowCover().setRegistryName(new ResourceLocation(RS.ID, "hollow_cover")));
        e.getRegistry().register(new RecipeUpgradeWithEnchantedBook("fortune", 1, ItemUpgrade.TYPE_FORTUNE_1).setRegistryName(new ResourceLocation(RS.ID, "fortune_1_upgrade")));
        e.getRegistry().register(new RecipeUpgradeWithEnchantedBook("fortune", 2, ItemUpgrade.TYPE_FORTUNE_2).setRegistryName(new ResourceLocation(RS.ID, "fortune_2_upgrade")));
        e.getRegistry().register(new RecipeUpgradeWithEnchantedBook("fortune", 3, ItemUpgrade.TYPE_FORTUNE_3).setRegistryName(new ResourceLocation(RS.ID, "fortune_3_upgrade")));
        e.getRegistry().register(new RecipeUpgradeWithEnchantedBook("silk_touch", 1, ItemUpgrade.TYPE_SILK_TOUCH).setRegistryName(new ResourceLocation(RS.ID, "silk_touch_upgrade")));
        */
    }
    
    @SubscribeEvent
    public void onHarvestCheck(PlayerEvent.HarvestCheck event) {
        if (event.getTargetBlock().getBlock() instanceof Block) {
            event.setCanHarvest(true); // Allow break without tool
        }
    }

    @SubscribeEvent
    public void onPlayerLoginEvent(net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.world.isRemote) {
            OrbitalSatellite.INSTANCE.packetHandler.sendTo(new MessageConfigSync(), (EntityPlayerMP) event.player);		// Sync config between server and client?
        }
    }

    @SubscribeEvent
    public void onPlayerLogoutEvent(WorldEvent.Unload event) {
        if (event.getWorld().isRemote && OrbitalSatellite.INSTANCE.config.getOriginalClientVersion() != null) {
        	OrbitalSatellite.INSTANCE.config = OrbitalSatellite.INSTANCE.config.getOriginalClientVersion();
        }
    }
    
    /*
    @SubscribeEvent
    public void fixItemMappings(RegistryEvent.MissingMappings<Item> e) {
    	// MANAGE SPECIAL UPDATE WITH MISSING MAPPING DU TO REMOVAL
    	
        for (RegistryEvent.MissingMappings.Mapping<Item> missing : e.getMappings()) {
            if (missing.key.getNamespace().equals(OrbitalSatellite.MODID) && missing.key.getPath().equals("solderer")) {
                missing.ignore();
            }
        }
    }

    @SubscribeEvent
    public void fixBlockMappings(RegistryEvent.MissingMappings<Block> e) {
        // MANAGE SPECIAL UPDATE WITH MISSING MAPPING DU TO REMOVAL

        for (RegistryEvent.MissingMappings.Mapping<Block> missing : e.getMappings()) {
            if (missing.key.getNamespace().equals(OrbitalSatellite.MODID) && missing.key.getPath().equals("solderer")) {
                missing.ignore();
            }
        }
    }
    */
}
