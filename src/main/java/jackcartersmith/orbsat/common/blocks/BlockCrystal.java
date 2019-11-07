package jackcartersmith.orbsat.common.blocks;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.client.render.IModelRegistration;
import jackcartersmith.orbsat.common.BlockOrbsatBase;
import jackcartersmith.orbsat.common.blocks.info.BlockInfoBuilder;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends BlockOrbsatBase {

	public BlockCrystal() {
		super(BlockInfoBuilder.forId("block_crystal").create());
		setLightOpacity(5);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerModels(IModelRegistration modelRegistration) {
        modelRegistration.setModel(this, 0, new ModelResourceLocation(info.getId(), "inventory"));
    }

}
