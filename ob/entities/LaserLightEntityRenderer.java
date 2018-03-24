package fr.jackcartersmith.ob.entities;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.ob.models.ModelLaserLight;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class LaserLightEntityRenderer extends RenderLiving
{
    ModelLaserLight model = new ModelLaserLight();
    private static final ResourceLocation Your_Texture = new ResourceLocation("ob", "textures/entity/laserLight.png");

    public LaserLightEntityRenderer(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return Your_Texture;
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        LaserLightEntity entity = (LaserLightEntity)par1Entity;
        GL11.glPushMatrix();
        GL11.glTranslated(par2, par4, par6);
        GL11.glRotatef((float)entity.angleZ, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef((float)entity.angleY, 0.0F, 0.0F, 1.0F);
        GL11.glScalef((float)entity.renderSize, 1.0F, 1.0F);
        this.model.renderAll();
        GL11.glPopMatrix();
    }
}
