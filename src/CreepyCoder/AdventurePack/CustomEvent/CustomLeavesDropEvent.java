package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomLeavesDropEvent implements Listener{
	
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
	public CustomLeavesDropEvent(Plugin plugin, FileConfiguration dataConfig) {
		
		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomLeavesDropEvent.key");
		
	}
	
	@EventHandler
	public void onCustomLeavesDrop(LeavesDecayEvent event) {
		
		String newDrop;
		
		try {
			for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
				this.Key = i.next();
				try {
					this.Source = dataConfig.getString(this.Key+".source");
					this.Drops = dataConfig.getString(this.Key+".drops");
					this.Chance = dataConfig.getLong(this.Key+".chance");
				
					if(Source.contentEquals(event.getBlock().getType().toString())) 
					{   
						if((long)(Math.random()*100)<this.Chance) {
							String[] DropsSplit = Drops.split("\\W+");
							newDrop = DropsSplit[(int)(Math.random()*(DropsSplit.length))];
							if(newDrop.length() < 1) newDrop = Drops;
							event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.getMaterial(newDrop, false)));
						}
					}
				}
				catch (Exception e) {}
			}			
		}
		catch (Exception e) {}	
	}

}
