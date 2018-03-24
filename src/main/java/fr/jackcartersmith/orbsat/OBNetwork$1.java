package fr.jackcartersmith.orbsat;

import java.util.Random;

import fr.jackcartersmith.ob.entities.MeteorEntity;
import net.minecraft.entity.player.EntityPlayerMP;

public class OBNetwork$1 extends Thread
{
    final Random val$random;
    final EntityPlayerMP val$playerMP;
    final int val$fx;
    final int val$fy;
    final int val$fz;

    OBNetwork$1(Random var2, EntityPlayerMP var3, int var4, int var5, int var6){
        this.val$random = var2;
        this.val$playerMP = var3;
        this.val$fx = var4;
        this.val$fy = var5;
        this.val$fz = var6;
    }

    public void run(){
        try{
            Thread.sleep((long)this.val$random.nextInt(2000));
            this.val$playerMP.worldObj.playSoundAtEntity(this.val$playerMP, "ob:reentry", 5.0F, 1.0F);
            this.val$playerMP.worldObj.playSoundAtEntity(this.val$playerMP, "ob:booming", 8.0F, 2.0F);
            this.val$playerMP.worldObj.spawnEntityInWorld(new MeteorEntity(this.val$playerMP.worldObj, this.val$fx, this.val$fy, this.val$fz));
        }
        catch (InterruptedException var2){
            var2.printStackTrace();
        }
    }
}
