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
	
	public static final String CHAT = "chat." + MODID + ".";
	public static final String CHAT_WARN = CHAT+"warning.";
	public static final String CHAT_INFO = CHAT+"info.";
	public static final String CHAT_COMMAND = CHAT+"command.";

	public static final String DESC = "desc." + MODID + ".";
	public static final String DESC_INFO = DESC+"info.";
	public static final String DESC_FLAVOUR = DESC+"flavour.";

	public static final String GUI = "gui." + MODID + ".";
	public static final String GUI_CONFIG = "gui." + MODID + ".config.";
	
	public static ArrayList<Item> registeredOSItems = new ArrayList<Item>();
	public static ArrayList<Block> registeredOSBlocks = new ArrayList<Block>();
}
