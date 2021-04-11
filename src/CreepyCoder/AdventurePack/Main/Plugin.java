package CreepyCoder.AdventurePack.Main;

import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.YAML.YAMLManager;

public class Plugin extends JavaPlugin {
	
	public YAMLManager YAMLManager = new YAMLManager(this);
	
	@Override
    public void onEnable() {	
		
		YAMLManager.LoadYAML("CustomPlayerInteractEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomDispenserEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomBlockFadeEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomLeavesDecayEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomStructureGrowEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomTreeGrowEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomLeavesDropEvent.yml","resources/CustomEvents/");
		YAMLManager.LoadYAML("CustomItemSpawnEvent.yml","resources/CustomEvents/");
		
		YAMLManager.LoadYAML("CustomCraftRecipes.yml","resources/CustomRecipes/");
		YAMLManager.LoadYAML("CustomCampfireRecipes.yml","resources/CustomRecipes/");
		YAMLManager.LoadYAML("CustomFurnaceRecipes.yml","resources/CustomRecipes/");
		YAMLManager.LoadYAML("CustomBlastFurnaceRecipes.yml","resources/CustomRecipes/");
		YAMLManager.LoadYAML("CustomSmokerRecipes.yml","resources/CustomRecipes/");
		
	}
	
    @Override
    public void onDisable() {

    }    
}