package fr.jackcartersmith.ob.blocks;

import java.util.Iterator;
import java.util.List;

import fr.jackcartersmith.ob.interfaces.PhotonRecieving;
import fr.jackcartersmith.ob.libs.OBConstants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;


public class DefenderLaserTileEntity extends PhotonRecieving
{
    int ticksCount = 0;

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        this.rotate += 1.0E-6F * (float)this.getCurrentCharge();

        if ((double)this.rotate >= 3.141D)
        {
            this.rotate = -3.141F;
        }

        if (this.ticksCount == 15)
        {
            List damages = this.getNearByEntitiesForDamage(this.xCoord, this.yCoord, this.zCoord);
            Iterator i$ = damages.iterator();

            while (i$.hasNext())
            {
                EntityMob damage = (EntityMob)i$.next();
                DamageSource source = DamageSource.anvil;
                damage.attackEntityFrom(source, 10.0F);
            }

            this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);

            if (!(this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof DefenderTileEntity))
            {
                this.worldObj.createExplosion((Entity)null, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, 0.1F, true);
            }
        }

        ++this.ticksCount;
    }

    public List getNearByEntitiesForDamage(int x, int y, int z)
    {
        int radius = OBConstants.DefenderRadius;
        List entities = this.worldObj.getEntitiesWithinAABB(EntityMob.class, AxisAlignedBB.getBoundingBox((double)(x - 5), (double)(y - 5), (double)(z - 5), (double)(x + 5), (double)(y + 5), (double)(z + 5)));
        return entities;
    }
}
