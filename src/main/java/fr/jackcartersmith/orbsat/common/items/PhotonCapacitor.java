package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class PhotonCapacitor extends ItemOS{
    public PhotonCapacitor() {
        this.setUnlocalizedName(OSStrings.photonCapacitorName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
