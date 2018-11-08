package jackcartersmith.orbsat.client;

import java.util.ArrayList;
import java.util.HashMap;

import jackcartersmith.orbsat.common.util.Lib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;

public class ClientUtils {
	public static final AxisAlignedBB standardBlockAABB = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
	static HashMap<String, ResourceLocation> resourceMap = new HashMap<String, ResourceLocation>();
	public static TextureAtlasSprite[] destroyBlockIcons = new TextureAtlasSprite[10];
	
	public static Tessellator tes()
	{
		return Tessellator.getInstance();
	}

	public static Minecraft mc()
	{
		return Minecraft.getMinecraft();
	}

	public static void bindTexture(String path)
	{
		mc().getTextureManager().bindTexture(getResource(path));
	}

	public static void bindAtlas()
	{
		mc().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
	}

	public static ResourceLocation getResource(String path)
	{
		ResourceLocation rl = resourceMap.containsKey(path)?resourceMap.get(path): new ResourceLocation(path);
		if(!resourceMap.containsKey(path))
			resourceMap.put(path, rl);
		return rl;
	}
	
	public static TextureAtlasSprite getSprite(ResourceLocation rl)
	{
		return mc().getTextureMapBlocks().getAtlasSprite(rl.toString());
	}
	
	public static FontRenderer font()
	{
		return mc().fontRenderer;
	}

	public static Timer timer()
	{
		return mc().timer;
	}

	public enum TimestampFormat
	{
		D,
		H,
		M,
		S,
		MS,
		HMS,
		HM,
		DHMS,
		DHM,
		DH;
		static TimestampFormat[] coreValues = {TimestampFormat.D, TimestampFormat.H, TimestampFormat.M, TimestampFormat.S};

		public boolean containsFormat(TimestampFormat format)
		{
			return this.toString().contains(format.toString());
		}

		public long getTickCut()
		{
			return this==D?1728000L: this==H?72000L: this==M?1200L: this==S?20L: 1;
		}

		public String getLocalKey()
		{
			return this==D?"day": this==H?"hour": this==M?"minute": this==S?"second": "";
		}
	}

	public static String fomatTimestamp(long timestamp, TimestampFormat format)
	{
		String s = "";
		for(TimestampFormat core : TimestampFormat.coreValues)
			if(format.containsFormat(core)&&timestamp >= core.getTickCut())
			{
				s += I18n.format(Lib.DESC_INFO+core.getLocalKey(), Long.toString(timestamp/core.getTickCut()));
				timestamp %= core.getTickCut();
			}
		if(s.isEmpty())
			for(int i = TimestampFormat.coreValues.length-1; i >= 0; i--)
				if(format.containsFormat(TimestampFormat.coreValues[i]))
				{
					s = I18n.format(Lib.DESC_INFO+TimestampFormat.coreValues[i].getLocalKey(), 0);
					break;
				}
		return s;
	}
	
	static int[] chatColours = {
			0x000000,//BLACK
			0x0000AA,//DARK_BLUE
			0x00AA00,//DARK_GREEN
			0x00AAAA,//DARK_AQUA
			0xAA0000,//DARK_RED
			0xAA00AA,//DARK_PURPLE
			0xFFAA00,//GOLD
			0xAAAAAA,//GRAY
			0x555555,//DARK_GRAY
			0x5555FF,//BLUE
			0x55FF55,//GREEN
			0x55FFFF,//AQUA
			0xFF5555,//RED
			0xFF55FF,//LIGHT_PURPLE
			0xFFFF55,//YELLOW
			0xFFFFFF//WHITE
	};

	public static int getFormattingColour(TextFormatting color)
	{
		return color.ordinal() < 16?chatColours[color.ordinal()]: 0;
	}

	public static int getDarkenedTextColour(int colour)
	{
		int r = (colour >> 16&255)/4;
		int g = (colour >> 8&255)/4;
		int b = (colour&255)/4;
		return r<<16|g<<8|b;
	}
	
	public static ModelRenderer[] copyModelRenderers(ModelBase model, ModelRenderer... oldRenderers)
	{
		ModelRenderer[] newRenderers = new ModelRenderer[oldRenderers.length];
		for(int i = 0; i < newRenderers.length; i++)
			if(oldRenderers[i]!=null)
			{
				newRenderers[i] = new ModelRenderer(model, oldRenderers[i].boxName);
				int toX = oldRenderers[i].textureOffsetX;
				int toY = oldRenderers[i].textureOffsetY;
				newRenderers[i].setTextureOffset(toX, toY);
				newRenderers[i].mirror = oldRenderers[i].mirror;
				ArrayList<ModelBox> newCubes = new ArrayList<ModelBox>();
				for(ModelBox cube : oldRenderers[i].cubeList)
					newCubes.add(new ModelBox(newRenderers[i], toX, toY, cube.posX1, cube.posY1, cube.posZ1, (int)(cube.posX2-cube.posX1), (int)(cube.posY2-cube.posY1), (int)(cube.posZ2-cube.posZ1), 0));
				newRenderers[i].cubeList = newCubes;
				newRenderers[i].setRotationPoint(oldRenderers[i].rotationPointX, oldRenderers[i].rotationPointY, oldRenderers[i].rotationPointZ);
				newRenderers[i].rotateAngleX = oldRenderers[i].rotateAngleX;
				newRenderers[i].rotateAngleY = oldRenderers[i].rotateAngleY;
				newRenderers[i].rotateAngleZ = oldRenderers[i].rotateAngleZ;
				newRenderers[i].offsetX = oldRenderers[i].offsetX;
				newRenderers[i].offsetY = oldRenderers[i].offsetY;
				newRenderers[i].offsetZ = oldRenderers[i].offsetZ;
			}
		return newRenderers;
	}
}
