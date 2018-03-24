package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class AdvancedExtrapolCircuit extends ItemOS{
    public AdvancedExtrapolCircuit() {
        this.setUnlocalizedName(OSStrings.advancedExtrapolCircuitName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
