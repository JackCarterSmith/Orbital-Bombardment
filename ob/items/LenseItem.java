package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.item.Item;

public class LenseItem extends Item{
	public LenseItem(){
		this.setCreativeTab(OrbitalSatellite.OBCreativeTabs);
		this.setTextureName(OrbitalSatellite.MODID + ":item_ob_lense");
	}
}
