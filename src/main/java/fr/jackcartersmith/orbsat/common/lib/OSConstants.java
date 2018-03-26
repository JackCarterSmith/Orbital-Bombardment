package fr.jackcartersmith.orbsat.common.lib;

import java.io.File;
import fr.jackcartersmith.orbsat.common.utils.OSLogHelper;
import net.minecraftforge.common.config.Configuration;

public class OSConstants {
	public static Configuration config;  
	
	public static boolean enableLog;
	public static int BlocksIdsStartAt;
	public static int ItemIdsStartAt;
	public static int SateliteLaunchPUCost;
	public static int PhotonSendingRadius;
	public static int PhotonSendingMaxRate;
	public static int DefenderRadius;
	public static int OverriderMaxCharge;
	public static int ExtenderMaxCharge;
	public static int AdvPhotonInvChargeRate;
	public static int AdvPhotonInvChargeRateConstant;
	public static int PhotonInvChargeRate;
	public static int PhotonInvChargeRateConstant;
	public static int DesignatorCommitRange;
	public static int DesignatorLowStrikePUUsage;
	public static int DesignatorLowPhotonStrikePower;
	public static int DesignatorLowMeteorAmount;
	public static int DesignatorLowMeteorDamage;
	public static int DesignatorLowLightning;
	public static int DesignatorMedStrikePUUsage;
	public static int DesignatorMedPhotonStrikePower;
	public static int DesignatorMedMeteorAmount;
	public static int DesignatorMedMeteorDamage;
	public static int DesignatorMedLightning;
	public static int DesignatorHighStrikePUUsage;
	public static int DesignatorHighPhotonStrikePower;
	public static int DesignatorHighMeteorAmount;
	public static int DesignatorHighMeteorDamage;
	public static int DesignatorHighLightning;
	   
	public static void init(File configFile) {
        if (config == null) config = new Configuration(configFile); 
        config.load(); 
	   
        try {
        	enableLog = config.get("Logging", "EnableLog", true).getBoolean();
   	     
        	DesignatorCommitRange = config.get("Designator Max Range", "DesignatorCommitRange", 50).getInt();     
        	OverriderMaxCharge = config.get("Max Charge on Blocks", "OverriderMaxCharge", 50000).getInt();   	     
        	ExtenderMaxCharge = config.get("Max Charge on Blocks", "ExtenderMaxCharge", 60000).getInt(); 
        	AdvPhotonInvChargeRateConstant = config.get("Charge Rates", "AdvPhotonInvChargeRate", 5).getInt();     
        	PhotonInvChargeRateConstant = config.get("Charge Rates", "PhotonInvChargeRate", 1).getInt();  	 
        	DefenderRadius = config.get("Defender Options", "DefenderAttackRadius", 15).getInt();	 
        	SateliteLaunchPUCost = config.get("Satelite Launch PU Cost", "SateliteLaunchPUCost", 25000).getInt(); 	     
        	AdvPhotonInvChargeRate = AdvPhotonInvChargeRateConstant;
        	PhotonInvChargeRate = PhotonInvChargeRateConstant; 	     
        	PhotonSendingRadius = config.get("Photon Sender Radius", "PhotonSendRadius", 30).getInt();   	 
        	PhotonSendingMaxRate = config.get("Photon Sending Rate", "PhotonSendingMaxRate", 1000).getInt();   	 
        	DesignatorLowStrikePUUsage = config.get("Low Focus Designator Custom Settings", "DesignatorLowStrikePUUsage", 2500).getInt();
        	DesignatorLowPhotonStrikePower = config.get("Low Focus Designator Custom Settings", "DesignatorLowPhotonStrikePower", 10).getInt();
        	DesignatorLowMeteorAmount = config.get("Low Focus Designator Custom Settings", "DesignatorLowMeteorAmount", 2).getInt();
        	DesignatorLowMeteorDamage = config.get("Low Focus Designator Custom Settings", "DesignatorLowMeteorDamage", 5).getInt();
        	DesignatorLowLightning = config.get("Low Focus Designator Custom Settings", "DesignatorLowLightning", 1).getInt();  	 
        	DesignatorMedStrikePUUsage = config.get("Med Focus Designator Custom Settings", "DesignatorMedStrikePUUsage", 12000).getInt();  	 
        	DesignatorMedPhotonStrikePower = config.get("Med Focus Designator Custom Settings", "DesignatorMedPhotonStrikePower", 30).getInt(); 	 
        	DesignatorMedMeteorAmount = config.get("Med Focus Designator Custom Settings", "DesignatorMedMeteorAmount", 7).getInt(); 	 
        	DesignatorMedMeteorDamage = config.get("Med Focus Designator Custom Settings", "DesignatorMedMeteorDamage", 7).getInt();
        	DesignatorMedLightning = config.get("Med Focus Designator Custom Settings", "DesignatorMedLightning", 1).getInt();
        	DesignatorHighStrikePUUsage = config.get("High Focus Designator Custom Settings", "DesignatorHighStrikePUUsage", 20000).getInt();
        	DesignatorHighPhotonStrikePower = config.get("High Focus Designator Custom Settings", "DesignatorHighPhotonStrikePower", 50).getInt();
        	DesignatorHighMeteorAmount = config.get("High Focus Designator Custom Settings", "DesignatorHighMeteorAmount", 10).getInt(); 	 
        	DesignatorHighMeteorDamage = config.get("High Focus Designator Custom Settings", "DesignatorHighMeteorDamage", 10).getInt();
        	DesignatorHighLightning = config.get("High Focus Designator Custom Settings", "DesignatorHighLightning", 1).getInt();
        }
        catch (Exception e) {
            OSLogHelper.error("Unable to load Config");
            e.printStackTrace();
        }
        finally {
            if (config.hasChanged()) config.save();
        }
	}
}
