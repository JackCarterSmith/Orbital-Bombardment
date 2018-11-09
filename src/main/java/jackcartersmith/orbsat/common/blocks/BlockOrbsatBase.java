package jackcartersmith.orbsat.common.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.common.OrbsatContents;
import jackcartersmith.orbsat.common.blocks.OrbsatBlockInterface.IOrbsatMetaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockOrbsatBase<E extends Enum<E> & BlockOrbsatBase.IBlockEnum> extends Block implements IOrbsatMetaBlock{
	public final String name;
	public final PropertyEnum<E> property;
	
	protected static IProperty[] tempProperties;
	protected static IUnlistedProperty[] tempUnlistedProperties;
	
	public final IProperty[] additionalProperties;
	public final IUnlistedProperty[] additionalUnlistedProperties;
	public final E[] enumValues;
	boolean[] isMetaHidden;
	boolean[] hasFlavour;
	protected Set<BlockRenderLayer> renderLayers = Sets.newHashSet(BlockRenderLayer.SOLID);
	protected Set<BlockRenderLayer>[] metaRenderLayers;
	protected Map<Integer, Integer> metaLightOpacities = new HashMap<>();
	protected Map<Integer, Float> metaHardness = new HashMap<>();
	protected Map<Integer, Integer> metaResistances = new HashMap<>();
	//protected EnumPushReaction[] metaMobilityFlags;
	//protected boolean[] canHammerHarvest;
	protected boolean[] metaNotNormalBlock;
	private boolean opaqueCube = false;
	
	public BlockOrbsatBase(String name, Material material, PropertyEnum<E> mainProperty, Class<? extends ItemBlockOrbsatBase> itemBlock, Object... additionalProperties)
	{
		super(setTempProperties(material, mainProperty, additionalProperties));
		this.name = name;
		this.property = mainProperty;
		this.enumValues = mainProperty.getValueClass().getEnumConstants();
		this.isMetaHidden = new boolean[this.enumValues.length];
		this.hasFlavour = new boolean[this.enumValues.length];
		this.metaRenderLayers = new Set[this.enumValues.length];
		//this.canHammerHarvest = new boolean[this.enumValues.length];
		//this.metaMobilityFlags = new EnumPushReaction[this.enumValues.length];

		ArrayList<IProperty> propList = new ArrayList<IProperty>();
		ArrayList<IUnlistedProperty> unlistedPropList = new ArrayList<IUnlistedProperty>();
		for(Object o : additionalProperties)
		{
			if(o instanceof IProperty)
				propList.add((IProperty)o);
			if(o instanceof IProperty[])
				for(IProperty p : ((IProperty[])o))
					propList.add(p);
			if(o instanceof IUnlistedProperty)
				unlistedPropList.add((IUnlistedProperty)o);
			if(o instanceof IUnlistedProperty[])
				for(IUnlistedProperty p : ((IUnlistedProperty[])o))
					unlistedPropList.add(p);
		}
		this.additionalProperties = propList.toArray(new IProperty[propList.size()]);
		this.additionalUnlistedProperties = unlistedPropList.toArray(new IUnlistedProperty[unlistedPropList.size()]);
		this.setDefaultState(getInitDefaultState());
		String registryName = createRegistryName();
		this.setTranslationKey(registryName.replace(':', '.'));
		this.setCreativeTab(OrbitalSatellite.creativeTab);
		this.adjustSound();

//		ImmersiveEngineering.registerBlockByFullName(this, itemBlock, registryName);
		OrbsatContents.registeredOrbsatBlocks.add(this);
		try
		{
			OrbsatContents.registeredOrbsatItems.add(itemBlock.getConstructor(Block.class).newInstance(this));
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		lightOpacity = 255;
	}
	
	public interface IBlockEnum extends IStringSerializable
	{
		int getMeta();

		boolean listForCreative();
	}

	@Override
	public String getOrbsatBlockName() {
		return this.name;
	}

	@Override
	public Enum[] getMetaEnums() {
		return enumValues;
	}

	@Override
	public IBlockState getInventoryState(int meta) {
		return getStateFromMeta(meta);
	}

	@Override
	public PropertyEnum<E> getMetaProperty()
	{
		return this.property;
	}
	
	@Override
	public boolean useCustomStateMapper()
	{
		return false;
	}

	@Override
	public String getCustomStateMapping(int meta, boolean itemBlock)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public StateMapperBase getCustomMapper()
	{
		return null;
	}

	@Override
	public boolean appendPropertiesToState()
	{
		return true;
	}
	
	public String getTranslationKey(ItemStack stack)
	{
		String subName = getStateFromMeta(stack.getItemDamage()).getValue(property).toString().toLowerCase(Locale.US);
		return super.getTranslationKey()+"."+subName;
	}
	
	void adjustSound()
	{
		if(this.material==Material.ANVIL)
			this.blockSoundType = SoundType.ANVIL;
		else if(this.material==Material.CARPET||this.material==Material.CLOTH)
			this.blockSoundType = SoundType.CLOTH;
		else if(this.material==Material.GLASS||this.material==Material.ICE)
			this.blockSoundType = SoundType.GLASS;
		else if(this.material==Material.GRASS||this.material==Material.TNT||this.material==Material.PLANTS||this.material==Material.VINE)
			this.blockSoundType = SoundType.PLANT;
		else if(this.material==Material.GROUND)
			this.blockSoundType = SoundType.GROUND;
		else if(this.material==Material.IRON)
			this.blockSoundType = SoundType.METAL;
		else if(this.material==Material.SAND)
			this.blockSoundType = SoundType.SAND;
		else if(this.material==Material.SNOW)
			this.blockSoundType = SoundType.SNOW;
		else if(this.material==Material.ROCK)
			this.blockSoundType = SoundType.STONE;
		else if(this.material==Material.WOOD||this.material==Material.CACTUS)
			this.blockSoundType = SoundType.WOOD;
	}
	
	protected static Material setTempProperties(Material material, PropertyEnum<?> property, Object... additionalProperties)
	{
		ArrayList<IProperty> propList = new ArrayList<IProperty>();
		ArrayList<IUnlistedProperty> unlistedPropList = new ArrayList<IUnlistedProperty>();
		propList.add(property);
		for(Object o : additionalProperties)
		{
			if(o instanceof IProperty)
				propList.add((IProperty)o);
			if(o instanceof IProperty[])
				for(IProperty p : ((IProperty[])o))
					propList.add(p);
			if(o instanceof IUnlistedProperty)
				unlistedPropList.add((IUnlistedProperty)o);
			if(o instanceof IUnlistedProperty[])
				for(IUnlistedProperty p : ((IUnlistedProperty[])o))
					unlistedPropList.add(p);
		}
		tempProperties = propList.toArray(new IProperty[propList.size()]);
		tempUnlistedProperties = unlistedPropList.toArray(new IUnlistedProperty[unlistedPropList.size()]);
		return material;
	}
	
	protected IBlockState getInitDefaultState()
	{
		IBlockState state = this.blockState.getBaseState().withProperty(this.property, enumValues[0]);
		for(int i = 0; i < this.additionalProperties.length; i++)
			if(this.additionalProperties[i]!=null&&!this.additionalProperties[i].getAllowedValues().isEmpty())
				state = applyProperty(state, additionalProperties[i], additionalProperties[i].getAllowedValues().iterator().next());
		return state;
	}
	
	protected <V extends Comparable<V>> IBlockState applyProperty(IBlockState in, IProperty<V> prop, Object val)
	{
		return in.withProperty(prop, (V)val);
	}
	
	public void onOrbsatBlockPlacedBy(World world, BlockPos pos, IBlockState state, EnumFacing side, float hitX, float hitY, float hitZ, EntityLivingBase placer, ItemStack stack)
	{
	}

	public boolean canOrbsatBlockBePlaced(World world, BlockPos pos, IBlockState newState, EnumFacing side, float hitX, float hitY, float hitZ, EntityPlayer player, ItemStack stack)
	{
		return true;
	}
	
	public String createRegistryName()
	{
		return OrbitalSatellite.MODID+":"+name;
	}
}
