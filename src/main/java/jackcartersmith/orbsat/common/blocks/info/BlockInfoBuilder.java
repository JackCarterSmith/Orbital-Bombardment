package jackcartersmith.orbsat.common.blocks.info;

import java.util.function.Supplier;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public final class BlockInfoBuilder {
	private Material material = Material.ROCK;
    private String id;
    private String modId;
    private Object modObject;
    private float hardness = 1.9F;
    private SoundType soundType = SoundType.STONE;
    private Supplier<TileEntityOrbsatBase> tileSupplier;

    private BlockInfoBuilder() {
    }

    public static BlockInfoBuilder forMod(Object modObject, String modId, String id) {
        BlockInfoBuilder builder = new BlockInfoBuilder();

        builder.modObject = modObject;
        builder.modId = modId;
        builder.id = id;

        return builder;
    }

    public static BlockInfoBuilder forId(String id) {
        return forMod(OrbitalSatellite.INSTANCE, OrbitalSatellite.MODID, id);
    }

    public BlockInfoBuilder material(Material material) {
        this.material = material;

        return this;
    }

    public BlockInfoBuilder soundType(SoundType soundType) {
        this.soundType = soundType;

        return this;
    }

    public BlockInfoBuilder hardness(float hardness) {
        this.hardness = hardness;

        return this;
    }

    public BlockInfoBuilder tileEntity(Supplier<TileEntityOrbsatBase> tileSupplier) {
        this.tileSupplier = tileSupplier;

        return this;
    }

    public BlockInfo create() {
        return new BlockInfo(material, soundType, hardness, id, modId, modObject, tileSupplier);
    }
}
