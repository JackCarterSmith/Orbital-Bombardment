package fr.jackcartersmith.orbsat.client.creativetabs;

import fr.jackcartersmith.ob.items.OBItems;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OSCreativeTabs extends CreativeTabs {

	public OSCreativeTabs(int id, String label) {
		super(id,label);
	}

	@Override
	public Item getTabIconItem() {

		return OBItems.designatorLow;
	}

}
