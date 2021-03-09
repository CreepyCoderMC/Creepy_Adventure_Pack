package CreepyCoder.AdventurePack.YAML;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Dictionary;
import java.util.Hashtable;
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
				return;
		}
	}
}