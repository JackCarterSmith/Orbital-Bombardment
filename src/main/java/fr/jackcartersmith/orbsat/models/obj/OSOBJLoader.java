package fr.jackcartersmith.orbsat.models.obj;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import fr.jackcartersmith.orbsat.common.util.OSLogger;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;

public class OSOBJLoader implements ICustomModelLoader{
	private IResourceManager manager;
	private final Set<String> enabledDomains = new HashSet<String>();
	private final Map<ResourceLocation, OSOBJModel> cache = new HashMap<ResourceLocation, OSOBJModel>();
	private final Map<ResourceLocation, Exception> errors = new HashMap<ResourceLocation, Exception>();
	public static OSOBJLoader instance = new OSOBJLoader();

	public void addDomain(String domain)
	{
		enabledDomains.add(domain.toLowerCase(Locale.ENGLISH));
		OSLogger.info("[OrbSAT] Custom OBJLoader: Domain has been added: "+domain.toLowerCase(Locale.ENGLISH));
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		return enabledDomains.contains(modelLocation.getResourceDomain()) && modelLocation.getResourcePath().endsWith(".obj.ie");
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		ResourceLocation file = new ResourceLocation(modelLocation.getResourceDomain(), modelLocation.getResourcePath());
		if(!cache.containsKey(file))
		{
			IModel model = OBJLoader.INSTANCE.loadModel(modelLocation);
			if(model instanceof OBJModel)
			{
				OSOBJModel ieobj = new OSOBJModel(((OBJModel)model).getMatLib(), file);
				cache.put(modelLocation, ieobj);
			}
		}
		OSOBJModel model = cache.get(file);
		if(model == null)
			return ModelLoaderRegistry.getMissingModel();
		return model;
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{
			this.manager = resourceManager;
			cache.clear();
			errors.clear();
	}
}
