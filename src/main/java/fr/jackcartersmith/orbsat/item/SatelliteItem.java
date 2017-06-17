package fr.jackcartersmith.orbsat.item;

import fr.jackcartersmith.orbsat.OSRefs;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.item.Item;

public class SatelliteItem extends Item{
	public SatelliteItem(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(OrbitalSatellite.creativeTab);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		OrbitalSatellite.register(this, unlocalizedName);
		OSRefs.registeredOSItems.add(this);
	}
}
