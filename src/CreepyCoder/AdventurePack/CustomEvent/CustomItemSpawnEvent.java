package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
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

import CreepyCoder.AdventurePack.Function.BlockFunction;

public class CustomItemSpawnEvent implements Listener {
	
	public Plugin plugin;
	public FileConfiguration dataConfig;
	private BlockFunction BlockFunction = new BlockFunction();
	//private PlayerFunction playerFunction;
	
	private String Key;
	//private boolean Enable;
	private String Source;
	private String OnBlock;
	private String Action;
	private String FromBlock;
	private long Delay;
    //private String Group;
    //private boolean Permission;
    //private String AddedBy;
    //private String Version;
	
	public List<String> KeyList = new ArrayList<String>();

	@SuppressWarnings({ "unchecked" })
	public CustomItemSpawnEvent(Plugin plugin, FileConfiguration dataConfig) {
		
		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomItemSpawnEvent.key");
		
	}

	@EventHandler
	public void onItemSpawnEvent(ItemSpawnEvent event) {
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Source = dataConfig.getString(key+".source");			
				this.OnBlock = dataConfig.getString(key+".onBlock");
				this.FromBlock = dataConfig.getString(key+".fromBlock");
				this.Delay = dataConfig.getLong(key+".delay");
	
				if(this.FromBlock.contains(event.getLocation().getBlock().getType().toString())) {
					if(event.getEntity().getItemStack().getType().toString().equals(this.Source)) {
						String tempOnBlock = this.OnBlock;
							new BukkitRunnable() {
								
								@Override
								public void run() {
									if(tempOnBlock.contains(BlockFunction.BelowBlock(event.getEntity().getLocation().getBlock()).getType().toString())) {
										if(event.getEntity().isValid() && event.getEntity().isOnGround()) {
											event.getEntity().getLocation().getBlock().setType(Material.valueOf(event.getEntity().getItemStack().getType().toString()));
											event.getEntity().remove();
										}
									}
								}
								
							}.runTaskLater(plugin, this.Delay);	
						}
					}
				}
			
			catch (Exception e) {
				Bukkit.getLogger().log(Level.WARNING, "Error with item spawn event "+key);
			}
		}		
	}
}
