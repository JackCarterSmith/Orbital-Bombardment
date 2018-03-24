package fr.jackcartersmith.orbsat;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.jackcartersmith.ob.blocks.ExtenderTileEntity;
import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.ob.interfaces.PhotonRecieving;
import fr.jackcartersmith.ob.interfaces.PhotonSending;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.player.EntityPlayerMP;

public class OBNetworkClient implements IMessage
{
    private List<Integer> dataArray = new ArrayList<Integer>();
    
    public OBNetworkClient() {}
    
    public OBNetworkClient(List<Integer> dataArray) {
        this.dataArray = dataArray;
    }
    
    @Override
    public void fromBytes(ByteBuf buf){
        for (int i = 0; i < 5; i++){
            this.dataArray.add(buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf){
        for (int i = 0; i < 5; i++){
            buf.writeInt(this.dataArray.get(i));
        }
    }
    
    public static class Handler implements IMessageHandler<OBNetworkClient, IMessage>{
        int function;
        int x;
        int y;
        int z;
        int data_value;
        
        @Override
        public IMessage onMessage(OBNetworkClient message, MessageContext ctx){
            try{
                this.function = message.dataArray.get(0);
                this.x = message.dataArray.get(1);
                this.y = message.dataArray.get(2);
                this.z = message.dataArray.get(3);
                this.data_value = message.dataArray.get(4);
            }catch(Exception var8){
                var8.printStackTrace();
                return null;
            }
            
            /*
            System.out.println(this.function);
            System.out.println(this.x);
            System.out.println(this.y);
            System.out.println(this.z);
            System.out.println(this.data_value);
            */
            
            try{                
                if (this.function == 1){
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    OverriderBlockTileEntity overrider = (OverriderBlockTileEntity)player.worldObj.getTileEntity(this.x, this.y, this.z);
                    
                    if (overrider != null && overrider instanceof OverriderBlockTileEntity){
                        overrider.currentCharge = this.data_value;
                    }
                }
                
                if (this.function == 3){
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    ExtenderTileEntity extender = (ExtenderTileEntity)player.worldObj.getTileEntity(this.x, this.y, this.z);
                    
                    if (extender instanceof ExtenderTileEntity){
                        extender.currentCharge = this.data_value;
                    }
                }
                
                if (this.function == 5){
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    OverriderBlockTileEntity overrider = (OverriderBlockTileEntity)player.worldObj.getTileEntity(this.x, this.y, this.z);
                    
                    if (overrider instanceof OverriderBlockTileEntity){
                        overrider.shotsLeft = this.data_value;
                    }
                }
                
                if (this.function == 7)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonSending ext2 = (PhotonSending)player.worldObj.getTileEntity(this.x, this.y, this.z);

                    if (ext2 instanceof PhotonSending)
                    {
                        ext2.setOutputRate(this.data_value);
                    }
                }

                if (this.function == 9)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonSending ext2 = (PhotonSending)player.worldObj.getTileEntity(this.x, this.y, this.z);

                    if (ext2 instanceof PhotonSending)
                    {
                        ext2.setScaledOutput(this.data_value);
                    }
                }

                if (this.function == 11)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonSending ext2 = (PhotonSending)player.worldObj.getTileEntity(this.x, this.y, this.z);

                    if (ext2 instanceof PhotonSending)
                    {
                        ext2.setListSize(this.data_value);
                    }
                }

                if (this.function == 13)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonRecieving ext3 = (PhotonRecieving)player.worldObj.getTileEntity(this.x, this.y, this.z);

                    if (ext3 instanceof PhotonRecieving)
                    {
                        ext3.setScaledOutput(this.data_value);
                    }
                }

                if (this.function == 15)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonRecieving ext3 = (PhotonRecieving)player.worldObj.getTileEntity(this.x, this.y, this.z);

                    if (ext3 instanceof PhotonRecieving)
                    {
                        ext3.setPowerIncoming(this.data_value);
                    }
                }

                if (this.function == 17)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    PhotonRecieving ext3 = (PhotonRecieving)player.worldObj.getTileEntity(this.x, this.y, this.z);
                    
                    if (ext3 instanceof PhotonRecieving)
                    {
                        ext3.setCurrentCharge(this.data_value);
                    }
                }

                if (this.function == 19)
                {
                    EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
                    player.worldObj.playSoundEffect((double)this.x, (double)this.y, (double)this.z, "ob:defender_strike", 1.0F, 1.0F);
                }
                
                return null;
            }catch (Exception var7){
                //var7.printStackTrace();
                return null;
            }
        }
    }
}
