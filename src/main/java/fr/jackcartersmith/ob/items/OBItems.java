package fr.jackcartersmith.ob.items;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.item.Item;

public class OBItems {
	public static Item designatorLow;
    public static Item designatorMed;
    public static Item designatorHigh;
    public static Item lenseItem;
    public static Item laserGunLowItem;
    public static Item laserGunMedItem;
    public static Item laserGunHighItem;
    public static Item thrusterFuelItem;
    public static Item photonCondenserItem;
    public static Item photonCapacitor;
    
    public static void init()
    {
    	designatorLow = new DesignatorLowItem().setUnlocalizedName("item_ob_designatorLow");
    	GameRegistry.registerItem(designatorLow, "ob_designatorLow");
    	designatorMed = new DesignatorMedItem().setUnlocalizedName("item_ob_designatorMed");
    	GameRegistry.registerItem(designatorMed, "ob_designatorMed");
    	designatorHigh = new DesignatorHighItem().setUnlocalizedName("item_ob_designatorHigh");
    	GameRegistry.registerItem(designatorHigh, "ob_designatorHigh");
    	lenseItem = new LenseItem().setUnlocalizedName("item_ob_lense");
    	GameRegistry.registerItem(lenseItem, "ob_lense_item");
    	laserGunLowItem = new LaserGunLowItem().setUnlocalizedName("item_ob_laserGunLow");
    	GameRegistry.registerItem(laserGunLowItem, "ob_laserGunLow");
    	laserGunMedItem = new LaserGunMedItem().setUnlocalizedName("item_ob_laserGunMed");
    	GameRegistry.registerItem(laserGunMedItem, "ob_laserGunMed");
    	laserGunHighItem = new LaserGunHighItem().setUnlocalizedName("item_ob_laserGunHigh");
    	GameRegistry.registerItem(laserGunHighItem, "ob_laserGunHigh");
    	photonCondenserItem = new PhotonCondenserItem().setUnlocalizedName("item_ob_condenser");
    	GameRegistry.registerItem(photonCondenserItem, "ob_condenser_item");
    	photonCapacitor = new PhotonCapacitorItem().setUnlocalizedName("item_ob_capacitor");
    	GameRegistry.registerItem(photonCapacitor, "ob_capacitor_item");
    }
}
