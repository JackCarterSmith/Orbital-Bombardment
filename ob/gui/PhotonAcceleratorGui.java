package fr.jackcartersmith.orbsat.client.gui;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.PhotonAcceleratorTileEntity;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class PhotonAcceleratorGui extends GuiScreen
{
    ResourceLocation texture = new ResourceLocation("OB".toLowerCase(), "textures/gui/photonAccelerator.png");
    public final int xSize = 200;
    public final int ySize = 120;
    EntityPlayer entity2;

    public PhotonAcceleratorGui(EntityPlayer entity)
    {
        this.entity2 = entity;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int x, int y, float f1)
    {
        this.drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.texture);
        int posX = (this.width - 200) / 2;
        int posY = (this.height - 120) / 2;
        this.drawTexturedModalRect(posX, posY, 0, 0, 200, 120);
        super.drawScreen(x, y, f1);
        PhotonAcceleratorTileEntity acceleratorTileEntity = (PhotonAcceleratorTileEntity)this.entity2.worldObj.getTileEntity(OrbitalSatellite.instance.lastGeneralX, OrbitalSatellite.instance.lastGeneralY, OrbitalSatellite.instance.lastGeneralZ);
        this.fontRendererObj.drawStringWithShadow("# Decelerators: " + acceleratorTileEntity.getListSize(), posX + 30, posY + 25, -1);
        this.fontRendererObj.drawStringWithShadow("Total output rate: " + acceleratorTileEntity.getOutputRate(), posX + 30, posY + 35, -1);
        this.fontRendererObj.drawStringWithShadow("Scaled output rate: " + acceleratorTileEntity.getScaledOutput(), posX + 30, posY + 45, -1);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
