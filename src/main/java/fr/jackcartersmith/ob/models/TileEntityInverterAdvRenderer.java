package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.InverterAdvTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityInverterAdvRenderer extends TileEntitySpecialRenderer
{
    public static ModelPhotonInverter model = new ModelPhotonInverter();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/solarPanel.png");
    long rotation;

    public void renderAModelAt(InverterAdvTileEntity tile, double d, double d1, double d2, float f)
    {
        if (tile.getWorldObj() != null)
        {
            this.rotation = tile.time;

            if (this.rotation < 6000L)
            {
                this.rotation -= 6000L;
            }

            if (this.rotation >= 6000L)
            {
                this.rotation -= 6000L;
            }

            if (tile.time > 12000L)
            {
                this.rotation = 0L;
            }
        }

        this.bindTexture(location);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        this.model.Shape3.rotateAngleX = (float)this.rotation / 15280.0F * 2.0F;
        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((InverterAdvTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
