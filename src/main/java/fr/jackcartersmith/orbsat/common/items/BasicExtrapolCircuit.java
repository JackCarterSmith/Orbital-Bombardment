package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class BasicExtrapolCircuit extends ItemOS{
    public BasicExtrapolCircuit() {
        this.setUnlocalizedName(OSStrings.basicExtrapolCircuitName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
