package jackcartersmith.orbsat.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.MissingMappings.Mapping;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class OrbsatContents {
	public static ArrayList<Block> registeredOrbsatBlocks = new ArrayList<Block>();
	public static ArrayList<Item> registeredOrbsatItems = new ArrayList<Item>();
	public static List<Class<? extends TileEntity>> registeredOrbsatTiles = new ArrayList<>();
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for(Block block : registeredOrbsatBlocks)
			event.getRegistry().register(block.setRegistryName(createRegistryName(block.getTranslationKey())));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		for(Item item : registeredOrbsatItems)
			event.getRegistry().register(item.setRegistryName(createRegistryName(item.getTranslationKey())));

		//registerOres();
	}

	@SubscribeEvent
	public static void missingItems(RegistryEvent.MissingMappings<Item> event)
	{
		Set<String> knownMissing = ImmutableSet.of(
				"fluidethanol",
				"fluidconcrete",
				"fluidbiodiesel",
				"fluidplantoil",
				"fluidcreosote"
		);
		for(Mapping<Item> missing : event.getMappings())
			if(knownMissing.contains(missing.key.getPath()))
				missing.ignore();
	}
	
	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
	{
		
	}

	private static ResourceLocation createRegistryName(String unlocalized)
	{
		unlocalized = unlocalized.substring(unlocalized.indexOf("orbsat"));
		unlocalized = unlocalized.replaceFirst("\\.", ":");
		return new ResourceLocation(unlocalized);
	}
	
	public static void registerTile(Class<? extends TileEntity> tile)
	{
		String s = tile.getSimpleName();
		s = s.substring(s.indexOf("TileEntity")+"TileEntity".length());
		GameRegistry.registerTileEntity(tile, OrbitalSatellite.MODID+":"+s);
		//registeredIETiles.add(tile);
	}
	
	public static void preInit(){
		
	}
	
	public static void init(){
		
	}
	
	public static void postInit(){
		
	}
}
