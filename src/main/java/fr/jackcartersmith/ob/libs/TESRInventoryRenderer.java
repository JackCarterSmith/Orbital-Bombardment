package fr.jackcartersmith.ob.libs;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.jackcartersmith.ob.blocks.OBBlocks;
import fr.jackcartersmith.ob.models.SateliteRenderer;
import fr.jackcartersmith.ob.models.TileEntityDefenderRenderer;
import fr.jackcartersmith.ob.models.TileEntityExtenderRenderer;
import fr.jackcartersmith.ob.models.TileEntityInverterAdvRenderer;
import fr.jackcartersmith.ob.models.TileEntityOverriderRenderer;
import fr.jackcartersmith.ob.models.TileEntityPhotonAcceleratorRenderer;
import fr.jackcartersmith.ob.models.TileEntityPhotonDeceleratorRenderer;
import fr.jackcartersmith.ob.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class TESRInventoryRenderer implements ISimpleBlockRenderingHandler
{

    @Override
    public int getRenderId()
    {
        return ClientProxy.tesrRenderId;
    }

    @Override
    public void renderInventoryBlock(Block arg0, int arg1, int arg2, RenderBlocks arg3)
    {
        if(arg0 == OBBlocks.defender && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityDefenderRenderer.location);
            TileEntityDefenderRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.photonAccelerator && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityPhotonAcceleratorRenderer.location);
            TileEntityPhotonAcceleratorRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.photonDecelerator && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityPhotonDeceleratorRenderer.location);
            TileEntityPhotonDeceleratorRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.photonInverterAdv && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityInverterAdvRenderer.location);
            TileEntityInverterAdvRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.extender && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityExtenderRenderer.location);
            TileEntityExtenderRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.overrider && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TileEntityOverriderRenderer.location);
            TileEntityOverriderRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
        else if(arg0 == OBBlocks.satelite && arg1 == 0)
        {
            GL11.glPushMatrix();
            GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.0F, -1.0F, 0.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(SateliteRenderer.location);
            SateliteRenderer.model.renderAll();
            GL11.glPopMatrix();
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess arg0, int arg1, int arg2, int arg3, Block arg4, int arg5, RenderBlocks arg6)
    {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int arg0)
    {
        return true;
    }

}
