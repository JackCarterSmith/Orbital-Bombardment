package fr.jackcartersmith.orbsat.block;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public enum BlockType_Devices implements IStringSerializable, BlockOSBase.IBlockEnum{
    SATELLITE_LAUNCHER,
    SATELLITE_CENTRALE,
    SATELLITE_ASSEMBLER;
    
    @Override
    public String getName() {
        return this.toString().toLowerCase(Locale.ENGLISH);
    }
    
	@Override
	public int getMeta()
	{
		return ordinal();
	}
	@Override
	public boolean listForCreative()
	{
		return ordinal()!=12;
	}
}
