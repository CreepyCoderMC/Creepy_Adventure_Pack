package CreepyCoder.AdventurePack.CustomEvent;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import CreepyCoder.AdventurePack.Function.DispenserFunction;
import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomTreeGrowEvent implements Listener{
	
	public Plugin plugin;
	//public FileConfiguration dataConfig;
	
	@SuppressWarnings({ "unchecked" })
	public CustomTreeGrowEvent(Plugin plugin) {

		this.plugin = plugin;
		//this.dataConfig = dataConfig;
		//this.KeyList = (List<String>) dataConfig.getList("CustomBlockFadeEvent.key");

	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
	/*
		if(!PlayerFunction.IsCurrentHand(event)) return;
		if(!PlayerFunction.OffHandEmpty(event)) return;
		if(event.getPlayer().getItemInHand().getType().toString().equals("OAK_SAPLING")) {
			
			Location location = event.getClickedBlock().getLocation();
			location.setY(location.getY()+1);
			
			new BukkitRunnable() {
				@Override
				public void run() {
					if(location.getBlock().getType().toString().equals("OAK_SAPLING")) {
						location.getBlock().setType(Material.AIR);
						event.getPlayer().getWorld().generateTree(location, TreeType.ACACIA);
						new BukkitRunnable() {
							@Override
							public void run() {
								if(location.getBlock().getType().toString().equals("AIR")) location.getBlock().setType(Material.OAK_SAPLING);
							}
						}.runTaskLater(plugin, 1L);
					}
				}
			}.runTaskLater(plugin, 200L);
		}*/
	}
}
