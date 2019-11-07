package jackcartersmith.orbsat.client;

import java.util.function.Function;
import java.util.function.Supplier;

import jackcartersmith.orbsat.client.render.IModelRegistration;
import jackcartersmith.orbsat.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;

public class ClientProxy extends CommonProxy implements IModelRegistration {

	@Override
	public void addBakedModelOverride(ResourceLocation resource, Function<IBakedModel, IBakedModel> override) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(Block block, int meta, ModelResourceLocation resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModel(Item item, int meta, ModelResourceLocation resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModelVariants(Item item, ResourceLocation... variants) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setModelMeshDefinition(Block block, ItemMeshDefinition meshDefinition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModelLoader(Supplier<ICustomModelLoader> modelLoader) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStateMapper(Block block, IStateMapper stateMapper) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTesr(Class<? extends TileEntity> tile, TileEntitySpecialRenderer tesr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addItemColor(Item item, IItemColor itemColor) {
		// TODO Auto-generated method stub
		
	}

}
