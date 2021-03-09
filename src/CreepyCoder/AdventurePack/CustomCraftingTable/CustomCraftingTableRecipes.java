package CreepyCoder.AdventurePack.CustomCraftingTable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomCraftingTableRecipes {

	private List<String> listRecipes = new ArrayList<String>();
	private boolean shaped;
	private ItemStack result;
	private int amount;
	private String ingredientA;
	private String ingredientB;
	private String ingredientC;
	private String ingredientD;
	private String ingredientE;
	private String ingredientF;
	private String ingredientG;
	private String ingredientH;
	private String ingredientI;
	private int rows;
	private int cols;
	
	private String ingredientAIngredient;
	private String ingredientBIngredient;
	private String ingredientCIngredient;
	private String ingredientDIngredient;
	private String ingredientEIngredient;
	private String ingredientFIngredient;
	private String ingredientGIngredient;
	private String ingredientHIngredient;
	private String ingredientIIngredient;
	
	private Material tempMaterial;
	
	private String IngredientRow1;
	private String IngredientRow2;
	private String IngredientRow3;
	
	public void loadRecipes(Plugin plugin, DataManager CustomCraftingTableData) {
		
		this.listRecipes = (List<String>) CustomCraftingTableData.getConfig().getList("CraftingRecipeList.key");
		
		for(Iterator<String> i = this.listRecipes.iterator(); i.hasNext(); ) {
			String key = i.next();
			this.shaped = CustomCraftingTableData.getConfig().getBoolean(key+".shaped");
			this.result = new ItemStack(Material.getMaterial(CustomCraftingTableData.getConfig().getString(key+".result")));
			this.amount = CustomCraftingTableData.getConfig().getInt(key+".amount");
			this.ingredientA = CustomCraftingTableData.getConfig().getString(key+".ingredientA");
			this.ingredientB = CustomCraftingTableData.getConfig().getString(key+".ingredientB");
			this.ingredientC = CustomCraftingTableData.getConfig().getString(key+".ingredientC");
			this.ingredientD = CustomCraftingTableData.getConfig().getString(key+".ingredientD");
			this.ingredientE = CustomCraftingTableData.getConfig().getString(key+".ingredientE");
			this.ingredientF = CustomCraftingTableData.getConfig().getString(key+".ingredientF");
			this.ingredientG = CustomCraftingTableData.getConfig().getString(key+".ingredientG");
			this.ingredientH = CustomCraftingTableData.getConfig().getString(key+".ingredientH");
			this.ingredientI = CustomCraftingTableData.getConfig().getString(key+".ingredientI");
			this.rows = CustomCraftingTableData.getConfig().getInt(key+".rows");
			this.cols = CustomCraftingTableData.getConfig().getInt(key+".cols");			
			
			result.setAmount(amount);
			
			if(shaped) {
				
				ShapedRecipe addRecipe = new ShapedRecipe(new NamespacedKey(plugin, key), this.result);
			
				if(this.ingredientA == null && rows > 0) {
					ingredientAIngredient = " ";
				}
				else {
					ingredientAIngredient = "A";
				}
			
				if(this.ingredientB == null && rows > 0) {
					ingredientBIngredient = " ";
				}
				else {
				ingredientBIngredient = "B";
				}
			
				if(this.ingredientC == null && rows > 0) {
					ingredientCIngredient = " ";
				}
				else {
					ingredientCIngredient = "C";
				}
			
				if(this.ingredientD == null && rows > 1) {
					ingredientDIngredient = " ";
				}
				else {
					ingredientDIngredient = "D";
				}
			
				if(this.ingredientE == null && rows > 1) {
					ingredientEIngredient = " ";
				}
				else {
					ingredientEIngredient = "E";
				}
			
				if(this.ingredientF == null && rows > 1) {
					ingredientFIngredient = " ";
				}
				else {
					ingredientFIngredient = "F";
				}
			
				if(this.ingredientG == null && rows > 2) {
					ingredientGIngredient = " ";
				}
				else {
					ingredientGIngredient = "G";
				}
			
				if(this.ingredientH == null && rows > 2) {
					ingredientHIngredient = " ";
				}
				else {
					ingredientHIngredient = "H";
				}
			
				if(this.ingredientI == null && rows > 2) {
					ingredientIIngredient = " ";
				}
				else {
					ingredientIIngredient = "I";
				}

				if (cols == 1) IngredientRow1 = ingredientAIngredient;
				if (cols == 2) IngredientRow1 = ingredientAIngredient + ingredientBIngredient;
				if (cols == 3) IngredientRow1 = ingredientAIngredient + ingredientBIngredient + ingredientCIngredient;

				if (cols == 1) IngredientRow2 = ingredientDIngredient;
				if (cols == 2) IngredientRow2 = ingredientDIngredient + ingredientEIngredient;
				if (cols == 3) IngredientRow2 = ingredientDIngredient + ingredientEIngredient + ingredientFIngredient;			
			
				if (cols == 1) IngredientRow3 = ingredientGIngredient;
				if (cols == 2) IngredientRow3 = ingredientGIngredient + ingredientHIngredient;
				if (cols == 3) IngredientRow3 = ingredientGIngredient + ingredientHIngredient + ingredientIIngredient;			

				if(rows == 1) addRecipe.shape(IngredientRow1);
				if(rows == 2) addRecipe.shape(IngredientRow1, IngredientRow2);
				if(rows == 3) addRecipe.shape(IngredientRow1, IngredientRow2, IngredientRow3);	
			
				if(this.ingredientA != null) addRecipe.setIngredient('A', Material.getMaterial(ingredientA));
				if(this.ingredientB != null) addRecipe.setIngredient('B', Material.getMaterial(ingredientB));
				if(this.ingredientC != null) addRecipe.setIngredient('C', Material.getMaterial(ingredientC));
				if(this.ingredientD != null) addRecipe.setIngredient('D', Material.getMaterial(ingredientD));
				if(this.ingredientE != null) addRecipe.setIngredient('E', Material.getMaterial(ingredientE));
				if(this.ingredientF != null) addRecipe.setIngredient('F', Material.getMaterial(ingredientF));
				if(this.ingredientG != null) addRecipe.setIngredient('G', Material.getMaterial(ingredientG));
				if(this.ingredientH != null) addRecipe.setIngredient('H', Material.getMaterial(ingredientH));
				if(this.ingredientI != null) addRecipe.setIngredient('I', Material.getMaterial(ingredientI));
			
				Bukkit.addRecipe(addRecipe);
			}
			else {
				
				ShapelessRecipe addRecipe = new ShapelessRecipe(new NamespacedKey(plugin, key), this.result);
				
				if(this.ingredientA != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientA));
				if(this.ingredientB != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientB));
				if(this.ingredientC != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientC));
				if(this.ingredientD != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientD));
				if(this.ingredientE != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientE));
				if(this.ingredientF != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientF));
				if(this.ingredientG != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientG));
				if(this.ingredientH != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientH));
				if(this.ingredientI != null) addRecipe.addIngredient(1, Material.getMaterial(ingredientI));
				
				Bukkit.addRecipe(addRecipe);
			}
		}
	}
}
