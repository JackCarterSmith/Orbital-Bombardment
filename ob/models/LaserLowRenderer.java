package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.blocks.LaserLowTileEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class LaserLowRenderer extends TileEntitySpecialRenderer
{
    //public ModelLaser model = new ModelLaser();
	public static ModelLaser model = new ModelLaser();
    public static ResourceLocation location = new ResourceLocation("ob", "textures/blocks/laserLow.png");
    float comeIn = 0.0F;
    float counter = 0.0F;
    String color = "";
    
    public LaserLowRenderer()
    {
        this.func_147497_a(TileEntityRendererDispatcher.instance);
    }
    
    public void renderAModelAt(LaserLowTileEntity tile, double d, double d1, double d2, float f)
    {
        if (tile.getWorldObj() != null)
        {
            ;
        }

        float rotation = tile.rotate;
        this.comeIn = tile.insideCounter;
        float counter = tile.color;
        //float grow = tile.grow;
        int r = (int)(50.0F - counter);
        int g = (int)(50.0F - counter);
        int b = (int)(50.0F - counter);
        this.bindTexture(location);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glRotatef(rotation * 90.0F, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);

        if (tile.low)
        {
            GL11.glColor4d((double)r, (double)g, 255.0D, 200.0D);
        }

        if (tile.med)
        {
            GL11.glColor4d(255.0D, (double)(255 + g), (double)b, 200.0D);
        }

        if (tile.high)
        {
            GL11.glColor4d(255.0D, (double)g, (double)b, 200.0D);
        }

        ModelRenderer shape1 = this.model.Shape1;
        this.model.renderSpecific(shape1);
        GL11.glColor4d(1000.0D, 0.0D, 0.0D, 255.0D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        this.model.Shape2.offsetX = this.comeIn * -1.0F;
        this.model.Shape2.offsetZ = this.comeIn * -1.0F;
        ModelRenderer shape2 = this.model.Shape2;
        this.model.renderSpecific(shape2);
        this.model.Shape3.offsetX = this.comeIn;
        this.model.Shape3.offsetZ = this.comeIn * -1.0F;
        ModelRenderer shape3 = this.model.Shape3;
        this.model.renderSpecific(shape3);
        this.model.Shape4.offsetX = this.comeIn * -1.0F;
        this.model.Shape4.offsetZ = this.comeIn;
        ModelRenderer shape4 = this.model.Shape4;
        this.model.renderSpecific(shape4);
        this.model.Shape5.offsetX = this.comeIn;
        this.model.Shape5.offsetZ = this.comeIn;
        ModelRenderer shape5 = this.model.Shape5;
        this.model.renderSpecific(shape5);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAModelAt((LaserLowTileEntity)par1TileEntity, par2, par4, par6, par8);
    }
}
