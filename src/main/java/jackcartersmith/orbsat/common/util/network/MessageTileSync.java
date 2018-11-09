package jackcartersmith.orbsat.common.util.network;

import io.netty.buffer.ByteBuf;
import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.common.blocks.TileEntityOrbsatBase;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageTileSync implements IMessage {
	BlockPos pos;
	NBTTagCompound nbt;

	public MessageTileSync(TileEntityOrbsatBase tile, NBTTagCompound nbt)
	{
		this.pos = tile.getPos();
		this.nbt = nbt;
	}

	public MessageTileSync()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(pos.getX()).writeInt(pos.getY()).writeInt(pos.getZ());
		ByteBufUtils.writeTag(buf, this.nbt);
	}
	
	public static class HandlerServer implements IMessageHandler<MessageTileSync, IMessage>
	{
		@Override
		public IMessage onMessage(MessageTileSync message, MessageContext ctx)
		{
			WorldServer world = ctx.getServerHandler().player.getServerWorld();
			world.addScheduledTask(() -> {
				if(world.isBlockLoaded(message.pos))
				{
					TileEntity tile = world.getTileEntity(message.pos);
					if(tile instanceof TileEntityOrbsatBase)
						((TileEntityOrbsatBase)tile).receiveMessageFromClient(message.nbt);
				}
			});
			return null;
		}
	}

	public static class HandlerClient implements IMessageHandler<MessageTileSync, IMessage>
	{
		@Override
		public IMessage onMessage(MessageTileSync message, MessageContext ctx)
		{
			Minecraft.getMinecraft().addScheduledTask(() -> {
				World world = OrbitalSatellite.proxy.getClientWorld();
				if (world!=null) // This can happen if the task is scheduled right before leaving the world
				{
					TileEntity tile = world.getTileEntity(message.pos);
					if(tile instanceof TileEntityOrbsatBase)
						((TileEntityOrbsatBase)tile).receiveMessageFromServer(message.nbt);
				}
			});
			return null;
		}
	}
}
