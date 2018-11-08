package jackcartersmith.orbsat.common.commands;

import javax.annotation.Nonnull;

import jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandResetRenders extends CommandBase{
	@Nonnull
	@Override
	public String getName()
	{
		return "resetrender";
	}

	@Nonnull
	@Override
	public String getUsage(@Nonnull ICommandSender sender)
	{
		return "Reset the render caches of Immersive Engineering and its addons";
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args)
	{
		OrbitalSatellite.proxy.clearRenderCaches();
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
}
