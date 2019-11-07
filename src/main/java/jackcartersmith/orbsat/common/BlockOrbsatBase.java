package jackcartersmith.orbsat.common;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.client.render.IModelRegistration;
import jackcartersmith.orbsat.common.blocks.collision.AdvancedRayTraceResult;
import jackcartersmith.orbsat.common.blocks.collision.AdvancedRayTracer;
import jackcartersmith.orbsat.common.blocks.collision.CollisionGroup;
import jackcartersmith.orbsat.common.blocks.info.BlockDirection;
import jackcartersmith.orbsat.common.blocks.info.IBlockInfo;
import jackcartersmith.orbsat.common.items.itemblocks.ItemBlockOrbsatBase;
import jackcartersmith.orbsat.common.tileentities.TileEntityOrbsatBase;
import jackcartersmith.orbsat.common.utils.CollisionUtils;
import jackcartersmith.orbsat.common.utils.WorldUtils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class BlockOrbsatBase extends Block {
	private static final CollisionGroup DEFAULT_COLLISION_GROUP = new CollisionGroup().addItem(new AxisAlignedBB(0, 0, 0, 1, 1, 1)).setCanAccessGui(true);
    private static final List<CollisionGroup> DEFAULT_COLLISION_GROUPS = Collections.singletonList(DEFAULT_COLLISION_GROUP);
	protected final IBlockInfo info;

	public BlockOrbsatBase(IBlockInfo info) {
		super(info.getMaterial());
		
		this.info = info;
		
		setHardness(info.getHardness());
        setRegistryName(info.getId());
        setCreativeTab(OrbitalSatellite.INSTANCE.creativeTab);
        setSoundType(info.getSoundType());
	}
	
	@SideOnly(Side.CLIENT)
    public void registerModels(IModelRegistration modelRegistration) {}

	@Override
    public String getTranslationKey() {
        return "block." + info.getId().toString();
    }
	
	protected BlockStateContainer.Builder createBlockStateBuilder() {
        BlockStateContainer.Builder builder = new BlockStateContainer.Builder(this);

        if (getDirection() != null) {
            builder.add(getDirection().getProperty());
        }

        return builder;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return createBlockStateBuilder().build();
    }

    public Item createItem() {
        return new ItemBlockOrbsatBase(this, false);
    }
    
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
    
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (getDirection() != null) {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof TileEntityOrbsatBase) {
                return state.withProperty(getDirection().getProperty(), ((TileEntityOrbsatBase) tile).getDirection());
            }
        }

        return state;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        if (!world.isRemote && getDirection() != null) {
        	TileEntityOrbsatBase tile = (TileEntityOrbsatBase) world.getTileEntity(pos);

            EnumFacing newDirection = getDirection().cycle(tile.getDirection());

            tile.setDirection(newDirection);

            WorldUtils.updateBlock(world, pos);

            return true;
        }

        return false;
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        dropContents(world, pos);
        removeTile(world, pos, state);
    }

    void removeTile(World world, BlockPos pos, IBlockState state) {
        if (hasTileEntity(state)) {
            world.removeTileEntity(pos);
        }
    }

    void dropContents(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof TileEntityOrbsatBase && ((TileEntityOrbsatBase) tile).getDrops() != null) {
            WorldUtils.dropInventory(world, pos, ((TileEntityOrbsatBase) tile).getDrops());
        }
    }
    
    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity tile, ItemStack stack) {
        super.harvestBlock(world, player, pos, state, tile, stack);

        world.setBlockToAir(pos);
    }

    @Override
    public final boolean hasTileEntity(IBlockState state) {
        return info.hasTileEntity();
    }

    @Override
    public final TileEntity createTileEntity(World world, IBlockState state) {
        return info.createTileEntity();
    }

    @Nullable
    public BlockDirection getDirection() {
        return null;
    }

    public final IBlockInfo getInfo() {
        return info;
    }
    
    protected boolean canAccessGui(IBlockState state, World world, BlockPos pos, float hitX, float hitY, float hitZ) {
        state = getActualState(state, world, pos);

        for (CollisionGroup group : getCollisions(world.getTileEntity(pos), state)) {
            if (group.canAccessGui()) {
                for (AxisAlignedBB aabb : group.getItems()) {
                    if (CollisionUtils.isInBounds(aabb, hitX, hitY, hitZ)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public List<CollisionGroup> getCollisions(TileEntity tile, IBlockState state) {
        return DEFAULT_COLLISION_GROUPS;
    }
    
    @Override
    public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
        for (CollisionGroup group : getCollisions(world.getTileEntity(pos), this.getActualState(state, world, pos))) {
            for (AxisAlignedBB aabb : group.getItems()) {
                state.addCollisionBoxToList(world, pos, entityBox, collidingBoxes, entityIn, isActualState);
            }
        }
    }

    @Override
    public RayTraceResult collisionRayTrace(IBlockState state, World world, BlockPos pos, Vec3d start, Vec3d end) {
        AdvancedRayTraceResult result = AdvancedRayTracer.rayTrace(pos, start, end, getCollisions(world.getTileEntity(pos), this.getActualState(state, world, pos)));

        return result != null ? result.getHit() : null;
    }
}
