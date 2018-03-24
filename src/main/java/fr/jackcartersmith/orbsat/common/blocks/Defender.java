package fr.jackcartersmith.orbsat.common.blocks;

import java.util.List;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
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
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class Defender extends BlockCustomDrop {
	public Defender() {
        super(Material.iron);
        this.setBlockName(OSStrings.defenderName);
        this.setCreativeTab(OrbitalSatellite.OSCreaTab);
        this.setStepSound(soundTypeStone);
        this.setBlockBounds(0f, 0f, 0f, 1f, 0.5f, 1f);
        OSBlocks.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        blockIcon = iconRegister.registerIcon(OSRefs.RESOURCESPREFIX + "machine_side");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileDefender();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float prx, float pry, float prz) {
        if (!world.isRemote) {
            //FMLNetworkHandler.openGui(player, OrbitalSatellite.instance, GuiHandler.GUIID_ENERGY_INFUSER, world, x, y, z);
        }
        world.markBlockForUpdate(x, y, z);
        return true;
    }

    @SideOnly(Side.CLIENT)
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
    protected boolean dropInventory() {
        return true;
    }

    @Override
    protected boolean hasCustomDropps() {
        return false;
    }

    @Override
    protected void getCustomTileEntityDrops(TileEntity te, List<ItemStack> droppes) {

    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess access, int x, int y, int z, int side) {
        return false;
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return true;
    }
}
