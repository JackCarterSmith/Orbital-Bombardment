package fr.jackcartersmith.ob.items;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.orbsat.OBNetwork;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class LaserGunMedItem extends Item
{
    public OverriderBlockTileEntity linkedSatelite;
    public int mode = 0;
    public String currentMode = "Photon Strike";
    public MovingObjectPosition pos;

    public LaserGunMedItem()
    {
        this.setCreativeTab(OrbitalSatellite.OBCreativeTabs);
        this.setTextureName(OrbitalSatellite.MODID + ":item_ob_laserGunMed");
        this.setMaxStackSize(1);
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        if (this.linkedSatelite == null)
        {
            int x = par1ItemStack.stackTagCompound.getInteger("xCoord");
            int y = par1ItemStack.stackTagCompound.getInteger("xCoord");
            int z = par1ItemStack.stackTagCompound.getInteger("xCoord");
            OverriderBlockTileEntity newEntity = (OverriderBlockTileEntity)par2World.getTileEntity(x, y, z);

            if (newEntity != null)
            {
                this.linkedSatelite = newEntity;
            }
        }
    }

    public void saveNBT(ItemStack par1ItemStack, World par2World, OverriderBlockTileEntity entity)
    {
        if (par1ItemStack.stackTagCompound == null)
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
        }

        par1ItemStack.stackTagCompound.setInteger("xCoord", entity.xCoord);
        par1ItemStack.stackTagCompound.setInteger("yCoord", entity.yCoord);
        par1ItemStack.stackTagCompound.setInteger("zCoord", entity.zCoord);
    }

    /*
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister icon)
    {
        this.itemIcon = icon.registerIcon("OB".toLowerCase() + ":laserGunMedItem");
    }
    */

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (par2World.isRemote)
        {
            this.pos = par3EntityPlayer.rayTrace((double)OSConstants.DesignatorCommitRange, 1.0F);
        }

        try
        {
            if (par2World.isRemote)
            {
                MovingObjectPosition e = par3EntityPlayer.rayTrace(5.0D, 1.0F);

                if (e == null)
                {
                    this.onItemUse(par1ItemStack, par3EntityPlayer, par2World, this.pos.blockX, this.pos.blockY, this.pos.blockZ, 0, 1.0F, 1.0F, 1.0F);
                }
            }
        }
        catch (Exception var5)
        {
            ;
        }

        return par1ItemStack;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par9 != 1.0F && !par2EntityPlayer.isSneaking() && par3World.isRemote)
        {
            par2EntityPlayer.addChatMessage(new ChatComponentText("Target too close!"));
        }

        if (par9 == 1.0F || par2EntityPlayer.isSneaking())
        {
            if (par3World.getTileEntity(par4, par5, par6) instanceof OverriderBlockTileEntity)
            {
                this.linkedSatelite = (OverriderBlockTileEntity)par3World.getTileEntity(par4, par5, par6);
                this.linkedSatelite.amountOfGuns.put(this.toString(), this);

                if (!par3World.isRemote)
                {
                    par2EntityPlayer.addChatMessage(new ChatComponentText("Overrider set for this designator."));
                    this.saveNBT(par1ItemStack, par3World, this.linkedSatelite);
                }
            }
            else
            {
                if (par2EntityPlayer.isSneaking() && par3World.getTileEntity(par4, par5, par6) == null)
                {
                    if (this.mode == 4)
                    {
                        this.mode = 0;
                    }

                    if (par3World.isRemote && this.linkedSatelite != null)
                    {
                        ++this.mode;

                        if (this.mode == 1)
                        {
                            this.currentMode = "Photon Strike";
                        }

                        if (this.mode == 2)
                        {
                            this.currentMode = "Lightning";
                        }

                        if (this.mode == 3)
                        {
                            this.currentMode = "Meteor Shower";
                        }

                        if (this.mode == 4)
                        {
                            this.currentMode = "Penetrating Strike";
                        }

                        par2EntityPlayer.addChatMessage(new ChatComponentText("Mode set to: " + this.currentMode));
                    }
                }

                if (this.linkedSatelite != null && this.linkedSatelite.getWorldObj().provider.dimensionId == par2EntityPlayer.dimension)
                {
                    if (this.linkedSatelite.getCurrentCharge() >= OSConstants.DesignatorMedStrikePUUsage && this.linkedSatelite.shotsLeft > 0)
                    {
                        EntityClientPlayerMP mp;

                        if (par3World.isRemote && !par2EntityPlayer.isSneaking() && this.currentMode != "Penetrating Strike")
                        {
                            mp = (EntityClientPlayerMP)par2EntityPlayer;
                            this.sendChangeToServer15(this.linkedSatelite.xCoord, this.linkedSatelite.yCoord, this.linkedSatelite.zCoord, OSConstants.DesignatorMedStrikePUUsage, mp);
                            --this.linkedSatelite.shotsLeft;
                        }

                        if (par3World.isRemote && !par2EntityPlayer.isSneaking() && this.currentMode == "Penetrating Strike")
                        {
                            mp = (EntityClientPlayerMP)par2EntityPlayer;
                            this.sendChangeToServer15(this.linkedSatelite.xCoord, this.linkedSatelite.yCoord, this.linkedSatelite.zCoord, OSConstants.DesignatorMedStrikePUUsage, mp);
                            --this.linkedSatelite.shotsLeft;
                        }

                        if (!par2EntityPlayer.isSneaking())
                        {
                            if (this.currentMode == "Photon Strike")
                            {
                                if (!par3World.isRemote)
                                {
                                    par2EntityPlayer.addChatMessage(new ChatComponentText("Low Focus Photon strike inbound. Vacate the area!"));
                                }

                                this.doAnimationsPhoton(par1ItemStack, par2EntityPlayer, par3World, this.pos.blockX, this.pos.blockY, this.pos.blockZ, par10);
                            }

                            if (this.currentMode == "Lightning")
                            {
                                if (!par3World.isRemote)
                                {
                                    par2EntityPlayer.addChatMessage(new ChatComponentText("Lightning Strike inbound. Vacate the area!"));
                                }

                                this.doAnimationsLightning(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                            }

                            if (this.currentMode == "Meteor Shower" && par3World.isRemote)
                            {
                                par2EntityPlayer.addChatMessage(new ChatComponentText("Meteor Shower inbound. Vacate the area!"));
                                this.doAnimationsMeteor(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                            }

                            if (this.currentMode == "Penetrating Strike" && par3World.isRemote)
                            {
                                par2EntityPlayer.addChatMessage(new ChatComponentText("Penetrating strike inbound!"));
                                this.doAnimationsPenetrate(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
                            }
                        }
                    }
                    else
                    {
                        if (par3World.isRemote && this.linkedSatelite.getCurrentCharge() < OSConstants.DesignatorMedStrikePUUsage)
                        {
                            par2EntityPlayer.addChatMessage(new ChatComponentText("Not enough photon charge on overrider. Needs " + OSConstants.DesignatorMedStrikePUUsage + "PU and only has " + this.linkedSatelite.getCurrentCharge()));
                        }

                        if (par3World.isRemote && this.linkedSatelite.shotsLeft < 1)
                        {
                            par2EntityPlayer.addChatMessage(new ChatComponentText("No more uses left on the linked satelite in orbit. Launch a new one."));
                        }
                    }
                }
                else
                {
                    if (par3World.isRemote && !par2EntityPlayer.isSneaking() && this.currentMode != "Penetrating Strike" && this.linkedSatelite == null)
                    {
                        par2EntityPlayer.addChatMessage(new ChatComponentText("Designator not linked to any overrider."));
                    }

                    if (par3World.isRemote && !par2EntityPlayer.isSneaking() && this.currentMode == "Penetrating Strike" && this.linkedSatelite == null)
                    {
                        par2EntityPlayer.addChatMessage(new ChatComponentText("Designator not linked to any overrider."));
                    }

                    if (this.linkedSatelite != null && this.linkedSatelite.getWorldObj().provider.dimensionId != par2EntityPlayer.dimension)
                    {
                        par2EntityPlayer.addChatMessage(new ChatComponentText("Linked Overrider not in the same dimension as you! Cannot call strike!"));
                    }
                }
            }
        }

        return false;
    }

    private void doAnimationsPenetrate(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        this.sendChangeToServer24();
        boolean y = false;
        LaserGunMedItem$1 buildThread = new LaserGunMedItem$1(this, par3World, par4, par5, par6, par2EntityPlayer);
        buildThread.start();
    }

    private void doAnimationsMeteor(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        this.sendChangeToServer24();
        LaserGunMedItem$2 buildThread = new LaserGunMedItem$2(this, par3World, par4, par5, par6, par2EntityPlayer);
        buildThread.start();
    }

    public synchronized void doAnimationsPhoton(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, float par10)
    {
        this.sendChangeToServer24();
        LaserGunMedItem$3 buildThread = new LaserGunMedItem$3(this, par3World, par4, par5, par6, par2EntityPlayer);
        buildThread.start();
    }

    public void doAnimationsLightning(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        this.sendChangeToServer24();
        (new Thread(new LaserGunMedItem$4(this, par3World, par4, par5, par6, par2EntityPlayer))).start();
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

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }

    @SideOnly(Side.CLIENT)
    public void sendChangeToServer2(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(5);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }

    public void sendChangeToServer3(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(9);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }

    public void sendChangeToServer12(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(12);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(1);

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }

    public void sendChangeToServer15(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(15);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }

    public void sendChangeToServer24()
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(24);
        bos.add(1);
        bos.add(1);
        bos.add(1);
        bos.add(1);

        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }
}
