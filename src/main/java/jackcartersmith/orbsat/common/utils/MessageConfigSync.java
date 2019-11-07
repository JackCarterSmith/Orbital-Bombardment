package jackcartersmith.orbsat.common.utils;

import io.netty.buffer.ByteBuf;
import jackcartersmith.orbsat.OrbitalSatellite;
import jackcartersmith.orbsat.OrbsatConfig;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageConfigSync implements IMessage, IMessageHandler<MessageConfigSync, IMessage> {
    @Override
    public void fromBytes(ByteBuf buf) {
    	OrbsatConfig serverVersion = new OrbsatConfig(OrbitalSatellite.INSTANCE.config, OrbitalSatellite.INSTANCE.config.getConfig());

        serverVersion.controllerCapacity = buf.readInt();
        serverVersion.wirelessGridCapacity = buf.readInt();

        OrbitalSatellite.INSTANCE.config = serverVersion;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(OrbitalSatellite.INSTANCE.config.controllerCapacity);
        buf.writeInt(OrbitalSatellite.INSTANCE.config.wirelessGridCapacity);
    }

    @Override
    public IMessage onMessage(MessageConfigSync message, MessageContext ctx) {
        return null;
    }

}
