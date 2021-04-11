package CreepyCoder.AdventurePack.YAML;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.TreeSpecies;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.CustomEvent.CustomBlastFurnaceRecipe;
import CreepyCoder.AdventurePack.CustomEvent.CustomBlockFadeEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomCampfireRecipe;
import CreepyCoder.AdventurePack.CustomEvent.CustomCraftRecipe;
import CreepyCoder.AdventurePack.CustomEvent.CustomDispenserEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomFurnaceRecipe;
import CreepyCoder.AdventurePack.CustomEvent.CustomItemSpawnEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomLeavesDecayEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomLeavesDropEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomPlayerInteractEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomSmokerRecipe;
import CreepyCoder.AdventurePack.CustomEvent.CustomStructureGrowEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomTreeGrowEvent;

public class YAMLManager {
	
	private Plugin plugin;
	
	public YAMLManager(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("unchecked")
	public void LoadYAML(String filename,String folder)
	{	
		String YAMLContextKey = filename.replace(".yml", "");
		filename = folder + filename;
		
		// Create or replace yaml file
		File configFile = new File(this.plugin.getDataFolder(), filename);
		this.plugin.saveResource(filename, true);
		
		FileConfiguration dataConfig = YamlConfiguration.loadConfiguration(configFile);
		InputStream defaultStream = this.plugin.getResource(filename);
		YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
		dataConfig.setDefaults(defaultConfig);
		
		List<String> VersionList = (List<String>) dataConfig.getList(YAMLContextKey+".version");
		List<String> GroupList = (List<String>) dataConfig.getList(YAMLContextKey+".group");
		List<String> ContributorList = (List<String>) dataConfig.getList(YAMLContextKey+".contributor");
		List<String> KeyList = (List<String>) dataConfig.getList(YAMLContextKey+".key");
		List<String> StructureList = (List<String>) dataConfig.getList(YAMLContextKey+".structure");
		List<String> KeyPressList = (List<String>) dataConfig.getList(YAMLContextKey+".keyPress");
		
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
				case "group":
				case "keyPress":
				case "sound":
				case "particle":
				case "treetype":
				case "biome":
					break;
				default:
					Bukkit.getLogger().log(Level.WARNING, YAMLContextKey+".structure("+ fullString +") : Type incorrectly Specified in " + filename);
			}
		}

