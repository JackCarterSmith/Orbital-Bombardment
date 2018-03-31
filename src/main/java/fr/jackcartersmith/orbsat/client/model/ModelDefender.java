package fr.jackcartersmith.orbsat.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;

/**
 * ModelDefender - JackCarterSmith
 * Created using Tabula 4.1.1
 */
public class ModelDefender extends ModelBase {
    public ModelRenderer crystalPlateforme;
    public ModelRenderer crystal;
    public ModelRenderer photonInjector;
    public ModelRenderer basePlateforme;
    public ModelRenderer crystalPlateforme2;
    public ModelRenderer photonInjector_1;
    public ModelRenderer baseSide1;
    public ModelRenderer baseSide2;
    public ModelRenderer baseSide3;
    public ModelRenderer baseSide4;
    public ModelRenderer crystalArmSupport1;
    public ModelRenderer crystalArmSupport2;
    public ModelRenderer crystalArmSupport3;
    public ModelRenderer crystalArmSupport4;
    public ModelRenderer shape13;
    public ModelRenderer shape14;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer shape17;
    public ModelRenderer shape18;
    public ModelRenderer support1;
    public ModelRenderer support2;
    public ModelRenderer support3;
    public ModelRenderer support4;
    public ModelRenderer support5;
    public ModelRenderer support6;
    public ModelRenderer support7;
    public ModelRenderer support8;

