package CreepyCoder.AdventurePack.CustomFurnace;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.BlastingRecipe;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;

import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomCampfireRecipes {
	
	private List<String> listRecipes = new ArrayList<String>();
	private ItemStack result;
	private Material source;
	private int data;
	private float experience;
	private int cookingTime;
	private CampfireRecipe addRecipe;
	
	public void loadRecipes(Plugin plugin, DataManager CustomFurnaceData) {
		
		this.listRecipes = (List<String>) CustomFurnaceData.getConfig().getList("CampfireRecipeList.key");
		
		for(Iterator<String> i = this.listRecipes.iterator(); i.hasNext(); ) {
			String key = i.next();
			this.result = new ItemStack(Material.getMaterial(CustomFurnaceData.getConfig().getString(key+".result")));
			this.source = Material.getMaterial(CustomFurnaceData.getConfig().getString(key+".source"));
			this.data = CustomFurnaceData.getConfig().getInt(key+".data");
			this.experience = new Float(CustomFurnaceData.getConfig().getString(key+".experience"));
			this.cookingTime = CustomFurnaceData.getConfig().getInt(key+".cookingTime");
			
			Bukkit.addRecipe(new CampfireRecipe(new NamespacedKey(plugin, key), this.result , this.source, this.experience, this.cookingTime));
		}
	}
	
}
