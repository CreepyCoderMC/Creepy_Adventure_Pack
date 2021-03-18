package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.IngredientFunction;

public class CustomCraftRecipe {

	private Plugin plugin;
	private FileConfiguration dataConfig;
	
	private IngredientFunction IngredientFunction = new IngredientFunction();

	private boolean Enable;
	private boolean Shaped;
	private ItemStack Result;
	private int Amount;
	private String IngredientA;
	private String IngredientB;
	private String IngredientC;
	private String IngredientD;
	private String IngredientE;
	private String IngredientF;
	private String IngredientG;
	private String IngredientH;
	private String IngredientI;
	private int Rows;
	private int Cols;
	private String Group;
	private boolean Permission;
	private String AddedBy;
	private String Version;

	private String IngredientAIngredient;
	private String IngredientBIngredient;
	private String IngredientCIngredient;
	private String IngredientDIngredient;
	private String IngredientEIngredient;
	private String IngredientFIngredient;
	private String IngredientGIngredient;
	private String IngredientHIngredient;
	private String IngredientIIngredient;

	private String IngredientRow1;
	private String IngredientRow2;
	private String IngredientRow3;

	public List<String> KeyList = new ArrayList<String>();

	public CustomCraftRecipe(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
	}

	public void addRecipe() {

		this.KeyList = (List<String>) dataConfig.getList("CustomCraftRecipes.key");

		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Shaped = dataConfig.getBoolean(key+".shaped");
				this.Result = new ItemStack(Material.valueOf(dataConfig.getString(key+".result")));
				this.Amount = dataConfig.getInt(key+".amount");
				this.IngredientA = dataConfig.getString(key+".ingredientA");
				this.IngredientB = dataConfig.getString(key+".ingredientB");
				this.IngredientC = dataConfig.getString(key+".ingredientC");
				this.IngredientD = dataConfig.getString(key+".ingredientD");
				this.IngredientE = dataConfig.getString(key+".ingredientE");
				this.IngredientF = dataConfig.getString(key+".ingredientF");
				this.IngredientG = dataConfig.getString(key+".ingredientG");
				this.IngredientH = dataConfig.getString(key+".ingredientH");
				this.IngredientI = dataConfig.getString(key+".ingredientI");
				this.Rows = dataConfig.getInt(key+".rows");
				this.Cols = dataConfig.getInt(key+".cols");			
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Error when retrieving crafting recipe "+key);
			}
			try {
				this.Result.setAmount(this.Amount);

				if(this.Shaped) {
					ShapedRecipe addRecipe = new ShapedRecipe(new NamespacedKey(this.plugin, key), this.Result);

					if(IngredientA.equals("AIR") && this.Rows > 0) {
						IngredientAIngredient = " ";
					}
					else {
						IngredientAIngredient = "A";
					}

					if(IngredientB.equals("AIR") && this.Rows > 0) {
						IngredientBIngredient = " ";
					}
					else {
						IngredientBIngredient = "B";
					}

					if(IngredientC.equals("AIR") && this.Rows > 0) {
						IngredientCIngredient = " ";
					}
					else {
						IngredientCIngredient = "C";
					}

					if(IngredientD.equals("AIR") && this.Rows > 1) {
						IngredientDIngredient = " ";
					}
					else {
						IngredientDIngredient = "D";
					}

					if(IngredientE.equals("AIR") && this.Rows > 1) {
						IngredientEIngredient = " ";
					}
					else {
						IngredientEIngredient = "E";
					}

					if(IngredientF.equals("AIR") && this.Rows > 1) {
						IngredientFIngredient = " ";
					}
					else {
						IngredientFIngredient = "F";
					}

					if(IngredientG.equals("AIR") && this.Rows > 2) {
						IngredientGIngredient = " ";
					}
					else {
						IngredientGIngredient = "G";
					}

					if(IngredientH.equals("AIR") && this.Rows > 2) {
						IngredientHIngredient = " ";
					}
					else {
						IngredientHIngredient = "H";
					}

					if(IngredientI.equals("AIR") && this.Rows > 2) {
						IngredientIIngredient = " ";
					}
					else {
						IngredientIIngredient = "I";
					}

					if (this.Cols == 1) IngredientRow1 = IngredientAIngredient;
					if (this.Cols == 2) IngredientRow1 = IngredientAIngredient + IngredientBIngredient;
					if (this.Cols == 3) IngredientRow1 = IngredientAIngredient + IngredientBIngredient + IngredientCIngredient;

					if (this.Cols == 1) IngredientRow2 = IngredientDIngredient;
					if (this.Cols == 2) IngredientRow2 = IngredientDIngredient + IngredientEIngredient;
					if (this.Cols == 3) IngredientRow2 = IngredientDIngredient + IngredientEIngredient + IngredientFIngredient;			

					if (this.Cols == 1) IngredientRow3 = IngredientGIngredient;
					if (this.Cols == 2) IngredientRow3 = IngredientGIngredient + IngredientHIngredient;
					if (this.Cols == 3) IngredientRow3 = IngredientGIngredient + IngredientHIngredient + IngredientIIngredient;			

					if(this.Rows == 1) addRecipe.shape(IngredientRow1);
					if(this.Rows == 2) addRecipe.shape(IngredientRow1, IngredientRow2);
					if(this.Rows == 3) addRecipe.shape(IngredientRow1, IngredientRow2, IngredientRow3);	

					if(!this.IngredientA.equals("AIR")) addRecipe.setIngredient('A', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientA)));
					if(!this.IngredientB.equals("AIR")) addRecipe.setIngredient('B', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientB)));
					if(!this.IngredientC.equals("AIR")) addRecipe.setIngredient('C', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientC)));
					if(!this.IngredientD.equals("AIR")) addRecipe.setIngredient('D', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientD)));
					if(!this.IngredientE.equals("AIR")) addRecipe.setIngredient('E', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientE)));
					if(!this.IngredientF.equals("AIR")) addRecipe.setIngredient('F', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientF)));
					if(!this.IngredientG.equals("AIR")) addRecipe.setIngredient('G', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientG)));
					if(!this.IngredientH.equals("AIR")) addRecipe.setIngredient('H', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientH)));
					if(!this.IngredientI.equals("AIR")) addRecipe.setIngredient('I', new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientI)));
					
					Bukkit.addRecipe(addRecipe);
				}
				else {

					ShapelessRecipe addRecipe = new ShapelessRecipe(new NamespacedKey(this.plugin, key), this.Result);

					if(!this.IngredientA.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientA)));
					if(!this.IngredientB.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientB)));
					if(!this.IngredientC.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientC)));
					if(!this.IngredientD.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientD)));
					if(!this.IngredientE.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientE)));
					if(!this.IngredientF.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientF)));
					if(!this.IngredientG.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientG)));
					if(!this.IngredientH.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientH)));
					if(!this.IngredientI.equals("AIR")) addRecipe.addIngredient(new RecipeChoice.MaterialChoice(IngredientFunction.IngredientToMaterialList(IngredientI)));

					Bukkit.addRecipe(addRecipe);

				}
			}
			catch (Exception e) {
				Bukkit.getLogger().log(java.util.logging.Level.WARNING, "Can not load crafting recipe "+key);
			}
		}
	}
}





