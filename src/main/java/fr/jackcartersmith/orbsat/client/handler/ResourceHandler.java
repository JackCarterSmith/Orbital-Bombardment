package fr.jackcartersmith.orbsat.client.handler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.ResourceLocation;

public class ResourceHandler {
    public static ResourceHandler instance = new ResourceHandler();
    private static ResourceLocation defaultParticles;
    private static ResourceLocation particles = new ResourceLocation(OSRefs.RESOURCESPREFIX + "textures/particle/particles.png");
    private static Map<String, ResourceLocation> cachedResources = new HashMap<String, ResourceLocation>();

    private static String savePath;
    private static File saveFolder;


    //-------------------- File Handling -----------------------//

    public static void init(FMLPreInitializationEvent event) {
        FMLCommonHandler.instance().bus().register(instance);

    }
    
    public static File getConfigFolder() {
        if (saveFolder == null) {
            saveFolder = new File(savePath);
        }
        if (!saveFolder.exists()) saveFolder.mkdir();

        return saveFolder;
    }

    //----------------------------------------------------------//


    public static void bindTexture(ResourceLocation texture) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

    /**
     * Binds the vanilla particle sheet
     */
    public static void bindDefaultParticles() {
        if (defaultParticles == null) {
            try {
                defaultParticles = (ResourceLocation) ReflectionHelper.getPrivateValue(EffectRenderer.class, null, "particleTextures", "field_110737_b");
            }
            catch (Exception e) {
            }
        }
        if (defaultParticles != null) bindTexture(defaultParticles);
    }

    public static void bindParticles() {
        bindTexture(particles);
    }

    public static ResourceLocation getResource(String rs) {
        if (!cachedResources.containsKey(rs))
            cachedResources.put(rs, new ResourceLocation(OSRefs.RESOURCESPREFIX + rs));
        return cachedResources.get(rs);
    }

    public static ResourceLocation getResourceWOP(String rs) {
        if (!cachedResources.containsKey(rs)) cachedResources.put(rs, new ResourceLocation(rs));
        return cachedResources.get(rs);
    }

    public static void bindResource(String rs) {
        bindTexture(ResourceHandler.getResource(rs));
    }
}
