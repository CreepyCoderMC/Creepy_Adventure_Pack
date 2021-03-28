package CreepyCoder.AdventurePack.CustomEvent;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomTreeGrowEvent implements Listener{
	
	public Plugin plugin;
	//public FileConfiguration dataConfig;
	
	public CustomTreeGrowEvent(Plugin plugin) {

		this.plugin = plugin;
		//this.dataConfig = dataConfig;
		//this.KeyList = (List<String>) dataConfig.getList("CustomBlockFadeEvent.key");

	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
	
		if(!PlayerFunction.IsCurrentHand(event)) return;
		if(!PlayerFunction.OffHandEmpty(event)) return;
		if(event.getPlayer().getItemInHand().getType().toString().contains("SAPLING")) {
			
			Location location = event.getClickedBlock().getLocation();
			location.setY(location.getY()+1);
			
			new BukkitRunnable() {
				@Override
				public void run() {
					if(location.getBlock().getType().toString().contains("SAPLING")) {
						int Count = 0;
						while(location.getBlock().getType().toString().contains("SAPLING") && Count < 10) {
							location.getBlock().applyBoneMeal(BlockFace.UP);
							Count = Count + 1;
						}
					}
				}
			}.runTaskLater(plugin, 200L);
		}
	}
}
