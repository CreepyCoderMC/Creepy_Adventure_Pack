package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
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
				
				if(event.getSpecies().toString().equals(Source)) {
					if(event.isFromBonemeal() && CancelBonemeal) {
						event.setCancelled(true);
						return;
					}
				}
				
				String[] BiomeSplit = Biome.split("\\W+");
				for (String BiomeNew : BiomeSplit) {
					if(event.getSpecies().toString().equals(Source))
						if(BiomeNew.equals(event.getLocation().getBlock().getBiome().toString())) {
							return;
						}
					}
				}
				catch (Exception e) {
					Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving structure grow "+key);
			}
		}
		event.setCancelled(true);
	}
}
