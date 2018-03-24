package fr.jackcartersmith.ob.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLaunchingSatelite extends ModelBase
{
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelLaunchingSatelite()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-4.0F, 0.0F, -4.0F, 8, 10, 8);
        this.Shape1.setRotationPoint(0.0F, 14.0F, 0.0F);
        this.Shape1.setTextureSize(64, 64);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
        this.Shape2 = new ModelRenderer(this, 32, 0);
        this.Shape2.addBox(8.0F, -7.0F, 0.0F, 9, 14, 1);
        this.Shape2.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Shape2.setTextureSize(64, 64);
        this.Shape2.mirror = true;
        this.setRotation(this.Shape2, -0.4363323F, 0.0F, 0.0F);
        this.Shape3 = new ModelRenderer(this, 32, 0);
        this.Shape3.addBox(-17.0F, -7.0F, 0.0F, 9, 14, 1);
        this.Shape3.setRotationPoint(0.0F, 19.0F, 0.0F);
        this.Shape3.setTextureSize(64, 64);
        this.Shape3.mirror = true;
        this.setRotation(this.Shape3, -0.4363323F, 0.0F, 0.0F);
        this.Shape4 = new ModelRenderer(this, 0, 18);
        this.Shape4.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1);
        this.Shape4.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Shape4.setTextureSize(64, 64);
        this.Shape4.mirror = true;
        this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
        this.Shape5 = new ModelRenderer(this, 0, 18);
        this.Shape5.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 1);
        this.Shape5.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Shape5.setTextureSize(64, 64);
        this.Shape5.mirror = true;
        this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
        this.Shape6 = new ModelRenderer(this, 0, 20);
        this.Shape6.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        this.Shape6.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.Shape6.setTextureSize(64, 64);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
        this.Shape2.render(f5);
        this.Shape2.render(f5);
        this.Shape3.render(f5);
        this.Shape3.render(f5);
        this.Shape4.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
    }

    public void renderAll()
    {
        this.Shape1.render(0.0625F);
        this.Shape2.render(0.0625F);
        this.Shape3.render(0.0625F);
        this.Shape4.render(0.0625F);
        this.Shape5.render(0.0625F);
        this.Shape6.render(0.0625F);
    }
}
