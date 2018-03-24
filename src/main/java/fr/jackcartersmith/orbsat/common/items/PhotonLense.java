package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class PhotonLense extends ItemOS{
    public PhotonLense() {
        this.setUnlocalizedName(OSStrings.photonLenseName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
