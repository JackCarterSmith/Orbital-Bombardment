package jackcartersmith.orbsat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OrbsatConfig {
	private Configuration config;
    private OrbsatConfig originalClientVersion;
    
    //region Controller
    public int controllerCapacity;
    public boolean controllerUsesEnergy;
    //endregion

    //region Grid
    public int maxRowsStretch;
    public boolean largeFont;
    public boolean detailedTooltip;
    //endregion

    //region Wireless Transmitter
    public int wirelessTransmitterBaseRange;
    public int wirelessTransmitterRangePerUpgrade;
    //endregion

    //region Wireless Grid
    public boolean wirelessGridUsesEnergy;
    public int wirelessGridCapacity;
    public int wirelessGridOpenUsage;
    public int wirelessGridExtractUsage;
    public int wirelessGridInsertUsage;
    //endregion
    
    //region Categories
    private static final String CONTROLLER = "controller";
    private static final String GRID = "grid";
    private static final String WIRELESS_TRANSMITTER = "wirelessTransmitter";
    private static final String WIRELESS_GRID = "wirelessGrid";
    //endregion
    
    public OrbsatConfig(@Nullable OrbsatConfig originalClientVersion, File configFile) {
        this(originalClientVersion, new Configuration(configFile));
    }

    public OrbsatConfig(@Nullable OrbsatConfig originalClientVersion, Configuration config) {
        this.originalClientVersion = originalClientVersion;
        this.config = config;

        MinecraftForge.EVENT_BUS.register(this);

        this.loadConfig();
    }

    public Configuration getConfig() {
        return config;
    }

    @Nullable
    public OrbsatConfig getOriginalClientVersion() {
        return originalClientVersion;
    }

    @SubscribeEvent
    public void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(OrbitalSatellite.MODID)) {
            loadConfig();
        }
    }
    
    private void loadConfig() {
        //region Controller
        controllerCapacity = config.getInt("capacity", CONTROLLER, 32000, 0, Integer.MAX_VALUE, "The energy capacity of the Controller");
        controllerUsesEnergy = config.getBoolean("usesEnergy", CONTROLLER, true, "Whether the Controller uses energy");
        //endregion

        //region Grid
        maxRowsStretch = config.getInt("maxRowsStretch", GRID, Integer.MAX_VALUE, 3, Integer.MAX_VALUE, "The maximum amount of rows that the Grid can show when stretched");
        largeFont = config.getBoolean("largeFont", GRID, false, "Whether the Grid should use a large font for stack quantity display");
        detailedTooltip = config.getBoolean("detailedTooltip", GRID, true, "Whether the Grid should display a detailed tooltip when hovering over an item or fluid");
        //endregion

        //region Wireless Transmitter
        wirelessTransmitterBaseRange = config.getInt("range", WIRELESS_TRANSMITTER, 16, 0, Integer.MAX_VALUE, "The base range of the Wireless Transmitter");
        wirelessTransmitterRangePerUpgrade = config.getInt("rangePerUpgrade", WIRELESS_TRANSMITTER, 8, 0, Integer.MAX_VALUE, "The additional range per Range Upgrade in the Wireless Transmitter");
        //endregion

        //region Wireless Grid
        wirelessGridUsesEnergy = config.getBoolean("usesEnergy", WIRELESS_GRID, true, "Whether the Wireless Grid uses energy");
        wirelessGridCapacity = config.getInt("capacity", WIRELESS_GRID, 3200, 0, Integer.MAX_VALUE, "The energy capacity of the Wireless Grid");
        wirelessGridOpenUsage = config.getInt("open", WIRELESS_GRID, 30, 0, Integer.MAX_VALUE, "The energy used by the Wireless Grid to open");
        wirelessGridInsertUsage = config.getInt("insert", WIRELESS_GRID, 3, 0, Integer.MAX_VALUE, "The energy used by the Wireless Grid to insert items");
        wirelessGridExtractUsage = config.getInt("extract", WIRELESS_GRID, 3, 0, Integer.MAX_VALUE, "The energy used by the Wireless Grid to extract items");
        //endregion

        if (config.hasChanged()) {
            config.save();
        }
    }
    
    public List<IConfigElement> getConfigElements() {
        List<IConfigElement> list = new ArrayList<>();

        list.add(new ConfigElement(config.getCategory(CONTROLLER)));
        list.add(new ConfigElement(config.getCategory(WIRELESS_TRANSMITTER)));
        list.add(new ConfigElement(config.getCategory(GRID)));
        list.add(new ConfigElement(config.getCategory(WIRELESS_GRID)));

        return list;
    }
}
