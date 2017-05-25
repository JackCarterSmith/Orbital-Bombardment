package fr.jackcartersmith.ob.models;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOverrider extends ModelBase
{
    ModelRenderer Base;
    ModelRenderer base2;
    ModelRenderer base3;
    ModelRenderer pole;
    ModelRenderer crystal;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelOverrider()
    {
        this.textureWidth = 256;
        this.textureHeight = 256;
        this.Base = new ModelRenderer(this, 5, 0);
        this.Base.addBox(0.0F, 0.0F, 0.0F, 16, 10, 16);
        this.Base.setRotationPoint(-8.0F, 14.0F, -8.0F);
        this.Base.setTextureSize(256, 256);
        this.Base.mirror = true;
        this.setRotation(this.Base, 0.0F, 0.0F, 0.0F);
        this.base2 = new ModelRenderer(this, 76, 0);
        this.base2.addBox(0.0F, 0.0F, 0.0F, 14, 1, 14);
        this.base2.setRotationPoint(-7.0F, 13.0F, -7.0F);
        this.base2.setTextureSize(256, 256);
        this.base2.mirror = true;
        this.setRotation(this.base2, 0.0F, 0.0F, 0.0F);
        this.base3 = new ModelRenderer(this, 146, 0);
        this.base3.addBox(0.0F, 0.0F, 0.0F, 12, 1, 12);
        this.base3.setRotationPoint(-6.0F, 12.0F, -6.0F);
        this.base3.setTextureSize(256, 256);
        this.base3.mirror = true;
        this.setRotation(this.base3, 0.0F, 0.0F, 0.0F);
        this.pole = new ModelRenderer(this, 199, 0);
        this.pole.addBox(0.0F, 0.0F, 0.0F, 2, 25, 2);
        this.pole.setRotationPoint(-1.0F, -13.0F, -1.0F);
        this.pole.setTextureSize(256, 256);
        this.pole.mirror = true;
        this.setRotation(this.pole, 0.0F, 0.0F, 0.0F);
        this.Shape1 = new ModelRenderer(this, 0, 60);
        this.Shape1.addBox(0.0F, 0.0F, 0.0F, 6, 1, 6);
        this.Shape1.setRotationPoint(-3.0F, -11.0F, -3.0F);
        this.Shape1.setTextureSize(256, 256);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 0, 72);
        this.Shape2.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4);
        this.Shape2.setRotationPoint(-2.0F, -9.0F, -2.0F);
        this.Shape2.setTextureSize(256, 256);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 0, 82);
        this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
        this.Shape3.setRotationPoint(4.0F, 0.0F, 4.0F);
        this.Shape3.setTextureSize(256, 256);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 10, 82);
        this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 12, 1);
        this.Shape4.setRotationPoint(4.0F, 0.0F, -5.0F);
        this.Shape4.setTextureSize(256, 256);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 20, 81);
        this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 13, 1);
        this.Shape5.setRotationPoint(-5.0F, 0.0F, -5.0F);
        this.Shape5.setTextureSize(256, 256);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 30, 80);
        this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 13, 1);
        this.Shape6.setRotationPoint(-5.0F, 0.0F, 4.0F);
        this.Shape6.setTextureSize(256, 256);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
        this.crystal = new ModelRenderer(this, 0, 37);
        this.crystal.addBox(-3.5F, -3.5F, -3.5F, 7, 7, 7);
        this.crystal.setRotationPoint(0F, -20.0F, 0F);
        this.crystal.setTextureSize(256, 256);
        this.crystal.mirror = true;
        GL11.glColor4d(255.0D, 255.0D, 255.0D, 255.0D);
        this.setRotation(this.crystal, 0.54F, 0F, 0.54F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.Base.render(f5);
        this.base2.render(f5);
        this.base3.render(f5);
        this.pole.render(f5);
        this.crystal.render(f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
        this.Shape5.render(f5);
        this.Shape6.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderAll()
    {
        this.Base.render(0.0625F);
        this.base2.render(0.0625F);
        this.base3.render(0.0625F);
        this.pole.render(0.0625F);
        this.Shape1.render(0.0625F);
        this.Shape2.render(0.0625F);
        this.Shape3.render(0.0625F);
        this.Shape4.render(0.0625F);
        this.Shape5.render(0.0625F);
        this.Shape6.render(0.0625F);
    }
}
