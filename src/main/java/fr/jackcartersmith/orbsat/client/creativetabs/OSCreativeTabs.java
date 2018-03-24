package fr.jackcartersmith.orbsat.client.creativetabs;

import fr.jackcartersmith.orbsat.common.OSItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class OSCreativeTabs extends CreativeTabs {

	public OSCreativeTabs(int id, String label) {
		super(id,label);
	}

	@Override
	public Item getTabIconItem() {

		return OSItems.basicExtrapolCircuit;
	}

}
