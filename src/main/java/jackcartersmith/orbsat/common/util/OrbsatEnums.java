package jackcartersmith.orbsat.common.util;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

public class OrbsatEnums {
	public enum SideConfig implements IStringSerializable
	{
		NONE("none"),
		INPUT("in"),
		OUTPUT("out");

		final String texture;

		SideConfig(String texture)
		{
			this.texture = texture;
		}

		@Override
		public String getName()
		{
			return this.toString().toLowerCase(Locale.ENGLISH);
		}

		public String getTextureName()
		{
			return texture;
		}

		public static SideConfig next(SideConfig current)
		{
			return current==INPUT?OUTPUT: current==OUTPUT?NONE: INPUT;
		}
	}
}
