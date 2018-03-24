package fr.jackcartersmith.ob.items;

import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

class LaserGunMedItem$4 implements Runnable
{
    final World val$par3World;

    final int val$par4;

    final int val$par5;

    final int val$par6;

    final EntityPlayer val$par2EntityPlayer;

    final LaserGunMedItem this$0;

    LaserGunMedItem$4(LaserGunMedItem var1, World var2, int var3, int var4, int var5, EntityPlayer var6)
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
        for (int mp = 1000; mp >= 1; --mp)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + mp, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + mp, this.this$0.linkedSatelite.zCoord);
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
        catch (InterruptedException var3)
        {
            var3.printStackTrace();
        }

        if (this.val$par3World.isRemote)
        {
            this.val$par3World.setBlockToAir(this.val$par4, this.val$par5 + 1, this.val$par6);
            this.val$par3World.setBlockToAir(this.this$0.linkedSatelite.xCoord, this.this$0.linkedSatelite.yCoord + 1, this.this$0.linkedSatelite.zCoord);
        }

        if (!this.val$par3World.isRemote)
        {
            this.val$par2EntityPlayer.addChatMessage(new ChatComponentText("Firing..."));
        }

        EntityClientPlayerMP var4 = (EntityClientPlayerMP)this.val$par2EntityPlayer;

        for (int i = 0; i < OSConstants.DesignatorMedLightning; ++i)
        {
            this.this$0.sendChangeToServer3(this.val$par4, this.val$par5, this.val$par6, 1, var4);
            this.val$par3World.spawnEntityInWorld(new EntityLightningBolt(this.val$par3World, (double)this.val$par4, (double)this.val$par5, (double)this.val$par6));
            this.this$0.sendChangeToServer3(this.val$par4 + 1, this.val$par5, this.val$par6, 1, var4);
            this.val$par3World.spawnEntityInWorld(new EntityLightningBolt(this.val$par3World, (double)(this.val$par4 + 1), (double)this.val$par5, (double)this.val$par6));
            this.this$0.sendChangeToServer3(this.val$par4 - 1, this.val$par5, this.val$par6, 1, var4);
            this.val$par3World.spawnEntityInWorld(new EntityLightningBolt(this.val$par3World, (double)(this.val$par4 - 1), (double)this.val$par5, (double)this.val$par6));
            this.this$0.sendChangeToServer3(this.val$par4, this.val$par5, this.val$par6 + 1, 1, var4);
            this.val$par3World.spawnEntityInWorld(new EntityLightningBolt(this.val$par3World, (double)this.val$par4, (double)this.val$par5, (double)(this.val$par6 + 1)));
            this.this$0.sendChangeToServer3(this.val$par4, this.val$par5, this.val$par6 - 1, 1, var4);
            this.val$par3World.spawnEntityInWorld(new EntityLightningBolt(this.val$par3World, (double)this.val$par4, (double)this.val$par5, (double)(this.val$par6 - 1)));
        }
    }
}
