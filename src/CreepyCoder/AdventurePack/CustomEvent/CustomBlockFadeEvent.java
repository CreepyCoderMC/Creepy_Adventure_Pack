package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.plugin.Plugin;

public class CustomBlockFadeEvent implements Listener {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	//private boolean Enable;
	private String Source;
	private boolean CancelEvent;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();
	
	@SuppressWarnings({ "unchecked" })
	public CustomBlockFadeEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomBlockFadeEvent.key");

	}
	
	@EventHandler
	public void blockFade(BlockFadeEvent event) {
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Source = dataConfig.getString(key+".source");			
				this.CancelEvent = dataConfig.getBoolean(key+".cancelEvent");
				
				if(event.getBlock().getType().toString().equals(Source) || CancelEvent) event.setCancelled(true);
			}
			catch (Exception e) {
				Bukkit.getLogger().log(Level.WARNING, "Error with block fade event "+key);
			}
		}
	}
}
