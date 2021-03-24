package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.TreeType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.plugin.Plugin;

public class CustomStructureGrowEvent implements Listener {
	
	public Plugin plugin;
	public FileConfiguration dataConfig;

	//private boolean Enable;;
	private String Source;
	private boolean CancelBonemeal;
	private String Biome;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();
	
	@SuppressWarnings({ "unchecked" })
	public CustomStructureGrowEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomStructureGrowEvent.key");

	}
	
	@EventHandler
	public void onTreeGrow(StructureGrowEvent event) {
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Source = dataConfig.getString(key+".source");
				this.CancelBonemeal = dataConfig.getBoolean(key+".cancelBonemeal");
				this.Biome = dataConfig.getString(key+".biome");
				
				if(key.equals(Source) && CancelBonemeal) {
					event.setCancelled(true);
					return;
				}
				
				String[] BiomeSplit = Biome.split("\\W+");
				for (String BiomeNew : BiomeSplit) {
					if(key.equals(Source) && BiomeNew.equals(Biome)) return;
				}
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving structure grow "+key);
			}
		}
		event.setCancelled(true);
	}
}
