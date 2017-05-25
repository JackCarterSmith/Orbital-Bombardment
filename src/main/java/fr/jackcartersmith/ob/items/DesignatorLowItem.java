package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.item.Item;

public class DesignatorLowItem extends Item {
	public DesignatorLowItem(){
		this.setCreativeTab(OrbitalBombardment.OBCreativeTabs);
		this.setTextureName(OrbitalBombardment.MODID + ":item_ob_designatorLow");
	}
}
