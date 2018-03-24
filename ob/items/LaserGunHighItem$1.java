package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.common.OSBlocks;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class LaserGunHighItem$1 extends Thread
{
    final World val$par3World;

    final int val$par4;

    final int val$par5;

    final int val$par6;

    final EntityPlayer val$par2EntityPlayer;

    final LaserGunHighItem this$0;

    LaserGunHighItem$1(LaserGunHighItem var1, World var2, int var3, int var4, int var5, EntityPlayer var6)
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
        for (int e = 250; e >= 1; --e)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + e, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + e, this.this$0.linkedSatelite.zCoord);
        }

        if (this.val$par3World.isRemote)
        {
            this.val$par3World.setBlock(this.val$par4, this.val$par5 + 1, this.val$par6, OSBlocks.laserLow);
            this.val$par3World.setBlock(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord, OSBlocks.laserLow);
        }

        try
        {
            Thread.sleep(5000L);
            this.this$0.sendChangeToServer12(this.val$par4, this.val$par5, this.val$par6, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 + 1, this.val$par5, this.val$par6, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 - 1, this.val$par5, this.val$par6, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4, this.val$par5, this.val$par6 + 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4, this.val$par5, this.val$par6 - 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 + 1, this.val$par5, this.val$par6 + 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 - 1, this.val$par5, this.val$par6 + 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 - 1, this.val$par5, this.val$par6 - 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.this$0.sendChangeToServer12(this.val$par4 + 1, this.val$par5, this.val$par6 - 1, 0, (EntityClientPlayerMP)this.val$par2EntityPlayer);
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + 1, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord);
        }
        catch (InterruptedException var2)
        {
            var2.printStackTrace();
        }
    }
}
