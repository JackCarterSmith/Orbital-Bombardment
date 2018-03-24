package fr.jackcartersmith.orbsat.client.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.ob.blocks.SateliteTileEntity;
import fr.jackcartersmith.orbsat.OBNetwork;
import fr.jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class OverriderBlockGui extends GuiScreen
{
    ResourceLocation texture = new ResourceLocation("OB".toLowerCase(), "textures/gui/gui.png");
    public final int xSize = 200;
    public final int ySize = 120;
    EntityPlayer entity2;

    public OverriderBlockGui(EntityPlayer entity)
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
        World world = this.entity2.worldObj;
        int xC = OrbitalSatellite.instance.lastChargerX;
        int yC = OrbitalSatellite.instance.lastChargerY;
        int zC = OrbitalSatellite.instance.lastChargerZ;
        OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(xC, yC, zC);

        if (entity.hasSatelite)
        {
            this.buttonList.add(new GuiButton(0, posX + 38, posY + 60, 100, 20, "Launch Satelite"));
            this.fontRendererObj.drawStringWithShadow("Photon Charge: " + entity.getCurrentCharge() + " PU", posX + 30, posY + 35, -1);
        }
        else
        {
            this.buttonList.removeAll(this.buttonList);
            String chargingString = "";

            if (entity.isCharging())
            {
                chargingString = "Yes";
            }

            if (!entity.isCharging())
            {
                chargingString = "No";
            }

            this.fontRendererObj.drawStringWithShadow("Photon Charge: " + entity.getCurrentCharge() + " PU", posX + 30, posY + 30, -1);

            if (entity.amountOfGuns == null)
            {
                this.fontRendererObj.drawStringWithShadow("# Designators: 0", posX + 30, posY + 40, -1);
            }
            else
            {
                this.fontRendererObj.drawStringWithShadow("# Designators: " + entity.amountOfGuns.size(), posX + 30, posY + 40, -1);
            }

            this.fontRendererObj.drawStringWithShadow("Satelite uses left: " + entity.shotsLeft, posX + 30, posY + 50, -1);
        }
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
                World world = this.entity2.worldObj;
                int xC = OrbitalSatellite.instance.lastChargerX;
                int yC = OrbitalSatellite.instance.lastChargerY + 1;
                int zC = OrbitalSatellite.instance.lastChargerZ;
                OverriderBlockTileEntity entity = (OverriderBlockTileEntity)world.getTileEntity(xC, yC - 1, zC);
                SateliteTileEntity sat = (SateliteTileEntity)world.getTileEntity(xC, yC, zC);

                if (entity.hasSatelite && entity.getCurrentCharge() > 25000 && !sat.shouldLaunch)
                {
                    EntityClientPlayerMP mp = (EntityClientPlayerMP)this.entity2;
                    this.sendChangeToServer18(xC, yC - 1, zC, entity.shotsLeft + 150, mp);
                    sat.shouldLaunch = true;
                    entity.hasSateliteLaunched = true;
                    entity.hasSatelite = false;
                }
                else if (entity.hasSatelite)
                {
                    this.entity2.addChatMessage(new ChatComponentText("Cannot launch satelite. Need 25000PU to launch."));
                    entity.hasSatelite = false;
                }

            default:
        }
    }

    public void sendChangeToServer18(int xCoord, int yCoord, int zCoord, int power, EntityClientPlayerMP mp)
    {
        List<Integer> bos = new ArrayList<Integer>();
        
        bos.add(18);
        bos.add(xCoord);
        bos.add(yCoord);
        bos.add(zCoord);
        bos.add(power);
        
        OrbitalSatellite.obNetwork.sendToServer(new OBNetwork(bos));
    }
}
