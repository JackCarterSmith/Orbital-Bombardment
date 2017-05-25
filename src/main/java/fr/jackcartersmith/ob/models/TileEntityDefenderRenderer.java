package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.DefenderTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityDefenderRenderer extends TileEntitySpecialRenderer
{
    public static ModelDefender model = new ModelDefender();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/defender.png");
    int rotation;

    public TileEntityDefenderRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
    
    public void renderAModelAt(DefenderTileEntity tile, double d, double d1, double d2, float f)
    {
        if (tile.getWorldObj() != null)
        {
            this.rotation = tile.getBlockMetadata();
        }

        this.bindTexture(location);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef((float)(this.rotation * 90), 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        this.model.Shape7.rotateAngleY = tile.rotate * 90.0F;
        this.model.Shape7.rotateAngleZ = tile.rotate * 90.0F;
        this.model.Shape7.rotateAngleZ = tile.rotate * 90.0F;

        if (tile.getCurrentCharge() > 0)
        {
            GL11.glDisable(GL11.GL_LIGHTING);
        }

        this.model.Shape7.render(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((DefenderTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
