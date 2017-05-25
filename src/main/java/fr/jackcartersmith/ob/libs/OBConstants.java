package fr.jackcartersmith.ob.libs;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class OBConstants {
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
	   
	   public static void init(File configFile)
	   {
	/*  48 */     Configuration config = new Configuration(configFile);
	     
	/*  50 */     config.load();
	     
	/*  56 */     DesignatorCommitRange = config.get("Designator Max Range", "DesignatorCommitRange", 50).getInt();
	     
	/*  58 */     OverriderMaxCharge = config.get("Max Charge on Blocks", "OverriderMaxCharge", 50000).getInt();
	     
	/*  60 */     ExtenderMaxCharge = config.get("Max Charge on Blocks", "ExtenderMaxCharge", 60000).getInt();
	     
	 
	/*  63 */     AdvPhotonInvChargeRateConstant = config.get("Charge Rates", "AdvPhotonInvChargeRate", 5).getInt();
	     
	/*  65 */     PhotonInvChargeRateConstant = config.get("Charge Rates", "PhotonInvChargeRate", 1).getInt();
	     
	 
	/*  68 */     DefenderRadius = config.get("Defender Options", "DefenderAttackRadius", 15).getInt();
	     
	 
	/*  71 */     SateliteLaunchPUCost = config.get("Satelite Launch PU Cost", "SateliteLaunchPUCost", 25000).getInt();
	     
	/*  73 */     AdvPhotonInvChargeRate = AdvPhotonInvChargeRateConstant;
	/*  74 */     PhotonInvChargeRate = PhotonInvChargeRateConstant;
	     
	/*  76 */     PhotonSendingRadius = config.get("Photon Sender Radius", "PhotonSendRadius", 30).getInt();
	     
	 
	/*  79 */     PhotonSendingMaxRate = config.get("Photon Sending Rate", "PhotonSendingMaxRate", 1000).getInt();
	     
	 
	 
	/*  83 */     DesignatorLowStrikePUUsage = config.get("Low Focus Designator Custom Settings", "DesignatorLowStrikePUUsage", 2500).getInt();
	     
	 
	/*  86 */     DesignatorLowPhotonStrikePower = config.get("Low Focus Designator Custom Settings", "DesignatorLowPhotonStrikePower", 10).getInt();
	     
	 
	/*  89 */     DesignatorLowMeteorAmount = config.get("Low Focus Designator Custom Settings", "DesignatorLowMeteorAmount", 2).getInt();
	     
	 
	/*  92 */     DesignatorLowMeteorDamage = config.get("Low Focus Designator Custom Settings", "DesignatorLowMeteorDamage", 5).getInt();
	     
	 
	/*  95 */     DesignatorLowLightning = config.get("Low Focus Designator Custom Settings", "DesignatorLowLightning", 1).getInt();
	     
	 
	 
	 
	/* 100 */     DesignatorMedStrikePUUsage = config.get("Med Focus Designator Custom Settings", "DesignatorMedStrikePUUsage", 12000).getInt();
	     
	 
	/* 103 */     DesignatorMedPhotonStrikePower = config.get("Med Focus Designator Custom Settings", "DesignatorMedPhotonStrikePower", 30).getInt();
	     
	 
	/* 106 */     DesignatorMedMeteorAmount = config.get("Med Focus Designator Custom Settings", "DesignatorMedMeteorAmount", 7).getInt();
	     
	 
	/* 109 */     DesignatorMedMeteorDamage = config.get("Med Focus Designator Custom Settings", "DesignatorMedMeteorDamage", 7).getInt();
	     
	 
	/* 112 */     DesignatorMedLightning = config.get("Med Focus Designator Custom Settings", "DesignatorMedLightning", 1).getInt();
	     
	 
	 
	 
	/* 117 */     DesignatorHighStrikePUUsage = config.get("High Focus Designator Custom Settings", "DesignatorHighStrikePUUsage", 20000).getInt();
	     
	 
	/* 120 */     DesignatorHighPhotonStrikePower = config.get("High Focus Designator Custom Settings", "DesignatorHighPhotonStrikePower", 50).getInt();
	     
	 
	/* 123 */     DesignatorHighMeteorAmount = config.get("High Focus Designator Custom Settings", "DesignatorHighMeteorAmount", 10).getInt();
	     
	 
	/* 126 */     DesignatorHighMeteorDamage = config.get("High Focus Designator Custom Settings", "DesignatorHighMeteorDamage", 10).getInt();
	     
	 
	/* 129 */     DesignatorHighLightning = config.get("High Focus Designator Custom Settings", "DesignatorHighLightning", 1).getInt();
	     
	 
	 
	/* 133 */     config.save();
	   }
}
