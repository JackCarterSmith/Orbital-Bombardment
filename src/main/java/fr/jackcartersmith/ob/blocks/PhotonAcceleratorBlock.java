package fr.jackcartersmith.ob.blocks;

import java.util.Random;

import fr.jackcartersmith.ob.proxy.ClientProxy;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class PhotonAcceleratorBlock extends BlockContainer
{
    public PhotonAcceleratorBlock(Material par2Material)
    {
        super(Material.iron);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
    }

    /**
     * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z, side, hitX, hitY, hitZ, block metadata
     */
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9)
    {
        return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8, par9);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
        PhotonAcceleratorTileEntity entity = (PhotonAcceleratorTileEntity)world.getTileEntity(x, y, z);

        if (!player.isSneaking())
        {
            OrbitalSatellite.instance.lastGeneralX = x;
            OrbitalSatellite.instance.lastGeneralY = y;
            OrbitalSatellite.instance.lastGeneralZ = z;
            player.openGui(OrbitalSatellite.instance, 4, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }

        return true;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        PhotonAcceleratorTileEntity entity = (PhotonAcceleratorTileEntity)par1World.getTileEntity(par2, par3, par4);

        if (entity.getOutputRate() > 0)
        {
            this.setLightLevel(1.0F);
            par1World.markBlockForUpdate(par2, par3, par4);
            float f1 = (float)par2 + 0.5F;
            float f2 = (float)par3 + 1.0F;
            float f3 = (float)par4 + 0.5F;

            for (int i = 0; i <= 2; ++i)
            {
                par1World.spawnParticle("reddust", (double)f1 + par5Random.nextDouble() + 0.3D - 0.8D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.8D + 1.0D, (double)f3 + par5Random.nextDouble() + 0.3D - 0.8D, 1.0D, 1.0D, 2.0D);
            }
        }
        else
        {
            this.setLightLevel(0.0F);
            par1World.markBlockForUpdate(par2, par3, par4);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World arg0, int arg1)
    {
        return new PhotonAcceleratorTileEntity();
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
        par1World.removeTileEntity(par2, par3 + 1, par4);
        par1World.setBlockToAir(par2, par3 + 1, par4);
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