    public ModelDefender() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.shape15 = new ModelRenderer(this, 0, 64);
        this.shape15.setRotationPoint(2.0F, 17.0F, 0.0F);
        this.shape15.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
        this.basePlateforme = new ModelRenderer(this, 0, 0);
        this.basePlateforme.setRotationPoint(-8.0F, 23.0F, -8.0F);
        this.basePlateforme.addBox(0.0F, 0.0F, 0.0F, 16, 1, 16, 0.0F);
        this.photonInjector_1 = new ModelRenderer(this, 0, 17);
        this.photonInjector_1.setRotationPoint(-2.0F, 11.0F, -2.0F);
        this.photonInjector_1.addBox(0.0F, 0.0F, 0.0F, 4, 12, 4, 0.0F);
        this.baseSide1 = new ModelRenderer(this, 16, 17);
        this.baseSide1.mirror = true;
        this.baseSide1.setRotationPoint(-8.0F, 19.0F, -8.0F);
        this.baseSide1.addBox(0.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
        this.crystalArmSupport1 = new ModelRenderer(this, 0, 33);
        this.crystalArmSupport1.setRotationPoint(-6.0F, -1.000000000000001F, -6.0F);
        this.crystalArmSupport1.addBox(0.0F, 0.0F, 0.0F, 2, 24, 2, 0.0F);
        this.shape16 = new ModelRenderer(this, 6, 64);
        this.shape16.setRotationPoint(0.0F, 21.0F, -5.0F);
        this.shape16.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, 0.0F);
        this.baseSide4 = new ModelRenderer(this, 16, 17);
        this.baseSide4.mirror = true;
        this.baseSide4.setRotationPoint(8.0F, 19.0F, -8.0F);
        this.baseSide4.addBox(0.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
        this.setRotateAngle(baseSide4, 0.0F, -1.5707963267948966F, 0.0F);
        this.shape14 = new ModelRenderer(this, 0, 68);
        this.shape14.setRotationPoint(3.0F, 18.0F, 0.0F);
        this.shape14.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1, 0.0F);
        this.shape17 = new ModelRenderer(this, 10, 64);
        this.shape17.mirror = true;
        this.shape17.setRotationPoint(-1.0F, 14.0F, 2.0F);
        this.shape17.addBox(0.0F, 0.0F, 0.0F, 1, 9, 1, 0.0F);
        this.support8 = new ModelRenderer(this, 52, 4);
        this.support8.mirror = true;
        this.support8.setRotationPoint(5.0F, 8.0F, 5.0F);
        this.support8.addBox(0.0F, 0.0F, -0.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(support8, 0.0F, 2.356194490192345F, 0.0F);
        this.crystalArmSupport4 = new ModelRenderer(this, 0, 33);
        this.crystalArmSupport4.setRotationPoint(4.0F, -1.000000000000001F, -6.0F);
        this.crystalArmSupport4.addBox(0.0F, 0.0F, 0.0F, 2, 24, 2, 0.0F);
        this.support5 = new ModelRenderer(this, 52, 4);
        this.support5.mirror = true;
        this.support5.setRotationPoint(-5.0F, 8.0F, 5.0F);
        this.support5.addBox(0.0F, 0.0F, -0.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(support5, 0.0F, 0.7853981633974483F, 0.0F);
        this.crystalPlateforme = new ModelRenderer(this, 0, 0);
        this.crystalPlateforme.setRotationPoint(-1.5F, 10.0F, -1.5F);
        this.crystalPlateforme.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.support6 = new ModelRenderer(this, 52, 4);
        this.support6.mirror = true;
        this.support6.setRotationPoint(-5.0F, 8.0F, -5.0F);
        this.support6.addBox(0.0F, 0.0F, -0.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(support6, 0.0F, -0.7853981633974483F, 0.0F);
        this.photonInjector = new ModelRenderer(this, 8, 33);
        this.photonInjector.setRotationPoint(0.0F, -6.0F, -3.5F);
        this.photonInjector.addBox(0.0F, 0.0F, 0.0F, 5, 12, 5, 0.0F);
        this.setRotateAngle(photonInjector, 0.0F, -0.7853981633974483F, 0.0F);
        this.crystalArmSupport2 = new ModelRenderer(this, 0, 33);
        this.crystalArmSupport2.setRotationPoint(-6.0F, -1.000000000000001F, 4.0F);
        this.crystalArmSupport2.addBox(0.0F, 0.0F, 0.0F, 2, 24, 2, 0.0F);
        this.shape13 = new ModelRenderer(this, 0, 64);
        this.shape13.setRotationPoint(0.0F, 20.0F, -5.0F);
        this.shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3, 0.0F);
        this.shape18 = new ModelRenderer(this, 10, 64);
        this.shape18.mirror = true;
        this.shape18.setRotationPoint(-3.0F, 16.0F, 0.0F);
        this.shape18.addBox(0.0F, 0.0F, 0.0F, 1, 7, 1, 0.0F);
        this.baseSide2 = new ModelRenderer(this, 16, 17);
        this.baseSide2.mirror = true;
        this.baseSide2.setRotationPoint(-8.0F, 19.0F, 8.0F);
        this.baseSide2.addBox(0.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
        this.setRotateAngle(baseSide2, 0.0F, 1.5707963267948966F, 0.0F);
        this.crystal = new ModelRenderer(this, 0, 4);
        this.crystal.setRotationPoint(0.0F, 8.5F, 0.0F);
        this.crystal.addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, 0.0F);
        //this.Shape7.addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6);
        //this.Shape7.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.support1 = new ModelRenderer(this, 52, 0);
        this.support1.mirror = true;
        this.support1.setRotationPoint(-5.0F, 1.0F, 5.0F);
        this.support1.addBox(0.0F, 0.0F, -0.5F, 5, 3, 1, 0.0F);
        this.setRotateAngle(support1, 0.0F, 0.7853981633974483F, 0.0F);
        this.crystalArmSupport3 = new ModelRenderer(this, 0, 33);
        this.crystalArmSupport3.setRotationPoint(4.0F, -1.000000000000001F, 4.0F);
        this.crystalArmSupport3.addBox(0.0F, 0.0F, 0.0F, 2, 24, 2, 0.0F);
        this.support4 = new ModelRenderer(this, 52, 0);
        this.support4.mirror = true;
        this.support4.setRotationPoint(5.0F, 1.0F, 5.0F);
        this.support4.addBox(0.0F, 0.0F, -0.5F, 5, 3, 1, 0.0F);
        this.setRotateAngle(support4, 0.0F, 2.356194490192345F, 0.0F);
        this.support2 = new ModelRenderer(this, 52, 0);
        this.support2.mirror = true;
        this.support2.setRotationPoint(-5.0F, 1.0F, -5.0F);
        this.support2.addBox(0.0F, 0.0F, -0.5F, 5, 3, 1, 0.0F);
        this.setRotateAngle(support2, 0.0F, -0.7853981633974483F, 0.0F);
        this.support3 = new ModelRenderer(this, 52, 0);
        this.support3.mirror = true;
        this.support3.setRotationPoint(5.0F, 1.0F, -5.0F);
        this.support3.addBox(0.0F, 0.0F, -0.5F, 5, 3, 1, 0.0F);
        this.setRotateAngle(support3, 0.0F, -2.356194490192345F, 0.0F);
        this.support7 = new ModelRenderer(this, 52, 4);
        this.support7.mirror = true;
        this.support7.setRotationPoint(5.0F, 8.0F, -5.0F);
        this.support7.addBox(0.0F, 0.0F, -0.5F, 5, 1, 1, -0.2F);
        this.setRotateAngle(support7, 0.0F, -2.356194490192345F, 0.0F);
        this.crystalPlateforme2 = new ModelRenderer(this, 0, 0);
        this.crystalPlateforme2.setRotationPoint(-1.5F, 6.0F, -1.5F);
        this.crystalPlateforme2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3, 0.0F);
        this.baseSide3 = new ModelRenderer(this, 16, 17);
        this.baseSide3.mirror = true;
        this.baseSide3.setRotationPoint(8.0F, 19.0F, 8.0F);
        this.baseSide3.addBox(0.0F, 0.0F, 0.0F, 16, 4, 1, 0.0F);
        this.setRotateAngle(baseSide3, 0.0F, -3.141592653589793F, 0.0F);
    }

