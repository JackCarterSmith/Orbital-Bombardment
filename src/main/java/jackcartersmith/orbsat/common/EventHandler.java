package jackcartersmith.orbsat.common;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

import jackcartersmith.orbsat.OrbitalSatellite;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EventHandler {
	//public static final ArrayList<ISpawnInterdiction> interdictionTiles = new ArrayList<ISpawnInterdiction>();
	//public static HashSet<IEExplosion> currentExplosions = new HashSet<IEExplosion>();
	public static final Queue<Pair<Integer, BlockPos>> requestedBlockUpdates = new LinkedList<>();
	public static final Set<TileEntity> REMOVE_FROM_TICKING = new HashSet<>();
	
	@SubscribeEvent
	public void onLoad(WorldEvent.Load event)
	{
		OrbitalSatellite.proxy.onWorldLoad();
	}
	
	@SubscribeEvent
	public void onSave(WorldEvent.Save event)
	{
		OrbsatSaveData.setDirty(0);
	}

	@SubscribeEvent
	public void onUnload(WorldEvent.Unload event)
	{
		OrbsatSaveData.setDirty(0);
	}
	
	@SubscribeEvent
	public void onWorldTick(WorldTickEvent event)
	{
		
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLogin(PlayerLoggedInEvent event)
	{

	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public void onLogout(PlayerLoggedOutEvent event)
	{

	}
}
