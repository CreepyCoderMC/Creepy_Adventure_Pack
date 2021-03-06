package CreepyCoder.AdventurePack.CustomRecipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.IngredientFunction;

public class CustomFurnaceRecipe {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	private IngredientFunction IngredientFunction = new IngredientFunction();

	//private boolean Enable;
	private ItemStack Result;
	private String Source;
	private float Experience;
	private int CookingTime;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();

	@SuppressWarnings({ "unchecked", "deprecation" })
	public CustomFurnaceRecipe(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomFurnaceRecipes.key");

		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Result = new ItemStack(Material.valueOf(dataConfig.getString(key+".result")));
				this.Source = dataConfig.getString(key+".source");
				this.Experience = new Float(dataConfig.getString(key+".experience"));
				this.CookingTime = dataConfig.getInt(key+".cookingTime");			
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving furnace recipe "+key);
			}
			try {
				Bukkit.addRecipe(new FurnaceRecipe(new NamespacedKey(plugin, key), this.Result ,  new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(this.Source)), this.Experience, this.CookingTime));
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Can not load furnace recipe "+key);
			}
		}
	}
}