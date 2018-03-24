package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class LaserGunHighItem$2 extends Thread
{
    final World val$par3World;

    final int val$par4;

    final int val$par5;

    final int val$par6;

    final EntityPlayer val$par2EntityPlayer;

    final LaserGunHighItem this$0;

    LaserGunHighItem$2(LaserGunHighItem var1, World var2, int var3, int var4, int var5, EntityPlayer var6)
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

        for (i = 250; i >= 1; --i)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + i, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + i, this.this$0.linkedSatelite.zCoord);
        }

        if (this.val$par3World.isRemote)
        {
            this.val$par3World.setBlock(this.val$par4, this.val$par5 + 1, this.val$par6, OSBlocks.laserLow);
            this.val$par3World.setBlock(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord, OSBlocks.laserLow);
        }

        try
        {
            Thread.sleep(5000L);
        }
        catch (InterruptedException var2)
        {
            var2.printStackTrace();
        }

        this.val$par2EntityPlayer.worldObj.playSoundAtEntity(this.val$par2EntityPlayer, "ob:reentry", 1.0F, 1.0F);
        this.val$par2EntityPlayer.worldObj.playSoundAtEntity(this.val$par2EntityPlayer, "ob:booming", 1.0F, 1.0F);
        this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + 1, this.val$par6);
        this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord);

        if (this.val$par6 != 0)
        {
            for (i = 0; i < OSConstants.DesignatorHighMeteorAmount; ++i)
            {
                this.this$0.sendChangeToServer2(this.val$par4, this.val$par5 + 100, this.val$par6, OSConstants.DesignatorHighMeteorDamage, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            }
        }
    }
}
