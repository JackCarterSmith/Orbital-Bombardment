package fr.jackcartersmith.ob.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class OBBlocks {
   public static Block laserLow;
   public static Block overrider;
   public static Block photonInverter;
   public static Block photonInverterAdv;
   public static Block extender;
   public static Block furnace;
   public static Block satelite;
   public static Block photonDecelerator;
   public static Block photonAccelerator;
   public static Block defender;
   public static Block laserDef;
   
   public static void init(){
	   laserLow = new LaserLowBlock(Material.air).setBlockName("obLaserLow").setBlockTextureName(OrbitalBombardment.MODID + ":laserLow").setHardness(-1.0F).setResistance(-1.0F).setLightLevel(25.0F);;
	   GameRegistry.registerBlock(laserLow, "ob_laserLow");
	   GameRegistry.registerTileEntity(LaserLowTileEntity.class, "laserLowTileEntity");
	   
       laserDef = new DefLasBlock(Material.air).setBlockName("obDefenderLaser").setBlockTextureName(OrbitalBombardment.MODID + ":laserLow").setHardness(10.0F).setResistance(25.0F).setLightLevel(5.0F);;
       GameRegistry.registerBlock(laserDef, "ob_defenderLaser");
       GameRegistry.registerTileEntity(DefenderLaserTileEntity.class, "defenderLaserTileEntity");
	       
	   overrider = new OverriderBlock(Material.anvil).setBlockName("obOverrider").setBlockTextureName(OrbitalBombardment.MODID + ":overrider").setHardness(10.0F).setResistance(25.0F).setCreativeTab(OrbitalBombardment.OBCreativeTabs);;
	   GameRegistry.registerBlock(overrider, "ob_overrider");
	   GameRegistry.registerTileEntity(OverriderBlockTileEntity.class, "overriderBlockTileEntity");
	       
	   photonInverter = new PhotonInverterBlock(Material.iron).setBlockName("obInverter").setBlockTextureName(OrbitalBombardment.MODID + ":solarPanel").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalBombardment.OBCreativeTabs);;
	   GameRegistry.registerBlock(photonInverter, "ob_inverter");
	   GameRegistry.registerTileEntity(InverterTileEntity.class, "inverter");
	       
	   photonInverterAdv = new PhotonInverterAdvBlock(Material.iron).setBlockName("obInverterAdv").setBlockTextureName(OrbitalBombardment.MODID + ":solarPanel").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalBombardment.OBCreativeTabs);;
	   GameRegistry.registerBlock(photonInverterAdv, "ob_photonInverterAdv");
	   GameRegistry.registerTileEntity(InverterAdvTileEntity.class, "inverterAdv");
	       
	   extender = new ExtenderBlock(Material.anvil).setBlockName("obExtender").setBlockTextureName(OrbitalBombardment.MODID + ":extender").setHardness(10.0F).setResistance(25.0F).setCreativeTab(OrbitalBombardment.OBCreativeTabs);;
	   GameRegistry.registerBlock(extender, "ob_extender");
	   GameRegistry.registerTileEntity(ExtenderTileEntity.class, "extenderTileEntity");
	       
	   satelite = new SateliteBlock(Material.anvil).setHardness(10.0F).setBlockName("obSatelite").setBlockTextureName(OrbitalBombardment.MODID + ":satelite").setResistance(25.0F).setCreativeTab(OrbitalBombardment.OBCreativeTabs);
	   GameRegistry.registerBlock(satelite, "ob_satelite");
	   GameRegistry.registerTileEntity(SateliteTileEntity.class, "sateliteTileEntity");
	       
	   photonDecelerator = new PhotonDeceleratorBlock(Material.iron).setBlockName("obDecelerator").setBlockTextureName(OrbitalBombardment.MODID + ":photonDecelerator").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setCreativeTab(OrbitalBombardment.OBCreativeTabs);
	   GameRegistry.registerBlock(photonDecelerator, "ob_decelerator");
	   GameRegistry.registerTileEntity(PhotonDeceleratorTileEntity.class, "PhotonDeceleratorTileEntity");
   
	   photonAccelerator = new PhotonAcceleratorBlock(Material.iron).setBlockName("obAccelerator").setBlockTextureName(OrbitalBombardment.MODID + ":photonAccelerator").setHardness(2.0F).setResistance(15.0F).setStepSound(Block.soundTypeMetal).setCreativeTab(OrbitalBombardment.OBCreativeTabs);
	   GameRegistry.registerBlock(photonAccelerator, "ob_accelerator");
	   GameRegistry.registerTileEntity(PhotonAcceleratorTileEntity.class, "PhotonAcceleratorTileEntity");

	   defender = new DefenderBlock(Material.anvil).setBlockName("obDefender").setBlockTextureName(OrbitalBombardment.MODID + ":defender").setLightLevel(0.2F).setResistance(25.0F).setHardness(10.0F).setCreativeTab(OrbitalBombardment.OBCreativeTabs);
	   GameRegistry.registerBlock(defender, "ob_defender");
	   GameRegistry.registerTileEntity(DefenderTileEntity.class, "DefenderTileEntity");
   }
}