		for(Iterator<String> iKey = KeyList.iterator(); iKey.hasNext(); ) {
			String Key = iKey.next();
	
			// Check that all events contains correct keys and values
			try {
				Set<String> itemKey = dataConfig.getConfigurationSection(Key).getKeys(false);			
				for(Iterator<String> iStructure = StructureList.iterator(); iStructure.hasNext();) {
					String fullString = iStructure.next();
					String[] splitString = fullString.split(", ");
					if(!itemKey.contains(splitString[0])) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Key not found or empty in " + filename);
						String valueTest = dataConfig.getString(Key + "." + splitString[0]);
						switch (splitString[1]) {
							case "boolean":
								if(!(valueTest == "false" || valueTest == "true")) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid boolean value used in " + filename);
								break;
							case "material":
								if (valueTest != null) {
									String[] valueTestSplit = valueTest.split("\\W+");
									for (String valueTestNew : valueTestSplit) {
										try {	
											@SuppressWarnings("unused")
											Material materialTest = Material.valueOf(valueTestNew);
										}
										catch (Exception e) {
											Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid material value used in " + filename + " [value: "+valueTest+"]");
										}
									}
								}
								break;
							case "effect":
								if (valueTest != null) {
									try {	
										@SuppressWarnings("unused")
										Effect effectTest = Effect.valueOf(valueTest);
									}
									catch (Exception e) {
										Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid effect value used in " + filename + " [value: "+valueTest+"]");
									}
								}
								break;
							case "string":
								break;
							case "contributor":
								if(!ContributorList.contains(valueTest)) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid contributor value used in " + filename + " [value: "+valueTest+"]");
								break;
							case "version":
								if(!VersionList.contains(valueTest)) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid version value used in " + filename  + " [value: "+valueTest+"]");
								break;
							case "number":
								if (valueTest != null) {
									try {	
										@SuppressWarnings("unused")
										float testNumber = Float.parseFloat(valueTest);
									}
									catch (Exception e) {
										Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid nuber value used in " + filename  + " [value: "+valueTest+"]");
									}
								}
								break;
							case "group":
								if(!GroupList.contains(valueTest)) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid group value used in " + filename  + " [value: "+valueTest+"]");
								break;
							case "keyPress":
								if(!KeyPressList.contains(valueTest)) Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid key press value used in " + filename  + " [value: "+valueTest+"]");
								break;
							case "sound":
								if (valueTest != null) {
									try {	
										@SuppressWarnings("unused")
										Sound soundTest = Sound.valueOf(valueTest);
									}
									catch (Exception e) {
										Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid sound used in " + filename + " [value: "+valueTest+"]");
									}
								}
								break;
							case "particle":
								if (valueTest != null) {
									try {	
										@SuppressWarnings("unused")
										Particle particleTest = Particle.valueOf(valueTest);
									}
									catch (Exception e) {
										Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid particle used in " + filename + " [value: "+valueTest+"]");
									}
								}
								break;
							case "treetype":
								if (valueTest != null) {
									try {	
										@SuppressWarnings("unused")
										TreeSpecies TreeTest = TreeSpecies.valueOf(valueTest);
									}
									catch (Exception e) {
										Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid tree type used in " + filename + " [value: "+valueTest+"]");
									}
								}
								break;
							case "biome":								
								if (valueTest != null) {
									String[] valueTestSplit = valueTest.split(", ");
									for (String valueTestNew : valueTestSplit) {
										try {	
											@SuppressWarnings("unused")
											Biome BiomeTest = Biome.valueOf(valueTestNew);
										}
										catch (Exception e) {
											Bukkit.getLogger().log(Level.WARNING, Key + "." + splitString[0] + " : Invalid biome used in " + filename + " [value: "+valueTestNew+"]");
										}
									}
								}
								break;
						}
					}
				}
				catch (Exception e) {
					Bukkit.getLogger().log(Level.WARNING, Key+" : Key not found in file "+filename);
				}
		}
		
		switch(YAMLContextKey)
		{
			case "CustomPlayerInteractEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomPlayerInteractEvent(plugin, dataConfig), plugin);
				break;
			case "CustomCraftRecipes":
				@SuppressWarnings("unused") CustomCraftRecipe CustomCraftRecipe = new CustomCraftRecipe(plugin, dataConfig);
				break;
			case "CustomCampfireRecipes":
				@SuppressWarnings("unused") CustomCampfireRecipe CustomCampfireRecipe = new CustomCampfireRecipe(plugin, dataConfig);
				break;
			case "CustomFurnaceRecipes":
				@SuppressWarnings("unused") CustomFurnaceRecipe CustomFurnaceRecipe = new CustomFurnaceRecipe(plugin, dataConfig);
				break;
			case "CustomBlastFurnaceRecipes":
				@SuppressWarnings("unused") CustomBlastFurnaceRecipe CustomBlastFurnaceRecipe = new CustomBlastFurnaceRecipe(plugin, dataConfig);
				break;
			case "CustomSmokerRecipes":
				@SuppressWarnings("unused") CustomSmokerRecipe CustomSmokerRecipe = new CustomSmokerRecipe(plugin, dataConfig);
				break;
			case "CustomDispenserEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomDispenserEvent(plugin, dataConfig), plugin);
				break;
			case "CustomBlockFadeEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomBlockFadeEvent(plugin, dataConfig), plugin);
				break;
			case "CustomLeavesDecayEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomLeavesDecayEvent(plugin, dataConfig), plugin);
				break;
			case "CustomStructureGrowEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomStructureGrowEvent(plugin, dataConfig), plugin);
				break;
			case "CustomTreeGrowEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomTreeGrowEvent(plugin, dataConfig), plugin);
				break;		
			case "CustomLeavesDropEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomLeavesDropEvent(plugin, dataConfig), plugin);
				break;	
			case "CustomItemSpawnEvent":
				plugin.getServer().getPluginManager().registerEvents(new CustomItemSpawnEvent(plugin, dataConfig), plugin);
				break;	
		}
	}
}			
				
				
