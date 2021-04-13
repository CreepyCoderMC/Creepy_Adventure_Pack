package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class CustomBlockBurnEvent implements Listener {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	//private boolean Enable;
	private String Source;
	private String  Drop;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();
	
	@SuppressWarnings({ "unchecked" })
	public CustomBlockBurnEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomBlockBurnEvent.key");

	}
	
	@EventHandler
	public void blockBurn(BlockBurnEvent event) {
		
		Location location = event.getBlock().getLocation();
		Item drop;
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Source = dataConfig.getString(key+".source");			
				this.Drop = dataConfig.getString(key+".drop");
				
				
				if(this.Source.contains(event.getBlock().getType().toString())) {
					drop = location.getWorld().dropItem(location, new ItemStack(Material.getMaterial(this.Drop, false)));
					drop.setInvulnerable(true);
				}
				
			}
			catch (Exception e) {
				Bukkit.getLogger().log(Level.WARNING, "Error with block burn event "+key);
			}
		}
	}
}
