package CreepyCoder.AdventurePack.Function;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;

public class IngredientFunction {

	public List IngredientToMaterialList(String ingredient) {
		
		List<Material> newList = new ArrayList<>();

		String[] ingredientSplit = ingredient.split("\\W+");
		for (String ingredientNew : ingredientSplit) {
			newList.add(Material.valueOf(ingredientNew));
		}
		
		return newList;
	}
	
}
