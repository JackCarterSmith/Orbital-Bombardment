package fr.jackcartersmith.ob.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class MeteorRenderer extends RenderLiving
{
    private static final ResourceLocation meteor_texture = new ResourceLocation("ob", "textures/entity/meteor.png");

    public MeteorRenderer(ModelBase par1ModelBase, float par2)
    {
        super(par1ModelBase, par2);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return meteor_texture;
    }
}
