package fr.jackcartersmith.orbsat.client.render.block;

import org.lwjgl.opengl.GL11;

import fr.jackcartersmith.orbsat.client.handler.ResourceHandler;
import fr.jackcartersmith.orbsat.client.render.tile.RenderTileDefender;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RenderDefender implements IItemRenderer{
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();
        float scale = (1F / 16F);

        if (type == ItemRenderType.INVENTORY) {
        	GL11.glScaled(0.76, 0.76, 0.76);
            GL11.glTranslated(0.5, 1.0, 0.5);
        	GL11.glRotated(180, 0, 1, 0);
        } else if (type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslated(0.5, 2, 0.5);
            GL11.glRotated(90, 0, 1, 0);
        } else if (type == ItemRenderType.ENTITY) {
        	GL11.glScaled(3.0, 3.0, 3.0);
            GL11.glTranslated(0.0, 1.5, 0.0);
        	GL11.glRotated(180, 0, 1, 0);
        }
        GL11.glRotated(180, 0, 0, 1);

        ResourceHandler.bindResource("textures/models/defender.png");
        RenderTileDefender.modelDefender.render(null, 1F, 0F, 0F, 0F, 0F, scale);
        RenderTileDefender.modelDefender.renderCrystal(null, 1F, 1F, 0F, 0F, 0F, scale);
        GL11.glPopMatrix();
    }
}
