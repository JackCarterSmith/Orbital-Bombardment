package jackcartersmith.orbsat.common.blocks.info;

import javax.annotation.Nullable;

import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public interface IBlockInfo {
	Material getMaterial();

    SoundType getSoundType();

    ResourceLocation getId();

    Object getModObject();

    float getHardness();

    @Nullable
    TileEntityOrbsatBase createTileEntity();

    boolean hasTileEntity();
}
