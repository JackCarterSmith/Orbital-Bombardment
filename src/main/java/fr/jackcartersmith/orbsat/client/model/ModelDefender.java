package fr.jackcartersmith.orbsat.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;

public class ModelDefender extends ModelBase {
    public ModelRenderer Shape1;
    public ModelRenderer Shape2;
    public ModelRenderer Shape3;
    public ModelRenderer Shape4;
    public ModelRenderer Shape5;
    public ModelRenderer Shape6;
    public ModelRenderer Shape7;
    public ModelRenderer Shape8;

    public ModelDefender()
    {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-8.0F, 0.0F, -8.0F, 16, 1, 16);
        this.Shape1.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.Shape1.setTextureSize(128, 128);
        this.Shape1.mirror = true;
        this.setRotateAngle(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 64, 0);
        this.Shape2.addBox(0.0F, 0.0F, -4.0F, 1, 7, 8);
        this.Shape2.setRotationPoint(0.0F, 16.0F, -7.0F);
        this.Shape2.setTextureSize(128, 128);
        this.Shape2.mirror = true;
        this.setRotateAngle(this.Shape2, 0.0F, ((float)Math.PI / 2F), 0.0F);
        this.Shape3 = new ModelRenderer(this, 64, 0);
        this.Shape3.addBox(0.0F, 0.0F, -4.0F, 1, 7, 8);
        this.Shape3.setRotationPoint(-7.0F, 16.0F, 0.0F);
        this.Shape3.setTextureSize(128, 128);
        this.Shape3.mirror = true;
        this.setRotateAngle(this.Shape3, 0.0F, (float)Math.PI, 0.0F);
        this.Shape4 = new ModelRenderer(this, 64, 0);
        this.Shape4.addBox(0.0F, 0.0F, -4.0F, 1, 7, 8);
        this.Shape4.setRotationPoint(0.0F, 16.0F, 7.0F);
        this.Shape4.setTextureSize(128, 128);
        this.Shape4.mirror = true;
        this.setRotateAngle(this.Shape4, 0.0F, -((float)Math.PI / 2F), 0.0F);
        this.Shape5 = new ModelRenderer(this, 64, 0);
        this.Shape5.addBox(0.0F, 0.0F, -4.0F, 1, 7, 8);
        this.Shape5.setRotationPoint(7.0F, 16.0F, 0.0F);
        this.Shape5.setTextureSize(128, 128);
        this.Shape5.mirror = true;
        this.setRotateAngle(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 21);
        this.Shape6.addBox(-1.0F, 0.0F, -1.0F, 2, 22, 2);
        this.Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Shape6.setTextureSize(128, 128);
        this.Shape6.mirror = true;
        this.setRotateAngle(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.Shape7 = new ModelRenderer(this, 85, 0);
        this.Shape7.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        this.Shape7.setRotationPoint(0.0F, -3.5F, 0.0F);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.setRotateAngle(this.Shape7, ((float)Math.sin(0)), (float)Math.sin(Math.PI/2), (float)Math.sin(3*Math.PI/2));
        this.Shape8 = new ModelRenderer(this, 10, 26);
        this.Shape8.addBox(-3.0F, 0.0F, -3.0F, 6, 2, 6);
        this.Shape8.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotateAngle(this.Shape8, 0.0F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float rotation, float brightness, float x, float y, float z, float scale) {
        this.Shape1.render(scale);
        this.Shape2.render(scale);
        this.Shape3.render(scale);
        this.Shape4.render(scale);
        this.Shape5.render(scale);
        this.Shape6.render(scale);
        this.Shape8.render(scale);
    }
    
    public void renderCrystal(Entity entity, float rotation, float brightness, float x, float y, float z, float scale) {
        float lastBrightnessX = OpenGlHelper.lastBrightnessX;
        float lastBrightnessY = OpenGlHelper.lastBrightnessY;

        float b = brightness * 200F;
        float colour = Math.min(2F, (brightness * 2F) + 0.5F);
        if (brightness > 0F) GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Math.min(200F, lastBrightnessX + b), Math.min(200F, lastBrightnessY + b));
        GL11.glColor4f(colour*10F, colour*0F, colour*20F, 0.9F);
        this.Shape7.render(scale);
        GL11.glDisable(GL11.GL_BLEND);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        if (brightness > 0F) GL11.glEnable(GL11.GL_LIGHTING);
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
