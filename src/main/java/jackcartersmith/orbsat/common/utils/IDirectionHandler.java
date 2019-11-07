package jackcartersmith.orbsat.common.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public interface IDirectionHandler {
	void setDirection(EnumFacing direction);

    EnumFacing getDirection();

    void writeToTileNbt(NBTTagCompound tag);

    void readFromTileNbt(NBTTagCompound tag);
}
