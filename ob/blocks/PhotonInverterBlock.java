package fr.jackcartersmith.ob.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class PhotonInverterBlock extends BlockContainer
{
    @SideOnly(Side.CLIENT)
    public static IIcon topIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon sideIcon;

    public PhotonInverterBlock(Material par2Material)
    {
        super(Material.rock);
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister icon)
    {
        this.topIcon = icon.registerIcon(OrbitalSatellite.MODID + ":solarPanelTop");
        this.sideIcon = icon.registerIcon(OrbitalSatellite.MODID + ":solarPanelSides");
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getIcon(int side, int metadata)
    {
        //return side == 0 ? sideIcon : (side == 1 ? topIcon : sideIcon);
        if(side == 1)
            return this.topIcon;
        return this.sideIcon;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        par5EntityPlayer.openGui(OrbitalSatellite.instance, 1, par1World, par2, par3, par4);
        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new InverterTileEntity();
	}
}
