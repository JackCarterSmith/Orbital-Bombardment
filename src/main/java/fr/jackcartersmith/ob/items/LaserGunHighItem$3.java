package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.ob.blocks.OBBlocks;
import fr.jackcartersmith.ob.libs.OBConstants;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

class LaserGunHighItem$3 extends Thread
{
    final World val$par3World;

    final int val$par4;

    final int val$par5;

    final int val$par6;

    final EntityPlayer val$par2EntityPlayer;

    final LaserGunHighItem this$0;

    LaserGunHighItem$3(LaserGunHighItem var1, World var2, int var3, int var4, int var5, EntityPlayer var6)
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
        for (int mp = 250; mp >= 1; --mp)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + mp, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + mp, this.this$0.linkedSatelite.zCoord);
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

        if (this.val$par3World.isRemote)
        {
            EntityClientPlayerMP var3 = (EntityClientPlayerMP)this.val$par2EntityPlayer;
            this.this$0.sendChangeToServer(this.val$par4, this.val$par5, this.val$par6, OBConstants.DesignatorHighPhotonStrikePower, var3);
        }
    }
}
