package fr.jackcartersmith.orbsat.common.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;

public class FMLEventHandler {
	private static boolean giftGiven = false;

    @SubscribeEvent
    public void playerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!giftGiven && event.player.getCommandSenderName().toLowerCase().equals("jackcartersmith")) {
            giftGiven = true;
            event.player.addChatComponentMessage(new ChatComponentText("Hello Jack! Take your personal satellite !"));
            event.player.worldObj.spawnEntityInWorld(new EntityItem(event.player.worldObj, event.player.posX, event.player.posY, event.player.posZ, new ItemStack(ModItems.dezilsMarshmallow)));
        }

        //ContributorHandler.onPlayerLogin(event);
    }
}
