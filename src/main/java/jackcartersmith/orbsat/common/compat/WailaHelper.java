package jackcartersmith.orbsat.common.compat;

import net.minecraftforge.fml.common.event.FMLInterModComms;

public class WailaHelper extends OrbsatCompatModule
{
	@Override
	public void preInit()
	{
	}

	@Override
	public void init()
	{
		FMLInterModComms.sendMessage("waila", "register", "jackcartersmith.orbsat.common.compat.OrbsatWailaDataProvider.callbackRegister");
	}

	@Override
	public void postInit()
	{
	}
}
