package CreepyCoder.AdventurePack.CustomRecipes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.StonecuttingRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.IngredientFunction;

public class CustomStoneCutterRecipe {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	private IngredientFunction IngredientFunction = new IngredientFunction();

	//private boolean Enable;
	private ItemStack Result;
	private String Source;
	private int Amount;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	public List<String> KeyList = new ArrayList<String>();
	
	@SuppressWarnings({ "unchecked" })
	public CustomStoneCutterRecipe(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomStoneCutterRecipes.key");

		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Amount = dataConfig.getInt(key+".amount");
				this.Result = new ItemStack(Material.valueOf(dataConfig.getString(key+".result")), this.Amount);
				this.Source = dataConfig.getString(key+".source");	
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving stonecutter recipe "+key);
			}
			try {
				Bukkit.addRecipe(new StonecuttingRecipe(new NamespacedKey(plugin, key), this.Result ,  new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(this.Source))));
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Can not load stonecutter recipe "+key);
			}
		}
	}
}