package jackcartersmith.orbsat.common.items.itemblocks;

import jackcartersmith.orbsat.common.BlockOrbsatBase;
import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBlockOrbsatBase extends ItemBlock {
	private BlockOrbsatBase block;

    public ItemBlockOrbsatBase(BlockOrbsatBase block, boolean subtypes) {
        super(block);

        this.block = block;

        setRegistryName(block.getInfo().getId());

        if (subtypes) {
            setMaxDamage(0);
            setHasSubtypes(true);
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        if (getHasSubtypes()) {
            return getTranslationKey() + "." + stack.getItemDamage();
        }

        return getTranslationKey();
    }

    @Override
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, IBlockState newState) {
        boolean result = super.placeBlockAt(stack, player, world, pos, side, hitX, hitY, hitZ, newState);

        if (result && block.getDirection() != null) {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntityOrbsatBase) {
                ((TileEntityOrbsatBase) tile).setDirection(block.getDirection().getFrom(side, pos, player));
            }
        }

        return result;
    }
}
