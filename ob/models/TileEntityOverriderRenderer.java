package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityOverriderRenderer extends TileEntitySpecialRenderer
{
    public static ModelOverrider model = new ModelOverrider();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/overrider.png");
    
    public TileEntityOverriderRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }

    public void renderAModelAt(OverriderBlockTileEntity tile, double d, double d1, double d2, float f)
    {
        int rotation = 0;

        if (tile.getWorldObj() != null)
        {
            rotation = tile.getBlockMetadata();
        }

        this.bindTexture(location);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef((float)(rotation * 90), 0.0F, 1.0F, 0.0F);
        //this.model.crystal.setRotationPoint(Math.abs(tile.rotate) - 5.0F, Math.abs(tile.rotate) - 23.0F, Math.abs(tile.rotate) - 3.0F);
        //this.model.crystal.rotateAngleZ = tile.rotate;
        //this.model.crystal.rotateAngleX = tile.rotate;
        this.model.crystal.rotateAngleY = tile.rotate;
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        if (tile.getCurrentCharge() > 0)
        {
            GL11.glDisable(GL11.GL_LIGHTING);
        }

        this.model.crystal.render(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((OverriderBlockTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
