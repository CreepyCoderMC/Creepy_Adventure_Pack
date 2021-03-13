package CreepyCoder.AdventurePack.YAML;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class YAMLValidator {
	
	public void Validate(String test) {
		
		/*
		for(Iterator<String> iKey = KeyList.iterator(); iKey.hasNext(); ) {
			String Key = iKey.next();
			for(Iterator<String> iStructure = ContributorList.iterator(); iStructure.hasNext();) {
				String fullString = iStructure.next();
				String[] splitString = fullString.split(", ");
				switch (splitString[1]) {
				case "boolean":
					break;
				case "material":
					break;
				case "effect":
					break;
				case "string":
					break;
				case "contributor":
					break;
				case "version":
					break;
				default:
					Bukkit.getLogger().log(Level.SEVERE, "CustomPlayerInteractEvent.structure("+ fullString +") : Type incorrectly Specified");
				}
			}
		}
		*/
	}
}
