package fr.jackcartersmith.orbsat.common.items;

import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSItems;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;

public class AdvancedTargetDevice extends ItemOS{
    public AdvancedTargetDevice() {
        this.setUnlocalizedName(OSStrings.advancedTargetDeviceName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        OSItems.register(this);
    }
}
