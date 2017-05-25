package fr.jackcartersmith.ob.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.ob.OBNetwork;
import fr.jackcartersmith.ob.OrbitalBombardment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MeteorEntity extends EntitySlime
{
    int ticksAlive;

    public MeteorEntity(World par1World)
    {
        super(par1World);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    public MeteorEntity(World par1World, int x, int y, int z)
    {
        super(par1World);
        this.setPosition((double)x, (double)y, (double)z);
        this.setHealth(1.0F);
        this.setSize(1.0F, 0.5F);
        Random veloRan = new Random();
        float choose = veloRan.nextFloat();
        float veloX;
        float veloY;
        float veloZ;

        if (choose > 0.5F)
        {
            veloX = veloRan.nextFloat() * -2.0F;
            veloY = veloRan.nextFloat() * 2.0F;
            veloZ = veloRan.nextFloat() * 2.0F;
            this.motionX = (double)veloX;
            this.motionY = (double)veloY;
            this.motionZ = (double)veloZ;
        }

        if (choose < 0.5F)
        {
            veloX = veloRan.nextFloat() * 2.0F;
            veloY = veloRan.nextFloat() * -2.0F;
            veloZ = veloRan.nextFloat() * -2.0F;
            this.motionX = (double)veloX;
            this.motionY = (double)veloY;
            this.motionZ = (double)veloZ;
        }

        this.renderDistanceWeight = 1.0D;
        super.setSlimeSize(1);
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource)
    {
        if (this.worldObj.isRemote)
        {
            this.sendChangeToServer((int)this.posX, (int)this.posY, (int)this.posZ, 8, Minecraft.getMinecraft().thePlayer);
        }
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float par1)
    {
        if (this.worldObj.isRemote)
        {
            this.sendChangeToServer((int)this.posX, (int)this.posY, (int)this.posZ, 8, Minecraft.getMinecraft().thePlayer);
            this.kill();
        }
    }

    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {}

    /**
     * Sets the entity's position and rotation. Args: posX, posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation(double par1, double par3, double par5, float par7, float par8) {}

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        ++this.ticksAlive;
        float f1 = (float)this.posX + 0.5F;
        float f2 = (float)this.posY + 1.0F;
        float f3 = (float)this.posZ + 0.5F;
        Random par5Random = new Random();
        this.worldObj.spawnParticle("largeexplode", (double)f1 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f3 + par5Random.nextDouble() + 0.3D - 0.6D, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("largeexplode", (double)f1 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f3 + par5Random.nextDouble() + 0.3D - 0.6D, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("largeexplode", (double)f1 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f3 + par5Random.nextDouble() + 0.3D - 0.6D, 0.0D, 0.0D, 0.0D);
        this.worldObj.spawnParticle("largeexplode", (double)f1 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f2 + par5Random.nextDouble() + 0.3D - 0.6D, (double)f3 + par5Random.nextDouble() + 0.3D - 0.6D, 0.0D, 0.0D, 0.0D);
        //this.worldObj.getWorldVec3Pool().getVecFromPool(0.0D, 0.0D, 0.5D);
        Vec3.createVectorHelper(0.0D, 0.0D, 0.5D);

        if (this.ticksAlive >= 120)
        {
            this.kill();
        }

        super.onLivingUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void sendChangeToServer(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(2);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);

        OrbitalBombardment.obNetwork.sendToServer(new OBNetwork(bos));
    }
}
