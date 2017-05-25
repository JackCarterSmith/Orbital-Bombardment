package fr.jackcartersmith.ob;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.jackcartersmith.ob.blocks.OverriderBlockTileEntity;
import fr.jackcartersmith.ob.libs.OBConstants;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class OBNetwork implements IMessage
{
    private List<Integer> dataArray = new ArrayList<Integer>();
    
    public OBNetwork() {}
    
    public OBNetwork(List<Integer> dataArray) {
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
    
    public static class Handler implements IMessageHandler<OBNetwork, IMessage>{
        int function;
        int xCoord;
        int yCoord;
        int zCoord;
        int data_value;
        
        public void sendChangeToClient(int xCoord, int yCoord, int zCoord, int chargeLevel)
        {
            List<Integer> bos = new ArrayList<Integer>();
            
            bos.add(5);
            bos.add(xCoord);
            bos.add(yCoord);
            bos.add(zCoord);
            bos.add(chargeLevel);

            /*
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "OB";
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            PacketDispatcher.sendPacketToAllPlayers(packet);
            */
            OrbitalBombardment.obNetwork.sendToAll(new OBNetworkClient(bos));
        }
        
        @Override
        public IMessage onMessage(OBNetwork message, MessageContext ctx){
            EntityPlayer sender = ctx.getServerHandler().playerEntity;
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            
            try{
                this.function = message.dataArray.get(0);
            }catch(Exception var22){
                var22.printStackTrace();
            }
            
            try{
                
                
                if (this.function == 2){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    this.data_value = message.dataArray.get(4);
                    
                    try{
                        List charge1 = player.worldObj.getEntitiesWithinAABB(EntityLiving.class, 
                                AxisAlignedBB.getBoundingBox((double)(this.xCoord - this.data_value / 3), 
                                (double)(this.yCoord - this.data_value / 3), (double)(this.zCoord - this.data_value / 3), 
                                (double)(this.xCoord + this.data_value / 3), (double)(this.yCoord + this.data_value / 3), 
                                (double)(this.zCoord + this.data_value / 3)));
                        
                        if (charge1.size() > 0){
                            for(int y = 0; y <= charge1.size() - 1; y++){
                                Random next = new Random();
                                EntityLiving e1 = (EntityLiving)charge1.get(y);
                                e1.setHealth(0.5F);
                                e1.motionY = (double)(this.data_value / 5);
                                e1.motionX = next.nextGaussian() * 5.0D;
                                e1.motionZ = next.nextGaussian() * 5.0D;
                            }
                        }
                    }catch(Exception var27){
                        var27.printStackTrace();
                    }
                    
                    if (this.data_value <= 10){
                        player.worldObj.playSoundAtEntity(player, "ob:explode", 0.3F, 5.0F);
                    }
                    
                    if (this.data_value > 10 && this.data_value <= 40){
                        player.worldObj.playSoundAtEntity(player, "ob:explode", 0.3F, 1.5F);
                    }
                    
                    if (this.data_value > 40){
                        player.worldObj.playSoundAtEntity(player, "ob:explode", 0.4F, 0.3F);
                    }
                    
                    player.worldObj.createExplosion((Entity)null, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (float)this.data_value, true);
                }
                
                if (this.function == 5 && message != null){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    Random rand = new Random();
                    OBNetwork$1 obNetworkServerThread = new OBNetwork$1(rand, player,this.xCoord, this.yCoord, this.zCoord);
                    obNetworkServerThread.start();
                }
                
                if (this.function == 9){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    player.worldObj.spawnEntityInWorld(new EntityLightningBolt(player.worldObj, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord));
                }
                
                if (this.function == 12){
                    synchronized (sender){
                        this.xCoord = message.dataArray.get(1);
                        this.yCoord = message.dataArray.get(2);
                        this.zCoord = message.dataArray.get(3);
                        this.data_value = message.dataArray.get(4);
                        World world = player.worldObj;
                        boolean var35 = false;
                        int var36 = this.yCoord + 1;
                        world.playSoundAtEntity(player, "ob:digging", 0.7F, 0.3F);
                        
                        do{
                            --var36;
                            
                            try{
                                Block found = world.getBlock(this.xCoord, var36, this.zCoord);
                                ItemStack itemBlock = new ItemStack(found);
                                new Random();
                                EntityItem droppedItem = new EntityItem(world, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord, itemBlock);
                                Random rand = new Random();
                                droppedItem.motionX = rand.nextGaussian() / 10.0D;
                                droppedItem.motionY = 0.8D;
                                droppedItem.motionZ = rand.nextGaussian() / 10.0D;
                                world.spawnEntityInWorld(droppedItem);
                                world.setBlockToAir(this.xCoord, var36, this.zCoord);
                                Thread.sleep(5L);
                            }catch(NullPointerException var23){
                            }catch(ConcurrentModificationException var24){
                                break;
                            }catch(Exception var25){}
                        }while(world.getBlock(this.xCoord, var36 - 1, this.zCoord) != Blocks.bedrock 
                         && world.getBlock(this.xCoord, var36 - 1, this.zCoord) != Blocks.flowing_lava
                         && world.getBlock(this.xCoord, var36 - 1, this.zCoord) != Blocks.lava
                         && world.getBlock(this.xCoord, var36 - 1, this.zCoord) != Blocks.flowing_water
                         && world.getBlock(this.xCoord, var36 - 1, this.zCoord) != Blocks.water);
                        
                        world.setBlockToAir(this.xCoord, this.yCoord + 1, this.zCoord);
                    }
                }
                
                if (this.function == 15){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    this.data_value = message.dataArray.get(4);
                    OverriderBlockTileEntity overrider = (OverriderBlockTileEntity)player.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord);
                    overrider.currentCharge -= this.data_value;
                    --overrider.shotsLeft;
                }
                
                if (this.function == 18){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    this.data_value = message.dataArray.get(4);
                    player.worldObj.playSoundAtEntity(player, "ob:satelliteLaunch", 1.0F, 1.0F);
                    OverriderBlockTileEntity overrider = (OverriderBlockTileEntity)player.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord);
                    overrider.shotsLeft += this.data_value;
                    overrider.currentCharge -= OBConstants.SateliteLaunchPUCost;
                    this.sendChangeToClient(this.xCoord, this.yCoord, this.zCoord, this.data_value);
                }
                
                if (this.function == 21){
                    this.xCoord = message.dataArray.get(1);
                    this.yCoord = message.dataArray.get(2);
                    this.zCoord = message.dataArray.get(3);
                    this.data_value = message.dataArray.get(4);
                    player.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
                    player.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
                }
                
                if (this.function == 24){
                    player.worldObj.playSoundAtEntity(player, "ob:start", 1.0F, 1.0F);
                    player.worldObj.playSoundAtEntity(player, "ob:middle", 1.0F, 0.7F);
                }
                
            }catch(Exception var28){}
            return null;
        }
    }
}
