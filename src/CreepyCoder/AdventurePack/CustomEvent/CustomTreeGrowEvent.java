package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomTreeGrowEvent implements Listener{
	
	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	//private boolean Enable;;
	private String Source;
	private long Delay;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();
	
	@SuppressWarnings("unchecked")
	public CustomTreeGrowEvent(Plugin plugin,  FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomTreeGrowEvent.key");

	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
	
		if(!PlayerFunction.IsCurrentHand(event)) return;
		if(!PlayerFunction.OffHandEmpty(event)) return;
		if(event.getPlayer().getItemInHand().getType().toString().contains("SAPLING")) {
			
			for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
				String key = i.next();
				try {
					this.Source = dataConfig.getString(key+".source");
					this.Delay = dataConfig.getLong(key+".delay");
					
					if(event.getPlayer().getItemInHand().getType().toString().equals(Source)) {
			
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
						}.runTaskLater(plugin, Delay);
					}
				}
				catch (Exception e) {
				}	
			}
		}
	}
}