    public void render() { 
        this.shape15.render(0.0625F);
        this.basePlateforme.render(0.0625F);
        this.photonInjector_1.render(0.0625F);
        this.baseSide1.render(0.0625F);
        this.crystalArmSupport1.render(0.0625F);
        this.shape16.render(0.0625F);
        this.baseSide4.render(0.0625F);
        this.shape14.render(0.0625F);
        this.shape17.render(0.0625F);
        this.support8.render(0.0625F);
        this.crystalArmSupport4.render(0.0625F);
        this.support5.render(0.0625F);
        this.crystalPlateforme.render(0.0625F);
        this.support6.render(0.0625F);
        this.photonInjector.render(0.0625F);
        this.crystalArmSupport2.render(0.0625F);
        this.shape13.render(0.0625F);
        this.shape18.render(0.0625F);
        this.baseSide2.render(0.0625F);
        this.support1.render(0.0625F);
        this.crystalArmSupport3.render(0.0625F);
        this.support4.render(0.0625F);
        this.support2.render(0.0625F);
        this.support3.render(0.0625F);
        this.support7.render(0.0625F);
        this.crystalPlateforme2.render(0.0625F);
        this.baseSide3.render(0.0625F);
    }
    
    public void renderCrystal(float brightness) {
        float lastBrightnessX = OpenGlHelper.lastBrightnessX;
        float lastBrightnessY = OpenGlHelper.lastBrightnessY;

        float b = brightness * 200F;
        float colour = Math.min(2F, (brightness * 2F) + 0.5F);
        if (brightness > 0F) GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Math.min(200F, lastBrightnessX + b), Math.min(200F, lastBrightnessY + b));
        GL11.glColor4f(colour*10F, colour*0F, colour*20F, 0.9F);
        this.crystal.render(0.0625F);
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

/* v1.0
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
        this.Shape7.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Shape7.setTextureSize(128, 128);
        this.Shape7.mirror = true;
        this.Shape8 = new ModelRenderer(this, 10, 26);
        this.Shape8.addBox(-3.0F, 0.0F, -3.0F, 6, 2, 6);
        this.Shape8.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.Shape8.setTextureSize(128, 128);
        this.Shape8.mirror = true;
        this.setRotateAngle(this.Shape8, 0.0F, 0.0F, 0.0F);
    }

    public void render() {
        this.Shape1.render(0.0625F);
        this.Shape2.render(0.0625F);
        this.Shape3.render(0.0625F);
        this.Shape4.render(0.0625F);
        this.Shape5.render(0.0625F);
        this.Shape6.render(0.0625F);
        this.Shape8.render(0.0625F);
    }
    
    public void renderCrystal(float brightness) {
        float lastBrightnessX = OpenGlHelper.lastBrightnessX;
        float lastBrightnessY = OpenGlHelper.lastBrightnessY;

        float b = brightness * 200F;
        float colour = Math.min(2F, (brightness * 2F) + 0.5F);
        if (brightness > 0F) GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Math.min(200F, lastBrightnessX + b), Math.min(200F, lastBrightnessY + b));
        GL11.glColor4f(colour*10F, colour*0F, colour*20F, 0.9F);
        this.Shape7.render(0.0625F);
        GL11.glDisable(GL11.GL_BLEND);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        if (brightness > 0F) GL11.glEnable(GL11.GL_LIGHTING);
    }
    
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
*/
