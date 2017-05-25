package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.ob.blocks.OBBlocks;
import fr.jackcartersmith.ob.libs.OBConstants;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class LaserGunLowItem$2 extends Thread
{
    final World val$par3World;

    final int val$par4;

    final int val$par5;

    final int val$par6;

    final EntityPlayer val$par2EntityPlayer;

    final LaserGunLowItem this$0;

    LaserGunLowItem$2(LaserGunLowItem var1, World var2, int var3, int var4, int var5, EntityPlayer var6)
    {
        this.this$0 = var1;
        this.val$par3World = var2;
        this.val$par4 = var3;
        this.val$par5 = var4;
        this.val$par6 = var5;
        this.val$par2EntityPlayer = var6;
    }

    public void run()
    {
        int i;

        for (i = 1000; i >= 1; --i)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + i, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + i, this.this$0.linkedSatelite.zCoord);
        }

        if (this.val$par3World.isRemote)
        {
            this.val$par3World.setBlock(this.val$par4, this.val$par5 + 1, this.val$par6, OBBlocks.laserLow);
            this.val$par3World.setBlock(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord, OBBlocks.laserLow);
        }

        try
        {
            Thread.sleep(5000L);
        }
        catch (InterruptedException var2)
        {
            var2.printStackTrace();
        }

        this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + 1, this.val$par6);
        this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord);

        if (this.val$par6 != 0)
        {
            for (i = 0; i < OBConstants.DesignatorLowMeteorAmount; ++i)
            {
                this.this$0.sendChangeToServer2(this.val$par4, this.val$par5 + 100, this.val$par6, OBConstants.DesignatorLowMeteorDamage, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            }
        }
    }
}
