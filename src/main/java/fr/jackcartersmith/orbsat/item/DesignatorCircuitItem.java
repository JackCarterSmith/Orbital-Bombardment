package fr.jackcartersmith.orbsat.item;

import fr.jackcartersmith.orbsat.OSRefs;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.item.Item;

public class DesignatorCircuitItem extends Item{
	public DesignatorCircuitItem(String unlocalizedName){
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(OrbitalSatellite.creativeTab);
		OrbitalSatellite.register(this, unlocalizedName);
		OSRefs.registeredOSItems.add(this);
	}
}
