package fr.jackcartersmith.orbsat.client.gui;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import fr.jackcartersmith.orbsat.common.lib.OSConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class PhotonInverterAdvGui extends GuiScreen
{
    ResourceLocation texture = new ResourceLocation("OB".toLowerCase(), "textures/gui/photonInverterAdv.png");
    public final int xSize = 200;
    public final int ySize = 120;
    EntityPlayer entity2;

    public PhotonInverterAdvGui(EntityPlayer entity)
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
        this.fontRendererObj.drawStringWithShadow("Production Rate: " + OSConstants.AdvPhotonInvChargeRate + "PU/t", posX + 30, posY + 40, -1);
    }

    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    public boolean doesGuiPauseGame()
    {
        return false;
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    public void actionPerformed(GuiButton button)
    {
        switch (button.id)
        {
            case 0:
                int xC = OrbitalSatellite.instance.lastChargerX;
                int yC = OrbitalSatellite.instance.lastChargerY;
                int zC = OrbitalSatellite.instance.lastChargerZ;
                World world = this.entity2.worldObj;
                OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(xC, yC, zC);
                entity.setCurrentCharge(0);

            default:
        }
    }
}
