package CreepyCoder.AdventurePack.Main;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.YAML.YAMLManager;

public class Plugin extends JavaPlugin {
	
	public YAMLManager YAMLManager = new YAMLManager(this);
	
	@Override
    public void onEnable() {		
		YAMLManager.LoadYAML("CustomPlayerInteractEvent.yml");
		YAMLManager.LoadYAML("CustomCraftRecipes.yml");
	}
	
    @Override
    public void onDisable() {

    }    
}