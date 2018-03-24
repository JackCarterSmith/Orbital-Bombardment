package fr.jackcartersmith.orbsat.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockOS extends Block{
    public BlockOS(final Material material) {
        super(material);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    public BlockOS() {
        super(Material.iron);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", OSRefs.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    public String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(OSRefs.RESOURCESPREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
}
