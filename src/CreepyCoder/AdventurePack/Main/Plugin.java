package CreepyCoder.AdventurePack.Main;

import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.YAML.YAMLManager;

public class Plugin extends JavaPlugin {
	
	public YAMLManager YAMLManager = new YAMLManager(this);
	
	@Override
    public void onEnable() {	
		
		YAMLManager.Loader();
	
	}
	
    @Override
    public void onDisable() {

    }    
}