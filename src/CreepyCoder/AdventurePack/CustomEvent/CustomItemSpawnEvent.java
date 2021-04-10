package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomItemSpawnEvent implements Listener {
	
	public Plugin plugin;
	public FileConfiguration dataConfig;
	//private PlayerFunction playerFunction;
	
	private String Key;
	//private boolean Enable;
	private String Source;
	private String Drops;
	private long Chance;
    //private String Group;
    //private boolean Permission;
    //private String AddedBy;
    //private String Version;
	
	public List<String> KeyList = new ArrayList<String>();

	@SuppressWarnings({ "unchecked" })
	public CustomItemSpawnEvent(Plugin plugin, FileConfiguration dataConfig) {
		
		this.plugin = plugin;
		this.dataConfig = dataConfig;
		//this.KeyList = (List<String>) dataConfig.getList("CustomItemSpawnEvent.key");
		
	}

	@EventHandler
	public void onItemSpawnEvent(ItemSpawnEvent event) {
		
		Bukkit.getLogger().log(Level.WARNING, ""+event.getEntityType().toString()+" : "+event.getLocation().getBlock().getType().toString() +" : "+event.getEntity().getItemStack().getType());
		
		if(event.getLocation().getBlock().getType().toString().contains("LEAVES")) {
			if(event.getEntity().getItemStack().getType().toString().contains("SAPLING")) {
				
				new BukkitRunnable() {
					@Override
					public void run() {
						if(event.getEntity().isValid() && event.getEntity().isOnGround()) {
							event.getEntity().getLocation().getBlock().setType(Material.valueOf(event.getEntity().getItemStack().getType().toString()));
							event.getEntity().remove();
						}
					}
				}.runTaskLater(plugin, 200L);	
			}
		}		
	}
}
