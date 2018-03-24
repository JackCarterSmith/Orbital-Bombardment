package fr.jackcartersmith.orbsat.client.handler;

import net.minecraft.client.Minecraft;

public class ClientEventHandler {
    //public static Map<EntityPlayer, XZPair<Float, Integer>> playerShieldStatus = new HashMap<EntityPlayer, XZPair<Float, Integer>>();

    public static int elapsedTicks;
    public static float previousSensitivity = 0;
    public static boolean bowZoom = false;
    public static boolean lastTickBowZoom = false;
    public static int tickSet = 0;
    public static float energyCrystalAlphaValue = 0f;
    public static float energyCrystalAlphaTarget = 0f;
    public static boolean playerHoldingWrench = false;
    public static Minecraft mc;
    //private static Random rand = new Random();
    //private static IModelCustom shieldSphere;

    public ClientEventHandler() {
        //shieldSphere = AdvancedModelLoader.loadModel(ResourceHandler.getResource("models/shieldSphere.obj"));
    }

    /*
    @SubscribeEvent
    public void tickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.START || event.type != TickEvent.Type.CLIENT || event.side != Side.CLIENT)
            return;

        for (Iterator<Map.Entry<EntityPlayer, XZPair<Float, Integer>>> i = playerShieldStatus.entrySet().iterator(); i.hasNext(); ) {
            Map.Entry<EntityPlayer, XZPair<Float, Integer>> entry = i.next();
            if (elapsedTicks - entry.getValue().getValue() > 5) i.remove();
        }


        if (mc == null) mc = Minecraft.getMinecraft();
        else if (mc.theWorld != null) {
            elapsedTicks++;
            HudHandler.clientTick();

            if (bowZoom && !lastTickBowZoom) {
                previousSensitivity = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
                Minecraft.getMinecraft().gameSettings.mouseSensitivity = previousSensitivity / 3;
            } else if (!bowZoom && lastTickBowZoom) {
                Minecraft.getMinecraft().gameSettings.mouseSensitivity = previousSensitivity;
            }

            lastTickBowZoom = bowZoom;
            if (elapsedTicks - tickSet > 10) bowZoom = false;

            if (energyCrystalAlphaValue < energyCrystalAlphaTarget) energyCrystalAlphaValue += 0.01f;
            if (energyCrystalAlphaValue > energyCrystalAlphaTarget) energyCrystalAlphaValue -= 0.01f;

            if (Math.abs(energyCrystalAlphaTarget - energyCrystalAlphaValue) <= 0.02f)
                energyCrystalAlphaTarget = rand.nextFloat();

            playerHoldingWrench = mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() == ModItems.wrench;

            searchForPlayerMount();
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void fovUpdate(FOVUpdateEvent event) {

        //region Bow FOV Update
        if (event.entity.getHeldItem() != null && (event.entity.getHeldItem().getItem() instanceof WyvernBow || event.entity.getHeldItem().getItem() instanceof DraconicBow) && Minecraft.getMinecraft().gameSettings.keyBindUseItem.getIsKeyPressed()) {

            BowHandler.BowProperties properties = new BowHandler.BowProperties(event.entity.getHeldItem(), event.entity);

            event.newfov = ((6 - properties.zoomModifier) / 6) * event.fov;

//			if (ItemNBTHelper.getString(event.entity.getItemInUse(), "mode", "").equals("sharpshooter")){
//				if (event.entity.getItemInUse().getItem() instanceof WyvernBow) zMax = 1.35f;
//				else if (event.entity.getItemInUse().getItem() instanceof DraconicBow) zMax = 2.5f;
//				bowZoom = true;
//				tickSet = elapsedTicks;
//			}

        }
        //endregion

        //region Armor move speed FOV effect cancellation
        CustomArmorHandler.ArmorSummery summery = new CustomArmorHandler.ArmorSummery().getSummery(event.entity);

        if (summery != null && summery.speedModifier > 0) {
            IAttributeInstance iattributeinstance = event.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
            float f = (float) ((iattributeinstance.getAttributeValue() / (double) event.entity.capabilities.getWalkSpeed() + 1.0D) / 2.0D);
            event.newfov /= f;
        }

        //endregion
    }
    */
}
