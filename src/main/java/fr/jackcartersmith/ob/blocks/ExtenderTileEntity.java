package fr.jackcartersmith.ob.blocks;

import fr.jackcartersmith.orbsat.OBNetworkClient;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.OSBlocks;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ExtenderTileEntity extends TileEntity
{
    int tick;
    final int takeGiveRate = 50;
    public int currentCharge;
    public final int maxCharge;

    public ExtenderTileEntity()
    {
        this.maxCharge = OSConstants.ExtenderMaxCharge;
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1)
    {
        super.writeToNBT(par1);
        par1.setInteger("currentChargeExtender", this.currentCharge);
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1)
    {
        super.readFromNBT(par1);
        this.currentCharge = par1.getInteger("currentChargeExtender");
        this.sendChangeToClient();
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        ++this.tick;
        World par1world = this.worldObj;

        if (!par1world.isRemote)
        {
            Block north = par1world.getBlock(this.xCoord + 1, this.yCoord, this.zCoord);
            Block south = par1world.getBlock(this.xCoord - 1, this.yCoord, this.zCoord);
            Block east = par1world.getBlock(this.xCoord, this.yCoord, this.zCoord + 1);
            Block west = par1world.getBlock(this.xCoord, this.yCoord, this.zCoord - 1);
            Block top = par1world.getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
            Block bottom = par1world.getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
            OverriderBlockTileEntity entity;
            int overriderCharge;
            int overriderMax;

            if (north == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord + 1, this.yCoord, this.zCoord);
                overriderCharge = entity.getCurrentCharge();
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 50 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (south == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord - 1, this.yCoord, this.zCoord);
                overriderCharge = entity.currentCharge;
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 50 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (east == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord, this.yCoord, this.zCoord + 1);
                overriderCharge = entity.currentCharge;
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 1000 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (west == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord, this.yCoord, this.zCoord - 1);
                overriderCharge = entity.currentCharge;
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 50 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (top == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
                overriderCharge = entity.currentCharge;
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 50 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (bottom == OSBlocks.overrider)
            {
                entity = (OverriderBlockTileEntity)par1world.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
                overriderCharge = entity.currentCharge;
                overriderMax = entity.maxCharge;

                if (overriderCharge >= overriderMax && this.currentCharge <= this.maxCharge)
                {
                    entity.currentCharge -= 50;
                    this.currentCharge += 50;
                }

                if (overriderCharge + 50 < overriderMax && this.currentCharge >= 50)
                {
                    entity.currentCharge += 50;
                    this.currentCharge -= 50;
                }
            }

            if (this.tick == 20 && !this.worldObj.isRemote)
            {
                this.tick = 0;
                this.sendChangeToClient();
            }
        }
    }

    public void sendChangeToClient()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(3);
        bos.add(this.xCoord);
        bos.add(this.yCoord);
        bos.add(this.zCoord);
        bos.add(this.currentCharge);

        OrbitalSatellite.obNetwork.sendToAll(new OBNetworkClient(bos));
    }
}
