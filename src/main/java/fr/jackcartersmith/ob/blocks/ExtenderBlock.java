package fr.jackcartersmith.ob.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.ob.proxy.ClientProxy;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ExtenderBlock extends BlockContainer
{
    public ExtenderBlock(Material par2Material)
    {
        super(par2Material);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon("OB".toLowerCase() + ":extender");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        OrbitalSatellite.instance.lastExtenderX = par2;
        OrbitalSatellite.instance.lastExtenderY = par3;
        OrbitalSatellite.instance.lastExtenderZ = par4;
        par5EntityPlayer.openGui(OrbitalSatellite.instance, 3, par1World, par2, par3, par4);
        return true;
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
	@Override
	public TileEntity createNewTileEntity(World arg0, int arg1) {
		return new ExtenderTileEntity();
	}
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int par6)
    {
        par1World.removeTileEntity(par2, par3, par4);
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return ClientProxy.tesrRenderId;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }
}
