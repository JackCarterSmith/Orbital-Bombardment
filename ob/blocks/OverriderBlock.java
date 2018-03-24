package fr.jackcartersmith.ob.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.ob.proxy.ClientProxy;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class OverriderBlock extends BlockContainer
{
    public OverriderBlock(Material par2Material)
    {
        super(par2Material);
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8F, 1.0F);
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon("OB".toLowerCase() + ":satelite");
    }

    public void onNeighborTileChange(World world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(x, y, z);

        if (entity.isCharging())
        {
            world.playSoundEffect((double)x, (double)y, (double)z, "ob:startCharging", 1.0F, 1.0F);
        }
    }

    /**
     * Called on server worlds only when the block has been replaced by a different block ID, or the same block with a
     * different metadata value, but before the new metadata value is set. Args: World, x, y, z, old block ID, old
     * metadata
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, Block block, int par6)
    {
        if (par1World.getBlock(par2, par3 + 1, par4) == OSBlocks.satelite)
        {
            par1World.removeTileEntity(par2, par3 + 1, par4);
            par1World.setBlockToAir(par2, par3 + 1, par4);
        }
        par1World.removeTileEntity(par2, par3, par4);
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float a, float b, float c)
    {
        OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(x, y, z);

        if (!player.isSneaking())
        {
            OrbitalSatellite.instance.lastChargerX = x;
            OrbitalSatellite.instance.lastChargerY = y;
            OrbitalSatellite.instance.lastChargerZ = z;
            player.openGui(OrbitalSatellite.instance, 0, world, (int)player.posX, (int)player.posY, (int)player.posZ);
        }

        return true;
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        OverriderBlockTileEntity entity = (OverriderBlockTileEntity)par1World.getTileEntity(par2, par3, par4);

        if (entity.currentCharge > 0)
        {
            float f1 = (float)par2 + 0.5F;
            float f2 = (float)par3 + 1.0F;
            float f3 = (float)par4 + 0.5F;

            if (!(par1World.getTileEntity(par2, par3 + 1, par4) instanceof LaserLowTileEntity))
            {
                for (int i = 0; i <= 50; ++i)
                {
                    par1World.spawnParticle("reddust", (double)f1 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.6D + (double)i, (double)f3 + par5Random.nextDouble() + 0.3D - 0.6D, 250.0D, 250.0D, 250.0D);
                }
            }
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
    public TileEntity createNewTileEntity(World arg0, int arg1) {
        return new OverriderBlockTileEntity();
    }
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
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

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int rotation = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3;
        world.setBlockMetadataWithNotify(i, j, k, rotation - 1, rotation - 1);
    }
}
