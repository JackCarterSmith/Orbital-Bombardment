package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.item.Item;

public class PhotonCapacitorItem extends Item{
	public PhotonCapacitorItem(){
		this.setCreativeTab(OrbitalBombardment.OBCreativeTabs);
		this.setTextureName(OrbitalBombardment.MODID + ":item_ob_capacitor");
	}
}
