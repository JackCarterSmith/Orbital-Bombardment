package jackcartersmith.orbsat.common.blocks.collision;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;

public class CollisionGroup {
	private List<AxisAlignedBB> items = new ArrayList<>();
    private boolean canAccessGui;
    @Nullable
    private EnumFacing direction;

    public CollisionGroup addItem(AxisAlignedBB item) {
        items.add(item);

        return this;
    }

    public List<AxisAlignedBB> getItems() {
        return items;
    }

    public boolean canAccessGui() {
        return canAccessGui;
    }

    public CollisionGroup setCanAccessGui(boolean canAccessGui) {
        this.canAccessGui = canAccessGui;

        return this;
    }

    public CollisionGroup setDirection(EnumFacing direction) {
        this.direction = direction;

        return this;
    }

    @Nullable
    public EnumFacing getDirection() {
        return direction;
    }
}
