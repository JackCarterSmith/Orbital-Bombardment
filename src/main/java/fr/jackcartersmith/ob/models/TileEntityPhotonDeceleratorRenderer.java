package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.PhotonDeceleratorTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityPhotonDeceleratorRenderer extends TileEntitySpecialRenderer
{
    public static ModelPhotonDecelerator model = new ModelPhotonDecelerator();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/photonDecelerator.png");
    int rotation;
    
    public TileEntityPhotonDeceleratorRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }

    public void renderAModelAt(PhotonDeceleratorTileEntity tile, double d, double d1, double d2, float f)
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
        this.model.Shape5.rotateAngleY = tile.rotate * 90.0F;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        if (tile.getPowerIncoming() > 0)
        {
            GL11.glDisable(GL11.GL_LIGHTING);
        }

        this.model.Shape5.render(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((PhotonDeceleratorTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
