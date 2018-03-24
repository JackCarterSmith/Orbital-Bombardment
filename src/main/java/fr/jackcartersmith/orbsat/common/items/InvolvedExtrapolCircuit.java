package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class InvolvedExtrapolCircuit extends ItemOS{
    public InvolvedExtrapolCircuit() {
        this.setUnlocalizedName(OSStrings.involvedExtrapolCircuitName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
