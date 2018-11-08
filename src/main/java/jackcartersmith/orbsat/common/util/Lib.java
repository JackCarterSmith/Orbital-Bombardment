package jackcartersmith.orbsat.common.util;

import jackcartersmith.orbsat.OrbitalSatellite;

public class Lib {
	public static final String MODID = OrbitalSatellite.MODID;

	public static final String[] METALS_IE = {"Copper", "Aluminum", "Lead", "Silver", "Nickel", "Uranium", "Constantan", "Electrum", "Steel"};
	public static final String[] METALS_ALL = {"Copper", "Aluminum", "Lead", "Silver", "Nickel", "Uranium", "Constantan", "Electrum", "Steel", "Iron", "Gold"};

	public static final String TOOL_HAMMER = "IE_HAMMER";
	public static final String TOOL_WIRECUTTER = "IE_WIRECUTTER";

	public static final String CHAT = "chat."+MODID+".";
	public static final String CHAT_WARN = CHAT+"warning.";
	public static final String CHAT_INFO = CHAT+"info.";
	public static final String CHAT_COMMAND = CHAT+"command.";

	public static final String DESC = "desc."+MODID+".";
	public static final String DESC_INFO = DESC+"info.";
	public static final String DESC_FLAVOUR = DESC+"flavour.";

	public static final String GUI = "gui."+MODID+".";
	public static final String GUI_CONFIG = "gui."+MODID+".config.";
	
	public static final int COLOUR_I_ImmersiveOrange = 0xfff78034;
	public static final float[] COLOUR_F_ImmersiveOrange = {247/255f, 128/255f, 52/255f};
	public static final int COLOUR_I_ImmersiveOrangeShadow = 0xff3e200d;
	
	/**
	 * Gui IDs
	 */
	//Tiles
	public static final int GUIID_Base_Tile = 0;
	
	//Items
	public static final int GUIID_Base_Item = 64;
	
		
	public static final int colour_nixieTubeText = 0xff9900;
}
