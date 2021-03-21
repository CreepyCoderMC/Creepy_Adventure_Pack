package CreepyCoder.AdventurePack.Function;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class IngredientFunction {

	@SuppressWarnings("rawtypes")
	public List IngredientToMaterialList(String ingredient) {
		
		List<Material> newList = new ArrayList<>();

		String[] ingredientSplit = ingredient.split("\\W+");
		for (String ingredientNew : ingredientSplit) {
			newList.add(Material.valueOf(ingredientNew));
		}
		
		return newList;
	}
	
}
