package fr.jackcartersmith.ob.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

/**
 * ModelDefenderLaserLow - JackCarterSmith
 * Created using Tabula 4.1.1
 */
public class ModelDefenderLaserEvo extends ModelBase {
    public ModelRenderer l1;
    public ModelRenderer l2;

    public ModelDefenderLaserEvo() {
        this.textureWidth = 16;
        this.textureHeight = 32;
        this.l1 = new ModelRenderer(this, 0, 0);
        this.l1.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.l1.addBox(-2.5F, -19000.0F, -2.5F, 5, 10000, 5, 0.0F);
        this.l2 = new ModelRenderer(this, 0, 0);
        this.l2.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.l2.addBox(-2.5F, -19000.0F, -2.5F, 5, 20000, 5, 0.0F);
        this.setRotateAngle(l2, 0.0F, 0.7853981633974483F, 0.0F);
    }

    @Override
    public void render(Entity entity, float embitterRotation, float brightness, float f2, float f3, float f4, float scale) { 
        float lastBrightnessX = OpenGlHelper.lastBrightnessX;
        float lastBrightnessY = OpenGlHelper.lastBrightnessY;

        float b = brightness * 200F;
        float colour_r = Math.min(2F, (brightness * 1F) + 0.1F);
        float colour_g = Math.min(2F, (brightness * 0F) + 0.1F);
        float colour_b = Math.min(2F, (brightness * 2F) + 0.1F);

        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Math.min(200F, lastBrightnessX + b), Math.min(200F, lastBrightnessY + b));
        GL11.glColor4f(colour_r, colour_g, colour_b, 0.75F);
        if (brightness > 0F) GL11.glDisable(GL11.GL_LIGHTING);
    	
    	this.l1.render(scale);
    	
        if (brightness > 0F) GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1F, 1F, 1F, 1F);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        GL11.glEnable(GL11.GL_LIGHTING);
        
        this.l2.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
