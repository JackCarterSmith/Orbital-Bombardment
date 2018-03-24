package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.item.Item;

public class DesignatorLowItem extends Item {
	public DesignatorLowItem(){
		this.setCreativeTab(OrbitalSatellite.OBCreativeTabs);
		this.setTextureName(OrbitalSatellite.MODID + ":item_ob_designatorLow");
	}
}
