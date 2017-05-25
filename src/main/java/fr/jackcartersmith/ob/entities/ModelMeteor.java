package fr.jackcartersmith.ob.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMeteor extends ModelBase
{
    ModelRenderer Shape1;

    public ModelMeteor()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.Shape1 = new ModelRenderer(this, 0, 0);
        this.Shape1.addBox(-3.0F, 0.0F, -3.0F, 6, 6, 6);
        this.Shape1.setRotationPoint(0.0F, 18.0F, 0.0F);
        this.Shape1.setTextureSize(64, 32);
        this.Shape1.mirror = true;
        this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Shape1.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, (Entity)null);
    }

    public void renderAll()
    {
        this.Shape1.render(0.0625F);
    }
}
