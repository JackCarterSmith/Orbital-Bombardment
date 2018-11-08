package jackcartersmith.orbsat.common.util;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Maps;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.common.compat.OrbsatCompatModule;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class ConfigManager {
	public static HashMap<String, Boolean> manual_bool = new HashMap<String, Boolean>();
	public static HashMap<String, Integer> manual_int = new HashMap<String, Integer>();
	public static HashMap<String, int[]> manual_intA = new HashMap<String, int[]>();
	public static HashMap<String, Double> manual_double = new HashMap<String, Double>();
	public static HashMap<String, double[]> manual_doubleA = new HashMap<String, double[]>();
	
	@net.minecraftforge.common.config.Config(modid = OrbitalSatellite.MODID)
	public static class OrbsatConfig {
		@Comment({"A list of all mods that OrbSat has integrated compatability for", "Setting any of these to false disables the respective compat"})
		public static Map<String, Boolean> compat = Maps.newHashMap(Maps.toMap(OrbsatCompatModule.moduleClasses.keySet(), (s) -> Boolean.TRUE));
		@Comment({"Increase the distance at which certain TileEntities (specifically windmills) are still visible. This is a modifier, so set it to 1 for default render distance, to 2 for doubled distance and so on."})
		public static double increasedTileRenderdistance = 1.5;
	}
	
	static Configuration config;

	public static void preInit(FMLPreInitializationEvent event)
	{
		onConfigUpdate();
	}
	
	private static void onConfigUpdate()
	{
		
	}
}
