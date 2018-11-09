package jackcartersmith.orbsat.common.compat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import jackcartersmith.orbsat.common.util.ConfigManager;
import jackcartersmith.orbsat.common.util.LogHelper;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class OrbsatCompatModule {
	public static HashMap<String, Class<? extends OrbsatCompatModule>> moduleClasses = new HashMap<String, Class<? extends OrbsatCompatModule>>();
	public static Set<OrbsatCompatModule> modules = new HashSet<OrbsatCompatModule>();
	
	static
	{
		moduleClasses.put("albedo", AlbedoHelper.class);
		//moduleClasses.put("baubles", BaublesHelper.class);
		//moduleClasses.put("opencomputers", OCHelper.class);
		moduleClasses.put("waila", WailaHelper.class);
	}
	
	public static void doModulesPreInit()
	{
		for(Entry<String, Class<? extends OrbsatCompatModule>> e : moduleClasses.entrySet())
			if(Loader.isModLoaded(e.getKey()))
				try
				{
					//IC2 Classic is not supported.
					if("ic2".equals(e.getKey())&&Loader.isModLoaded("ic2-classic-spmod"))
						continue;

					Boolean enabled = ConfigManager.OrbsatConfig.compat.get(e.getKey());
					if(enabled==null||!enabled)
						continue;
					OrbsatCompatModule m = e.getValue().newInstance();
					modules.add(m);
					m.preInit();
				} catch(Exception exception)
				{
					LogHelper.logger.error("Compat module for "+e.getKey()+" could not be preInitialized. Report this and include the error message below!", exception);
				}
	}

	public static void doModulesInit()
	{
		for(OrbsatCompatModule compat : OrbsatCompatModule.modules)
			try
			{
				compat.init();
			} catch(Exception exception)
			{
				LogHelper.logger.error("Compat module for "+compat+" could not be initialized. Report this and include the error message below!", exception);
			}
	}

	public static void doModulesPostInit()
	{
		for(OrbsatCompatModule compat : OrbsatCompatModule.modules)
			try
			{
				compat.postInit();
			} catch(Exception exception)
			{
				LogHelper.logger.error("Compat module for "+compat+" could not be postInitialized. Report this and include the error message below!", exception);
			}
	}
	
	public static boolean serverStartingDone = false;

	public static void doModulesLoadComplete()
	{
		if(!serverStartingDone)
		{
			serverStartingDone = true;
			for(OrbsatCompatModule compat : OrbsatCompatModule.modules)
				try
				{
					compat.loadComplete();
				} catch(Exception exception)
				{
					LogHelper.logger.error("Compat module for "+compat+" could not be initialized. Report this and include the error message below!", exception);
				}
		}
	}
	
	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

	public void loadComplete() {}

	@SideOnly(Side.CLIENT)
	public void clientPreInit() {}

	@SideOnly(Side.CLIENT)
	public void clientInit() {}

	@SideOnly(Side.CLIENT)
	public void clientPostInit() {}
}
