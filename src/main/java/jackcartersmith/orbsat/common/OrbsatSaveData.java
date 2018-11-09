package jackcartersmith.orbsat.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class OrbsatSaveData extends WorldSavedData {
	private static OrbsatSaveData INSTANCE;
	public static final String dataName = "OrbitalSatellite-SaveData";

	public OrbsatSaveData(String name) {
		super(name);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void setDirty(int dimension)
	{
		//		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER && INSTANCE.get(dimension)!=null)
		//		{
		//			INSTANCE.get(dimension).markDirty();
		//		}
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER&&INSTANCE!=null)
			INSTANCE.markDirty();
	}

	public static void setInstance(int dimension, OrbsatSaveData in)
	{
		//		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
		//			INSTANCE.put(dimension, in);
		if(FMLCommonHandler.instance().getEffectiveSide()==Side.SERVER)
			INSTANCE = in;
	}
}
