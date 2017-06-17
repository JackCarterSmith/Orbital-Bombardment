package fr.jackcartersmith.orbsat.client;

import fr.jackcartersmith.orbsat.OSRefs;
import fr.jackcartersmith.orbsat.common.CommonProxy;
import fr.jackcartersmith.orbsat.common.util.OSLogger;
import fr.jackcartersmith.orbsat.item.ItemOSBase;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameData;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerRender(){
		
	}
	
	@Override
	public void preInit(){
		
		for(Item item : OSRefs.registeredOSItems)
		{
			if(item instanceof Item)
			{	
				ItemOSBase ieMetaItem = (ItemOSBase) item;
				if(ieMetaItem.registerSubModels && ieMetaItem.getSubNames() != null && ieMetaItem.getSubNames().length > 0)
				{
					for(int meta = 0; meta < ieMetaItem.getSubNames().length; meta++)
					{
						ResourceLocation loc = new ResourceLocation("orbsat", ieMetaItem.itemName + "/" + ieMetaItem.getSubNames()[meta]);
						ModelBakery.registerItemVariants(ieMetaItem, loc);
						ModelLoader.setCustomModelResourceLocation(ieMetaItem, meta, new ModelResourceLocation(loc, "inventory"));
					}
				} else
				{
					final ResourceLocation loc = new ResourceLocation("orbsat", ieMetaItem.itemName);
					ModelBakery.registerItemVariants(ieMetaItem, loc);
					ModelLoader.setCustomMeshDefinition(ieMetaItem, new ItemMeshDefinition()
					{
						@Override
						public ModelResourceLocation getModelLocation(ItemStack stack)
						{
							return new ModelResourceLocation(loc, "inventory");
						}
					});
				}
			} 
			else
			{
				final ResourceLocation loc = GameData.getItemRegistry().getNameForObject(item);
				ModelBakery.registerItemVariants(item, loc);
				ModelLoader.setCustomMeshDefinition(item, new ItemMeshDefinition()
				{
					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack)
					{
						return new ModelResourceLocation(loc, "inventory");
					}
				});
			}
		}
	}
	
}
