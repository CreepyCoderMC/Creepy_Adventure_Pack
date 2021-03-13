package CreepyCoder.AdventurePack.YAML;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.CustomEvent.CustomPlayerInteractEvent;

public class YAMLManager {
	
	private Plugin plugin;
	private Dictionary<String, Object> YAMLContext = new Hashtable<String, Object>();
	
	public YAMLManager(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void LoadYAML(String filename)
	{	
		String YAMLContextKey = filename.replace(".yml", "");
		
		// Create or replace yaml file
		File configFile = new File(this.plugin.getDataFolder(), filename);
		this.plugin.saveResource(filename, true);
		
		FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(configFile);
		InputStream defaultStream = this.plugin.getResource(filename);
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
		dataConfig.setDefaults(defaultConfig);
		YAMLContext.put(YAMLContextKey, dataConfig);
		
		switch(YAMLContextKey)
		{
			case "CustomPlayerInteractEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomPlayerInteractEvent(plugin, dataConfig), plugin);
				break;
		}
		
		List<String> VersionList = (List<String>) dataConfig.getList(YAMLContextKey+".version");
		List<String> GroupList = (List<String>) dataConfig.getList(YAMLContextKey+".group");
		List<String> ContributorList = (List<String>) dataConfig.getList(YAMLContextKey+".contributor");
		List<String> KeyList = (List<String>) dataConfig.getList(YAMLContextKey+".key");
		List<String> StructureList = (List<String>) dataConfig.getList(YAMLContextKey+".structure");

		// Check that all structure items is of correct type
		for(Iterator<String> iStructure = StructureList.iterator(); iStructure.hasNext();) {
			String fullString = iStructure.next();
			String[] splitString = fullString.split(", ");
			
			switch (splitString[1]) {
			case "boolean":
			case "material":
			case "effect":
			case "string":
			case "contributor":
			case "version":
			case "number":
				break;
			default:
				Bukkit.getLogger().log(Level.WARNING, ""+YAMLContextKey+".structure("+ fullString +") : Type incorrectly Specified in " + filename);
			}
		}

		for(Iterator<String> iKey = KeyList.iterator(); iKey.hasNext(); ) {
			String Key = iKey.next();
			
			// Check that all events contains correct keys
			Set<String> itemKey = dataConfig.getConfigurationSection(Key).getKeys(false);
			for(Iterator<String> iStructure = StructureList.iterator(); iStructure.hasNext();) {
				String fullString = iStructure.next();
				String[] splitString = fullString.split(", ");
				
				if(!itemKey.contains(splitString[0])) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Key not found in " + filename);
			}		
		}
	}
}