package fr.jackcartersmith.orbsat.common;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.jackcartersmith.orbsat.common.items.AdvancedExtrapolCircuit;
import fr.jackcartersmith.orbsat.common.items.AdvancedTargetDevice;
import fr.jackcartersmith.orbsat.common.items.BasicExtrapolCircuit;
import fr.jackcartersmith.orbsat.common.items.BasicTargetDevice;
import fr.jackcartersmith.orbsat.common.items.InvolvedExtrapolCircuit;
import fr.jackcartersmith.orbsat.common.items.InvolvedTargetDevice;
import fr.jackcartersmith.orbsat.common.items.ItemOS;
import fr.jackcartersmith.orbsat.common.items.PhotonCapacitor;
import fr.jackcartersmith.orbsat.common.items.PhotonLense;
import fr.jackcartersmith.orbsat.common.items.PhotonCondenser;
import fr.jackcartersmith.orbsat.common.lib.OSRefs;

@GameRegistry.ObjectHolder(OSRefs.MODID)
public class OSItems{
	public static ItemOS basicExtrapolCircuit;
    public static ItemOS advancedExtrapolCircuit;
    public static ItemOS involvedExtrapolCircuit;
    public static ItemOS photonLense;
    public static ItemOS basicTargetDevice;
    public static ItemOS advancedTargetDevice;
    public static ItemOS involvedTargetDevice;
    public static ItemOS photonCondenser;
    public static ItemOS photonCapacitor;
    
    public static void init()
    {
    	photonLense = new PhotonLense();
    	photonCondenser = new PhotonCondenser();
    	photonCapacitor = new PhotonCapacitor();
    	basicExtrapolCircuit = new BasicExtrapolCircuit();
        advancedExtrapolCircuit = new AdvancedExtrapolCircuit();
        involvedExtrapolCircuit = new InvolvedExtrapolCircuit();
        basicTargetDevice = new BasicTargetDevice();
        advancedTargetDevice = new AdvancedTargetDevice();
        involvedTargetDevice = new InvolvedTargetDevice();
        
        
    	
    	
    	
    	
    	
    	/*
        //Custom ItemStacks
        wyvernEnergyCore = new ItemStack(ModItems.draconiumEnergyCore, 1, 0);
        draconicEnergyCore = new ItemStack(ModItems.draconiumEnergyCore, 1, 1);
        wyvernFluxCapacitor = new ItemStack(ModItems.draconiumFluxCapacitor, 1, 0);
        draconicFluxCapacitor = new ItemStack(ModItems.draconiumFluxCapacitor, 1, 1);

        partStabFrame = new ItemStack(reactorStabilizerParts, 1, 0);
        partStabRotorInner = new ItemStack(reactorStabilizerParts, 1, 1);
        partStabRotorOuter = new ItemStack(reactorStabilizerParts, 1, 2);
        partStabRotorAssembly = new ItemStack(reactorStabilizerParts, 1, 3);
        partStabRing = new ItemStack(reactorStabilizerParts, 1, 4);

        nuggetDraconium = new ItemStack(nugget, 1, 0);
        nuggetAwakened = new ItemStack(nugget, 1, 1);
        */
    }
    
    public static void register(final ItemOS item) {
        String name = item.getUnwrappedUnlocalizedName(item.getUnlocalizedName());
        //if (isEnabled(item)) GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
        GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
    }

    /*
    public static boolean isEnabled(Item item) {
        return !ConfigHandler.disabledNamesList.contains(item.getUnlocalizedName());
    }
    */
}
