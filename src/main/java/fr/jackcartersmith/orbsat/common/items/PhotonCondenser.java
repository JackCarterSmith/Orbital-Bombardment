package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class PhotonCondenser extends ItemOS{
    public PhotonCondenser() {
        this.setUnlocalizedName(OSStrings.photonCondenserName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
