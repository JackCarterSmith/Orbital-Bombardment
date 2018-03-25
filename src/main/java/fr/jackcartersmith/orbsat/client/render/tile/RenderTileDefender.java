package fr.jackcartersmith.orbsat.client.render.tile;

import org.lwjgl.opengl.GL11;
import fr.jackcartersmith.orbsat.client.handler.ResourceHandler;
import fr.jackcartersmith.orbsat.client.model.ModelDefender;
import fr.jackcartersmith.orbsat.common.tileentities.TileDefender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public class RenderTileDefender extends TileEntitySpecialRenderer {
    public static ModelDefender modelDefender = new ModelDefender();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 1.5, z + 0.5);

        renderCore((TileDefender) tileEntity, partialTick);

        GL11.glPopMatrix();
    }

    public static void renderCore(TileDefender tile, float partialTick) {
        GL11.glPushMatrix();
        float scale = (1F / 16F);
        GL11.glRotated(180, 2, 0, 0);

        /*
        switch (tile.facingDirection) {
            case 1:
                GL11.glRotated(180, -1, 0, 0);
                break;
            case 2:
                GL11.glRotated(90, 1, 0, 0);
                break;
            case 3:
                GL11.glRotated(90, -1, 0, 0);
                break;
            case 4:
                GL11.glRotated(90, 0, 0, -1);
                break;
            case 5:
                GL11.glRotated(90, 0, 0, 1);
        }
        */

        ResourceHandler.bindResource("textures/models/defender.png");
        modelDefender.render(null, 0F, 0F, 0F, 0F, 0F, scale);
        GL11.glEnable(GL11.GL_BLEND);
        //float rotation = tile.rotation + (partialTick * tile.rotationSpeed);
        //GL11.glRotatef(tile.rotation + (partialTick * tile.rotationSpeed), 0F, 0F, 1F);
        //GL11.glRotatef(-tile.rotation + (partialTick * tile.rotationSpeed), 0F, 0F, 1F);
        //modelDefender.Shape7.rotateAngleY = tile.rotation * 90.0F;
        //modelDefender.Shape7.rotateAngleZ = tile.rotation * 90.0F;
        
        modelDefender.Shape7.rotateAngleY = (float) (-Math.sin(tile.rotation));
        modelDefender.Shape7.rotateAngleZ = (float) (Math.sin(-2*Math.PI/3+tile.rotation));
        modelDefender.Shape7.rotateAngleX = (float) (Math.sin(2*Math.PI/3+tile.rotation));
        modelDefender.renderCrystal(null, 0F, tile.modelIllumination, 0F, 0F, 0F, scale);
        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }
}
