package fr.jackcartersmith.orbsat.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;
import fr.jackcartersmith.orbsat.common.lib.OSStrings;
import fr.jackcartersmith.orbsat.common.tileentities.TileDefender;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Defender extends BlockOS {
	public Defender() {
        super(Material.iron);
        this.setBlockName(OSStrings.defenderName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        this.setStepSound(soundTypeStone);
        OSBlocks.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(OSRefs.RESOURCESPREFIX + "machine");
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess access, int x, int y, int z) {
    	/*
        TileDefender tile = access.getTileEntity(x, y, z) instanceof TileDefender ? (TileDefender) access.getTileEntity(x, y, z) : null;
        if (tile != null) {
            switch (tile.facingDirection) {
                case 0:
                    this.setBlockBounds(0F, 0.885F, 0F, 1F, 1F, 1F);
                    break;
                case 1:
                    this.setBlockBounds(0F, 0F, 0F, 1F, 0.125F, 1F);
                    break;
                case 2:
                    this.setBlockBounds(0F, 0F, 0.885F, 1F, 1F, 1F);
                    break;
                case 3:
                    this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 0.125F);
                    break;
                case 4:
                    this.setBlockBounds(0.885F, 0F, 0F, 1F, 1F, 1F);
                    break;
                case 5:
                    this.setBlockBounds(0F, 0F, 0F, 0.125F, 1F, 1F);
                    break;
            }
        }
        */
    	this.setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
        super.setBlockBoundsBasedOnState(access, x, y, z);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
    	/*
        TileDefender tile = world.getTileEntity(x, y, z) instanceof TileTileDefender ? (TileTileDefender) world.getTileEntity(x, y, z) : null;
        if (tile != null) {
            switch (tile.facingDirection) {
                case 0:
                    this.setBlockBounds(0F, 0.885F, 0F, 1F, 1F, 1F);
                    break;
                case 1:
                    this.setBlockBounds(0F, 0F, 0F, 1F, 0.125F, 1F);
                    break;
                case 2:
                    this.setBlockBounds(0F, 0F, 0.885F, 1F, 1F, 1F);
                    break;
                case 3:
                    this.setBlockBounds(0F, 0F, 0F, 1F, 1F, 0.125F);
                    break;
                case 4:
                    this.setBlockBounds(0.885F, 0F, 0F, 1F, 1F, 1F);
                    break;
                case 5:
                    this.setBlockBounds(0F, 0F, 0F, 0.125F, 1F, 1F);
                    break;
            }
        }
        */
    	this.setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    /*
    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {  	
        int d = Utils.determineOrientation(x, y, z, entity);
        TileReactorEnergyInjector tile = (TileReactorEnergyInjector) world.getTileEntity(x, y, z);
        tile.facingDirection = ForgeDirection.getOrientation(d).getOpposite().ordinal();
        tile.onPlaced();
    }
    */

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float prx, float pry, float prz) {
    	TileDefender tile = (TileDefender) world.getTileEntity(x, y, z);
        if (tile != null) {
	        if (!world.isRemote) {
	        	player.addChatComponentMessage(new ChatComponentText(Float.toString(tile.rotation)));
	            //FMLNetworkHandler.openGui(player, OrbitalSatellite.instance, GuiHandler.GUIID_ENERGY_INFUSER, world, x, y, z);
	        }
        }
        world.markBlockForUpdate(x, y, z);
        return true;
    }

    @Override
    public int getRenderType() {
        return -1;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
  
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileDefender();
    }
}
