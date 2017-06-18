package fr.jackcartersmith.orbsat;

import fr.jackcartersmith.orbsat.block.BlockOSBase;
import fr.jackcartersmith.orbsat.block.BlockType_Devices;
import fr.jackcartersmith.orbsat.block.ItemBlockOSBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;

public class OSBlocks {
	public static BlockOSBase devices;
	
	public static void init(){
		devices = (BlockOSBase)new BlockOSBase("devices", Material.IRON, PropertyEnum.create("type", BlockType_Devices.class), ItemBlockOSBase.class).setOpaque(true).setHardness(2.0F).setResistance(5.0F);
	}	
}
