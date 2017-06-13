package fr.jackcartersmith.orbsat;

import fr.jackcartersmith.orbsat.item.LaserDesignatorItem;
import net.minecraft.item.Item;

public class OSItems {
	
	public static Item laserDesignator_mkI;

	public static void init(){
		laserDesignator_mkI = new LaserDesignatorItem("laserDesignator_mki");
	}	
}
