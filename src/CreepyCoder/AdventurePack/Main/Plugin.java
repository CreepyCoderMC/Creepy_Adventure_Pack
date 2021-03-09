package CreepyCoder.AdventurePack.Main;

import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.YAML.DataManager;
import CreepyCoder.AdventurePack.CustomBlockFadeEvent.CustomBlockFadeCancelData;
import CreepyCoder.AdventurePack.CustomBlockFadeEvent.CustomBlockFadeCancelListner;
import CreepyCoder.AdventurePack.CustomCraftingTable.CustomCraftingTableRecipes;
import CreepyCoder.AdventurePack.CustomDispenser.CustomDispenserDyeData;
import CreepyCoder.AdventurePack.CustomDispenser.CustomDispenserListener;
import CreepyCoder.AdventurePack.CustomDispenser.CustomDispenserPlaceBlockData;
import CreepyCoder.AdventurePack.CustomFurnace.CustomCampfireRecipes;
import CreepyCoder.AdventurePack.CustomFurnace.CustomFurnaceRecipes;
import CreepyCoder.AdventurePack.CustomFurnace.CustomBlastFurnaceRecipes;
import CreepyCoder.AdventurePack.CustomPlayerInteract.CustomOnImteractListener;
import CreepyCoder.AdventurePack.CustomPlayerInteract.CustomOnInteractDyeData;

public class Plugin extends JavaPlugin {
	
	public DataManager CustomFurnaceData;
	public CustomFurnaceRecipes CustomFurnaceRecipes= new CustomFurnaceRecipes();
	
	public DataManager CustomBlastFurnaceData;
	public CustomBlastFurnaceRecipes CustomBlastFurnaceRecipes= new CustomBlastFurnaceRecipes();
	
	public DataManager CustomCampfireData;
	public CustomCampfireRecipes CustomCampfireRecipes= new CustomCampfireRecipes();
	
	public DataManager CustomCraftTableData;
	public CustomCraftingTableRecipes CustomCraftTableRecipes = new CustomCraftingTableRecipes();
	
	public DataManager CustomBlockFadeData;
	public static CustomBlockFadeCancelData CustomBlockFadeCancelItem = new CustomBlockFadeCancelData();
	
	public static DataManager CustomInteractDyeData;
	public static CustomOnInteractDyeData CustomOnInteractDyeItem = new CustomOnInteractDyeData();
	
	public static DataManager CustomDispenserDyeData;
	public static CustomDispenserDyeData CustomDispenserDyeItem = new CustomDispenserDyeData();
	
	public static DataManager CustomDispenserPlaceBlockData;
	public static CustomDispenserPlaceBlockData CustomDispenserPlaceBlockItem = new CustomDispenserPlaceBlockData();
	
	@Override
    public void onEnable() {
	
		// Load Custom Furnace Recipes
		this.CustomFurnaceData = new DataManager(this, "FurnaceRecipe.yml");
		this.CustomFurnaceRecipes.loadRecipes(this, CustomFurnaceData);
		
		// Load Custom Blast Furnace Recipes
		this.CustomBlastFurnaceData = new DataManager(this, "BlastFurnaceRecipe.yml");
		this.CustomBlastFurnaceRecipes.loadRecipes(this, CustomBlastFurnaceData);
		
		// Load Custom Camp Fire Recipes
		this.CustomCampfireData = new DataManager(this, "CampfireRecipe.yml");
		this.CustomCampfireRecipes.loadRecipes(this, CustomCampfireData);
		
		// Load Custom Craft Table Recipes
		this.CustomCraftTableData = new DataManager(this, "CraftingTableRecipe.yml");
		this.CustomCraftTableRecipes.loadRecipes(this, CustomCraftTableData);
		
		// Load Custom Block Fade Cancel Data
		this.CustomBlockFadeData = new DataManager(this, "BlockFadeCancel.yml");
		this.CustomBlockFadeCancelItem.loadBlockData(this, CustomBlockFadeData);
		
		// Load Custom Block Dye Data
		this.CustomInteractDyeData = new DataManager(this, "CustomDyeItem.yml");
		this.CustomOnInteractDyeItem.loadBlockData(this, CustomInteractDyeData);
		
		// Load Custom Dispenser Dye Data
		this.CustomDispenserDyeData = new DataManager(this, "CustomDyeDispenser.yml");
		this.CustomDispenserDyeItem.loadRecipes(this, CustomDispenserDyeData);
		
		// Load Custom Dispenser Place Data
		this.CustomDispenserPlaceBlockData = new DataManager(this, "CustomBlockPlaceDispenser.yml");
		this.CustomDispenserPlaceBlockItem.loadRecipes(this, CustomDispenserPlaceBlockData);
	    		
		// Add Block Fade Listener
    	getServer().getPluginManager().registerEvents(new CustomBlockFadeCancelListner(), this);
    	
    	// Add Block Fade Listener
    	getServer().getPluginManager().registerEvents(new CustomOnImteractListener(), this);
    	
    	// Add Dispenser Listener
    	getServer().getPluginManager().registerEvents(new CustomDispenserListener(this), this);
	}
	
    @Override
    public void onDisable() {

    }    
}