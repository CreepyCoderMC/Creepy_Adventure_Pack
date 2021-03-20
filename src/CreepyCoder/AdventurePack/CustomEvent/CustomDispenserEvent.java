package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.BlockFunction;

public class CustomDispenserEvent implements Listener {

	private Plugin plugin;
	private FileConfiguration dataConfig;
	
	private BlockFunction BlockFunction = new BlockFunction();

	private boolean Enable;
	private String Source;
	private ItemStack Result;
	private ItemStack ItemUsed;
	private String Particle;
	private boolean Break;
	private String Group;
	private boolean Permission;
	private String AddedBy;
	private String Version;

	public List<String> KeyList = new ArrayList<String>();

	public CustomDispenserEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomDispenserEvent.key");

	}

	@EventHandler
	public void onDispence(BlockDispenseEvent event) {

		Block block = BlockFunction.InFrontOfBlock(event.getBlock());
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Result = new ItemStack(Material.valueOf(dataConfig.getString(key+".result")));
				this.Source = dataConfig.getString(key+".source");			
				this.ItemUsed = new ItemStack(Material.valueOf(dataConfig.getString(key+".itemUsed")));
				this.Particle = dataConfig.getString(key+".particle");
				this.Break = dataConfig.getBoolean(key+".break");
				
				if(block.getType().toString().equals(Source) && event.getItem().getType().toString().equals(ItemUsed.getType().toString())) {

				}
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving dispenser event "+key);
			}
		}
	}
}
