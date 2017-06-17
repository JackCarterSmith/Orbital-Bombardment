package fr.jackcartersmith.orbsat.client;

import java.util.HashMap;

import fr.jackcartersmith.orbsat.block.OSBlockInterface.IOSMetaBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.util.ResourceLocation;

public class OSCustomStateMapper extends StateMapperBase{
	public static HashMap<String, StateMapperBase> stateMappers = new HashMap<>();
	public static StateMapperBase getStateMapper(IOSMetaBlock metaBlock)
	{
		String key = metaBlock.getOSBlockName();
		StateMapperBase mapper = stateMappers.get(key);
		if(mapper==null)
		{
			mapper = metaBlock.getCustomMapper();
			if(mapper==null)
				mapper = new OSCustomStateMapper();
			stateMappers.put(key, mapper);
		}
		return mapper;
	}

	@Override
	protected ModelResourceLocation getModelResourceLocation(IBlockState state)
	{
		try{
			ResourceLocation rl = Block.REGISTRY.getNameForObject(state.getBlock());
			IOSMetaBlock metaBlock = (IOSMetaBlock)state.getBlock();
			String custom = metaBlock.getCustomStateMapping(state.getBlock().getMetaFromState(state), false);
			if(custom!=null)
				rl = new ResourceLocation(rl.toString()+"_"+custom);
			String prop = metaBlock.appendPropertiesToState()?this.getPropertyString(state.getProperties()):null;
			return new ModelResourceLocation(rl, prop);
		}catch(Exception e)
		{
			e.printStackTrace();
			ResourceLocation rl = Block.REGISTRY.getNameForObject(state.getBlock());
			return new ModelResourceLocation(rl, this.getPropertyString(state.getProperties()));
		}
	}
}
