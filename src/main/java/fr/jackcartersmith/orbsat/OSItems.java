package fr.jackcartersmith.orbsat;

import fr.jackcartersmith.orbsat.item.ItemOSBase;

public class OSItems {
	public static ItemOSBase componentsItem;
	public static ItemOSBase satelliteItem;
	
	public static void init(){
		componentsItem = new ItemOSBase("components",3,
				"laserDesignator","designatorCircuit",
				"photonLens","photonConcentrator","photonCapacitor");
		satelliteItem = new ItemOSBase("satellites",1,
				"classic_mki","classic_mkii","classic_mkiii");
	}	
}
