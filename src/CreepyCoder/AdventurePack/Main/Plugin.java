package CreepyCoder.AdventurePack.Main;

import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.CustomEvent.CustomPlayerInteractEvent;
import CreepyCoder.AdventurePack.CustomEvent.CustomTreeGrowEvent;
import CreepyCoder.AdventurePack.YAML.YAMLManager;

public class Plugin extends JavaPlugin {
	
	public YAMLManager YAMLManager = new YAMLManager(this);
	
	@Override
    public void onEnable() {		
		YAMLManager.LoadYAML("CustomPlayerInteractEvent.yml");
		YAMLManager.LoadYAML("CustomCraftRecipes.yml");
		YAMLManager.LoadYAML("CustomCampfireRecipes.yml");
		YAMLManager.LoadYAML("CustomFurnaceRecipes.yml");
		YAMLManager.LoadYAML("CustomBlastFurnaceRecipes.yml");
		YAMLManager.LoadYAML("CustomSmokerRecipes.yml");
		YAMLManager.LoadYAML("CustomDispenserEvent.yml");
		YAMLManager.LoadYAML("CustomBlockFadeEvent.yml");
		YAMLManager.LoadYAML("CustomLeavesDecayEvent.yml");
		YAMLManager.LoadYAML("CustomStructureGrowEvent.yml");
		YAMLManager.LoadYAML("CustomTreeGrowEvent.yml");
		YAMLManager.LoadYAML("CustomLeavesDropEvent.yml");
		YAMLManager.LoadYAML("CustomItemSpawnEvent.yml");
	}
	
    @Override
    public void onDisable() {

    }    
}