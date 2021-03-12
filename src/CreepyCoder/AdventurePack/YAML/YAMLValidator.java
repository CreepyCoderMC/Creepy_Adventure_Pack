package CreepyCoder.AdventurePack.YAML;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class YAMLValidator {
	
	public void Validate(Plugin plugin, FileConfiguration dataConfig) {
				
		List<String> VersionList = new ArrayList<String>();
		List<String> GroupList = new ArrayList<String>();
		List<String> ContributorList = new ArrayList<String>();
		List<String> KeyList = new ArrayList<String>();
		List<String> StructureList = new ArrayList<String>();
			
		VersionList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.version");
		GroupList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.group");
		ContributorList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.contributor");
		KeyList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.key");
		StructureList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.structure");
		
		for(Iterator<String> iKey = KeyList.iterator(); iKey.hasNext(); ) {
			String Key = iKey.next();
			for(Iterator<String> iStructure = ContributorList.iterator(); iStructure.hasNext();) {
				String fullString = iStructure.next();
				String[] splitString = fullString.split(", ");
				switch (splitString[1]) {
				case "boolean":
					break;
				case "":
					break;
				}
			}
			
			//if (dataConfig.getString(Key+".source") == null) Bukkit.getLogger().log(Level.SEVERE, "CustomPlayerInteractEvent.key("+ this.Key +") could not be found");
		}
	}
}
