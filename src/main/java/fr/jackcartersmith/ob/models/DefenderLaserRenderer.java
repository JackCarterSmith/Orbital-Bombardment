package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.DefenderLaserTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class DefenderLaserRenderer extends TileEntitySpecialRenderer
{
    public static ModelLaser model = new ModelLaser();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/laserLow.png");
    float comeIn = 0.0F;
    float counter = 0.0F;
    String color = "";
    
    public DefenderLaserRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }

    public void renderAModelAt(DefenderLaserTileEntity tile, double d, double d1, double d2, float f)
    {
        if (tile.getWorldObj() != null)
        {
            ;
        }

        float rotation = tile.rotate;
        this.bindTexture(location);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, 1.0F);
        GL11.glRotatef(rotation * 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glColor4d(1.0D, 0.0D, 10.0D, 200.0D);
        this.model.Shape1.rotateAngleY = rotation;
        this.model.renderSpecific(this.model.Shape1);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((DefenderLaserTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
