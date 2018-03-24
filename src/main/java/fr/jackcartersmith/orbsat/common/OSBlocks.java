package fr.jackcartersmith.orbsat.common;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.block.BlockOS;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

@GameRegistry.ObjectHolder(OSRefs.MODID)
public class OSBlocks {
   public static BlockOS laserLow;
   public static BlockOS overrider;
   public static BlockOS photonInverter;
   public static BlockOS photonInverterAdv;
   public static BlockOS extender;
   public static BlockOS furnace;
   public static BlockOS satelite;
   public static BlockOS photonDecelerator;
   public static BlockOS photonAccelerator;
   public static BlockOS defender;
   public static BlockOS laserDef;
   
   public static void init(){
	   laserLow = new LaserLowBlock(Material.fire).setBlockName("obLaserLow").setBlockTextureName(OSRefs.MODID + ":laserLow").setHardness(-1.0F).setResistance(-1.0F).setLightLevel(25.0F);;
	   GameRegistry.registerBlock(laserLow, "ob_laserLow");
	   GameRegistry.registerTileEntity(LaserLowTileEntity.class, "laserLowTileEntity");
	   
       laserDef = new DefLasBlock(Material.fire).setBlockName("obDefenderLaser").setBlockTextureName(OSRefs.MODID + ":laserLow").setHardness(10.0F).setResistance(25.0F).setLightLevel(5.0F).setCreativeTab(OrbitalSatellite.OBCreativeTabs);;
       GameRegistry.registerBlock(laserDef, "ob_defenderLaser");
       GameRegistry.registerTileEntity(DefenderLaserTileEntity.class, "defenderLaserTileEntity");
	       
	   overrider = new OverriderBlock(Material.anvil).setBlockName("obOverrider").setBlockTextureName(OSRefs.MODID + ":overrider").setHardness(10.0F).setResistance(25.0F).setCreativeTab(OrbitalSatellite.OBCreativeTabs);;
	   GameRegistry.registerBlock(overrider, "ob_overrider");
	   GameRegistry.registerTileEntity(OverriderBlockTileEntity.class, "overriderBlockTileEntity");
	       
	   photonInverter = new PhotonInverterBlock(Material.iron).setBlockName("obInverter").setBlockTextureName(OSRefs.MODID + ":solarPanel").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalSatellite.OBCreativeTabs);;
	   GameRegistry.registerBlock(photonInverter, "ob_inverter");
	   GameRegistry.registerTileEntity(InverterTileEntity.class, "inverter");
	       
	   photonInverterAdv = new PhotonInverterAdvBlock(Material.iron).setBlockName("obInverterAdv").setBlockTextureName(OSRefs.MODID + ":solarPanel").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalSatellite.OBCreativeTabs);;
	   GameRegistry.registerBlock(photonInverterAdv, "ob_photonInverterAdv");
	   GameRegistry.registerTileEntity(InverterAdvTileEntity.class, "inverterAdv");
	       
	   extender = new ExtenderBlock(Material.anvil).setBlockName("obExtender").setBlockTextureName(OSRefs.MODID + ":extender").setHardness(10.0F).setResistance(25.0F).setCreativeTab(OrbitalSatellite.OBCreativeTabs);;
	   GameRegistry.registerBlock(extender, "ob_extender");
	   GameRegistry.registerTileEntity(ExtenderTileEntity.class, "extenderTileEntity");
	       
	   satelite = new SateliteBlock(Material.anvil).setHardness(10.0F).setBlockName("obSatelite").setBlockTextureName(OSRefs.MODID + ":satelite").setResistance(25.0F).setCreativeTab(OrbitalSatellite.OBCreativeTabs);
	   GameRegistry.registerBlock(satelite, "ob_satelite");
	   GameRegistry.registerTileEntity(SateliteTileEntity.class, "sateliteTileEntity");
	       
	   photonDecelerator = new PhotonDeceleratorBlock(Material.iron).setBlockName("obDecelerator").setBlockTextureName(OSRefs.MODID + ":photonDecelerator").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalSatellite.OBCreativeTabs);
	   GameRegistry.registerBlock(photonDecelerator, "ob_decelerator");
	   GameRegistry.registerTileEntity(PhotonDeceleratorTileEntity.class, "PhotonDeceleratorTileEntity");
   
	   photonAccelerator = new PhotonAcceleratorBlock(Material.iron).setBlockName("obAccelerator").setBlockTextureName(OSRefs.MODID + ":photonAccelerator").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(OrbitalSatellite.OBCreativeTabs);
	   GameRegistry.registerBlock(photonAccelerator, "ob_accelerator");
	   GameRegistry.registerTileEntity(PhotonAcceleratorTileEntity.class, "PhotonAcceleratorTileEntity");

	   defender = new DefenderBlock(Material.anvil).setBlockName("obDefender").setBlockTextureName(OSRefs.MODID + ":defender").setLightLevel(0.2F).setResistance(25.0F).setHardness(10.0F).setCreativeTab(OrbitalSatellite.OBCreativeTabs);
	   GameRegistry.registerBlock(defender, "ob_defender");
	   GameRegistry.registerTileEntity(DefenderTileEntity.class, "DefenderTileEntity");
	   
	   
	   
	   
	   
	   /*
	    flowGate = new FlowGate();
        reactorStabilizer = new ReactorStabilizer();
        reactorEnergyInjector = new ReactorEnergyInjector();
        chaosCrystal = new ChaosCrystal();
        upgradeModifier = new UpgradeModifier();

        longRangeDislocator = new LongRangeDislocator();

        if (isEnabled(chaosShardAtmos)) GameRegistry.registerBlock(chaosShardAtmos, "chaosShardAtmos");

        if (DraconicEvolution.debug) {
            testBlock = new TestBlock();
            containerTemplate = new BlockContainerTemplate();
        }

        resurrectionStone = new ItemStack(ModBlocks.draconiumBlock, 1, 1);
		*/
   }
}
