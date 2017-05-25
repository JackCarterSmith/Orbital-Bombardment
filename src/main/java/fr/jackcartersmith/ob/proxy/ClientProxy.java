package fr.jackcartersmith.ob.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.jackcartersmith.ob.blocks.DefenderLaserTileEntity;
import fr.jackcartersmith.ob.blocks.DefenderTileEntity;
import fr.jackcartersmith.ob.blocks.ExtenderTileEntity;
import fr.jackcartersmith.ob.blocks.InverterAdvTileEntity;
import fr.jackcartersmith.ob.blocks.LaserLowTileEntity;
import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.ob.blocks.PhotonAcceleratorTileEntity;
import fr.jackcartersmith.ob.blocks.PhotonDeceleratorTileEntity;
import fr.jackcartersmith.ob.blocks.SateliteTileEntity;
import fr.jackcartersmith.ob.libs.TESRInventoryRenderer;
import fr.jackcartersmith.ob.models.DefenderLaserRenderer;
import fr.jackcartersmith.ob.models.LaserLowRenderer;
import fr.jackcartersmith.ob.models.SateliteRenderer;
import fr.jackcartersmith.ob.models.TileEntityDefenderRenderer;
import fr.jackcartersmith.ob.models.TileEntityExtenderRenderer;
import fr.jackcartersmith.ob.models.TileEntityInverterAdvRenderer;
import fr.jackcartersmith.ob.models.TileEntityOverriderRenderer;
import fr.jackcartersmith.ob.models.TileEntityPhotonAcceleratorRenderer;
import fr.jackcartersmith.ob.models.TileEntityPhotonDeceleratorRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy{
    public static int tesrRenderId;
    
	@Override
	public void registerRender()
	{
		tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new TESRInventoryRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(DefenderTileEntity.class, new TileEntityDefenderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(PhotonAcceleratorTileEntity.class, new TileEntityPhotonAcceleratorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(PhotonDeceleratorTileEntity.class, new TileEntityPhotonDeceleratorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(InverterAdvTileEntity.class, new TileEntityInverterAdvRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(ExtenderTileEntity.class, new TileEntityExtenderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(OverriderBlockTileEntity.class, new TileEntityOverriderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(SateliteTileEntity.class, new SateliteRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(DefenderLaserTileEntity.class, new DefenderLaserRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(LaserLowTileEntity.class, new LaserLowRenderer());
	}
	
   @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work as expected because you will be getting a
        // client player even when you are on the server!
        // Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }
}
