package fr.jackcartersmith.ob.gui;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.OrbitalBombardment;
import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.ob.blocks.ExtenderTileEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class ExtenderGui extends GuiScreen
{
    ResourceLocation texture = new ResourceLocation("OB".toLowerCase(), "textures/gui/extender.png");
    public final int xSize = 200;
    public final int ySize = 120;
    EntityPlayer entity2;

    public ExtenderGui(EntityPlayer entity)
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
        int xC = OrbitalBombardment.instance.lastExtenderX;
        int yC = OrbitalBombardment.instance.lastExtenderY;
        int zC = OrbitalBombardment.instance.lastExtenderZ;
        World world = this.entity2.worldObj;
        ExtenderTileEntity entity = (ExtenderTileEntity)world.getTileEntity(xC, yC, zC);
        String chargingString = "";
        this.fontRendererObj.drawStringWithShadow("Photon Charge: " + entity.currentCharge + " PU", posX + 30, posY + 40, -1);
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
                int xC = OrbitalBombardment.instance.lastChargerX;
                int yC = OrbitalBombardment.instance.lastChargerY;
                int zC = OrbitalBombardment.instance.lastChargerZ;
                World world = this.entity2.worldObj;
                OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(xC, yC, zC);
                entity.setCurrentCharge(entity.getCurrentCharge() - 30000);

            default:
        }
    }
}
