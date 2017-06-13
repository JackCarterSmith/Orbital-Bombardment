package fr.jackcartersmith.orbsat;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class OSRefs {
	public static final String MODID = "orbsat";
	public static final String NAME = "Orbital Satellite";
	public static final String VERSION = "0.1";
	
	public static final String CLIENT_PROXY_CLASS = "fr.jackcartersmith.orbsat.client.ClientProxy";
	public static final String COMMON_PROXY_CLASS = "fr.jackcartersmith.orbsat.common.CommonProxy";
	
	public static ArrayList<Item> registeredOSItems = new ArrayList<Item>();
	public static ArrayList<Block> registeredOSBlocks = new ArrayList<Block>();
}
