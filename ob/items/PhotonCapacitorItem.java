package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.item.Item;

public class PhotonCapacitorItem extends Item{
	public PhotonCapacitorItem(){
		this.setCreativeTab(OrbitalSatellite.OBCreativeTabs);
		this.setTextureName(OrbitalSatellite.MODID + ":item_ob_capacitor");
	}
}
