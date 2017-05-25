package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.item.Item;

public class DesignatorMedItem extends Item {
	public DesignatorMedItem(){
		this.setCreativeTab(OrbitalBombardment.OBCreativeTabs);
		this.setTextureName(OrbitalBombardment.MODID + ":item_ob_designatorMed");
	}
}
