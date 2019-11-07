package jackcartersmith.orbsat.common.blocks.info;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class BlockInfo implements IBlockInfo {
	private final Material material;
    private final SoundType soundType;
    private final float hardness;
    private final ResourceLocation id;
    private final Object modObject;
    @Nullable
    private final Supplier<TileEntityOrbsatBase> tileSupplier;

    public BlockInfo(Material material, SoundType soundType, float hardness, String id, String modId, Object modObject, Supplier<TileEntityOrbsatBase> tileSupplier) {
        this.material = material;
        this.soundType = soundType;
        this.hardness = hardness;
        this.id = new ResourceLocation(modId, id);
        this.modObject = modObject;
        this.tileSupplier = tileSupplier;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public SoundType getSoundType() {
        return soundType;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public Object getModObject() {
        return modObject;
    }

    @Override
    public float getHardness() {
        return hardness;
    }

    @Nullable
    @Override
    public TileEntityOrbsatBase createTileEntity() {
        return tileSupplier.get();
    }

    @Override
    public boolean hasTileEntity() {
        return tileSupplier != null;
    }
}
