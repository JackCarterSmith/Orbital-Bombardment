package fr.jackcartersmith.ob.libs;

import fr.jackcartersmith.ob.OrbitalBombardment;
import fr.jackcartersmith.ob.blocks.OBBlocks;
import fr.jackcartersmith.ob.items.OBItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OBCreativeTabs extends CreativeTabs {

	public OBCreativeTabs(String label) {
		
		super(label);
	}

	@Override
	public Item getTabIconItem() {

		return OBItems.designatorLow;
	}

}
