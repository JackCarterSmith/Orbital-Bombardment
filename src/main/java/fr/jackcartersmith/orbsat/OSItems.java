package fr.jackcartersmith.orbsat;

import fr.jackcartersmith.orbsat.item.DesignatorCircuitItem;
import fr.jackcartersmith.orbsat.item.LaserDesignatorItem;
import net.minecraft.item.Item;

public class OSItems {
	
	public static Item laserDesignator;
	public static Item designatorCircuit;
	
	public static void init(){
		laserDesignator = new LaserDesignatorItem("laserDesignator");
		designatorCircuit = new DesignatorCircuitItem("designatorCircuit");
	}	
}
