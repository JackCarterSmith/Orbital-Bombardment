package fr.jackcartersmith.orbsat.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.client.gui.OSGuiHandler;
import fr.jackcartersmith.orbsat.client.render.particle.ParticleLaserBeam;
import fr.jackcartersmith.orbsat.common.handler.FMLEventHandler;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import fr.jackcartersmith.orbsat.common.network.PhotonPacket;
import fr.jackcartersmith.orbsat.common.network.TileObjectPacket;
import fr.jackcartersmith.orbsat.common.tileentities.TileDefender;
import fr.jackcartersmith.orbsat.common.utils.OSLogHelper;
import net.minecraft.client.audio.ISound;
import net.minecraft.world.World;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
		OSConstants.init(event.getSuggestedConfigurationFile());
		registerEventListeners(event.getSide());
		OSBlocks.init();
		OSItems.init();
        registerTileEntities();
        initializeNetwork();
        //registerOres();
		
		OSLogHelper.info("Finished PreInitialization");
    }
    
    public void init(FMLInitializationEvent event) {
    	//CraftingHandler.init();
        registerGuiHandeler();
        //registerWorldGen();
        registerEntities();

        OSLogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        //Achievements.registerAchievementPane();
        
        OSLogHelper.info("Finished PostInitialization");
    }
    
    public void initializeNetwork() {
        OrbitalSatellite.network = NetworkRegistry.INSTANCE.newSimpleChannel(OrbitalSatellite.networkChannelName);
        OrbitalSatellite.network.registerMessage(TileObjectPacket.Handler.class, TileObjectPacket.class, 0, Side.SERVER);
        OrbitalSatellite.network.registerMessage(TileObjectPacket.Handler.class, TileObjectPacket.class, 1, Side.CLIENT);
        OrbitalSatellite.network.registerMessage(PhotonPacket.Handler.class, PhotonPacket.class, 10, Side.SERVER);
        OrbitalSatellite.network.registerMessage(PhotonPacket.Handler.class, PhotonPacket.class, 11, Side.CLIENT);
    }
    
    public void registerTileEntities() {
    	GameRegistry.registerTileEntity(TileDefender.class, OSRefs.RESOURCESPREFIX + "TileDefender");
    	
    	
    	
    	/*
        GameRegistry.registerTileEntity(TileWeatherController.class, References.RESOURCESPREFIX + "TileWeatherController");
        GameRegistry.registerTileEntity(TileSunDial.class, References.RESOURCESPREFIX + "TileSunDial");
        GameRegistry.registerTileEntity(TileGrinder.class, References.RESOURCESPREFIX + "TileGrinder");
        GameRegistry.registerTileEntity(TilePotentiometer.class, References.RESOURCESPREFIX + "TilePotentiometer");
        GameRegistry.registerTileEntity(TileParticleGenerator.class, References.RESOURCESPREFIX + "TileParticleGenerator");
        GameRegistry.registerTileEntity(TilePlayerDetector.class, References.RESOURCESPREFIX + "TilePlayerDetector");
        GameRegistry.registerTileEntity(TilePlayerDetectorAdvanced.class, References.RESOURCESPREFIX + "TilePlayerDetectorAdvanced");
        GameRegistry.registerTileEntity(TileEnergyInfuser.class, References.RESOURCESPREFIX + "TileEnergyInfuser");
        GameRegistry.registerTileEntity(TileCustomSpawner.class, References.RESOURCESPREFIX + "TileCustomSpawner");
        GameRegistry.registerTileEntity(TileGenerator.class, References.RESOURCESPREFIX + "TileGenerator");
        GameRegistry.registerTileEntity(TileEnergyStorageCore.class, References.RESOURCESPREFIX + "TileEnergyStorageCore");
        GameRegistry.registerTileEntity(TileInvisibleMultiblock.class, References.RESOURCESPREFIX + "TileInvisibleMultiblock");
        GameRegistry.registerTileEntity(TileEnergyPylon.class, References.RESOURCESPREFIX + "TileEnergyPylon");
        GameRegistry.registerTileEntity(TileEnderResurrection.class, References.RESOURCESPREFIX + "TileEnderResurrection");
        GameRegistry.registerTileEntity(TilePlacedItem.class, References.RESOURCESPREFIX + "TilePlacedItem");
        GameRegistry.registerTileEntity(TileCKeyStone.class, References.RESOURCESPREFIX + "TileCKeyStone");
        GameRegistry.registerTileEntity(TileDissEnchanter.class, References.RESOURCESPREFIX + "TileDissEnchanter");
        GameRegistry.registerTileEntity(TileTeleporterStand.class, References.RESOURCESPREFIX + "TileTeleporterStand");
        GameRegistry.registerTileEntity(TileDraconiumChest.class, References.RESOURCESPREFIX + "TileDraconiumChest");
        GameRegistry.registerTileEntity(TileEnergyRelay.class, References.RESOURCESPREFIX + "TileEnergyRelay");
        GameRegistry.registerTileEntity(TileEnergyTransceiver.class, References.RESOURCESPREFIX + "TileEnergyTransceiver");
        GameRegistry.registerTileEntity(TileWirelessEnergyTransceiver.class, References.RESOURCESPREFIX + "TileWirelessEnergyTransceiver");
        GameRegistry.registerTileEntity(TileDislocatorReceptacle.class, References.RESOURCESPREFIX + "TileDislocatorReceptacle");
        GameRegistry.registerTileEntity(TilePortalBlock.class, References.RESOURCESPREFIX + "TilePortalBlock");
        GameRegistry.registerTileEntity(TileReactorCore.class, References.RESOURCESPREFIX + "TileReactorCore");
        GameRegistry.registerTileEntity(TileFluxGate.class, References.RESOURCESPREFIX + "TileFluxGate");
        GameRegistry.registerTileEntity(TileFluidGate.class, References.RESOURCESPREFIX + "TileFluidGate");
        GameRegistry.registerTileEntity(TileReactorStabilizer.class, References.RESOURCESPREFIX + "TileReactorStabilizer");
        GameRegistry.registerTileEntity(TileReactorEnergyInjector.class, References.RESOURCESPREFIX + "TileReactorEnergyInjector");
        GameRegistry.registerTileEntity(TileChaosShard.class, References.RESOURCESPREFIX + "TileChaosShard");
        GameRegistry.registerTileEntity(TileUpgradeModifier.class, References.RESOURCESPREFIX + "TileEnhancementModifier");
        */
    }
    
    public void registerEventListeners(Side s) {
        //MinecraftForge.EVENT_BUS.register(new MinecraftForgeEventHandler());
        //MinecraftForge.EVENT_BUS.register(new Achievements());
        //FMLCommonHandler.instance().bus().register(new Achievements());
        FMLCommonHandler.instance().bus().register(new FMLEventHandler());
    }
    
    public void registerGuiHandeler() {
        new OSGuiHandler();
    }
    
    //@Callback
    public void registerEntities() {
	    //EntityRegistry.registerModEntity(MeteorEntity.class, "meteor", 0, OrbitalSatellite.instance, 256, 3, true);
	    //EntityRegistry.registerModEntity(LaserLightEntity.class, "LaserLightEntity", 1, OrbitalSatellite.instance, 256, 3, true);
    	
    	
    	
    	
    	/*
        EntityRegistry.registerModEntity(EntityCustomDragon.class, "EnderDragon", 0, DraconicEvolution.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityPersistentItem.class, "Persistent Item", 1, DraconicEvolution.instance, 32, 5, true);
        EntityRegistry.registerModEntity(EntityDraconicArrow.class, "Arrow", 2, DraconicEvolution.instance, 32, 5, true);
        EntityRegistry.registerModEntity(EntityEnderArrow.class, "Ender Arrow", 3, DraconicEvolution.instance, 32, 1, true);
        //EntityRegistry.registerModEntity(EntityChaosDrill.class, "Chaos Drill", 4, DraconicEvolution.instance, 10, 5, false);
        EntityRegistry.registerModEntity(EntityDragonHeart.class, "Dragon Heart Item", 5, DraconicEvolution.instance, 32, 5, true);
        EntityRegistry.registerModEntity(EntityChaosGuardian.class, "ChaosGuardian", 6, DraconicEvolution.instance, 256, 1, true);
        EntityRegistry.registerModEntity(EntityDragonProjectile.class, "DragonProjectile", 7, DraconicEvolution.instance, 256, 1, true);
        EntityRegistry.registerModEntity(EntityChaosCrystal.class, "ChaosCrystal", 8, DraconicEvolution.instance, 256, 5, false);
        EntityRegistry.registerModEntity(EntityChaosBolt.class, "ChaosBolt", 9, DraconicEvolution.instance, 32, 5, true);
        EntityRegistry.registerModEntity(EntityChaosVortex.class, "EntityChaosEnergyVortex", 10, DraconicEvolution.instance, 512, 5, true);
        EntityRegistry.registerModEntity(EntityCustomArrow.class, "CustomArrow", 11, DraconicEvolution.instance, 128, 1, true);
        */
    }
    
    /*
     * ------------- Code inspiration pour le faisceau d'energie du satellite
     * 
    public ParticleEnergyBeam energyBeam(World worldObj, double x, double y, double z, double tx, double ty, double tz, int powerFlow, boolean advanced, ParticleEnergyBeam oldBeam, boolean render, int beamType) {
        return null;
    }

    public ParticleEnergyField energyField(World worldObj, double x, double y, double z, int type, boolean advanced, ParticleEnergyField oldBeam, boolean render) {
        return null;
    }

    public ParticleReactorBeam reactorBeam(TileEntity tile, ParticleReactorBeam oldBeam, boolean render) {
        return null;
    }
    */
    
    //public ParticleLaserBeam energyBeam(World worldObj, double x, double y, double z, double tx, double ty, double tz, int powerFlow, boolean advanced, ParticleLaserBeam oldBeam, boolean render, int beamType) {
    public ParticleLaserBeam energyBeam(World worldObj, double x, double y, double z, double tx, double ty, double tz, int powerFlow, int pulseLenght, ParticleLaserBeam oldBeam, boolean render, int beamType) {
        return null;
    }

    public void spawnParticle(Object particle, int range) {}

    public ISound playISound(ISound sound) {
        return null;
    }
}
