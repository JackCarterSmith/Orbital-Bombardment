package fr.jackcartersmith.orbsat.common.lib;

public class OSRefs {
    public static final String MODID = "orbsat";
    public static final String MODNAME = "Orbital Satellite";
    public static final String VERSION = "0.1.8";
    public static final String MCVERSION = "1.7.10";
    public static final String CLIENTPROXYLOCATION = "fr.jackcartersmith.orbsat.client.ClientProxy";
    public static final String SERVERPROXYLOCATION = "fr.jackcartersmith.orbsat.common.CommonProxy";
    //public static final String GUIFACTORY = "fr.jackcartersmith.ob.client.gui.DEGUIFactory";
    public static final String RESOURCESPREFIX = MODID.toLowerCase() + ":";

    //======================Render IDs========================//
    public static int idTeleporterStand = -1;
    public static int idPortal = -1;

    //======================Data Types========================//

    public static final byte BYTE_ID = 0;
    public static final byte SHORT_ID = 1;
    public static final byte INT_ID = 2;
    public static final byte LONG_ID = 3;
    public static final byte FLOAT_ID = 4;
    public static final byte DOUBLE_ID = 5;
    public static final byte BOOLEAN_ID = 6;
    public static final byte CHAR_ID = 7;
    public static final byte STRING_ID = 8;
    public static final byte INT_PAIR_ID = 9;
}
