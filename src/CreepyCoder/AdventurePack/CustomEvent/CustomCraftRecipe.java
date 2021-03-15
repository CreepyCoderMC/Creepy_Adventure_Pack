package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomCraftRecipe {

	private Plugin plugin;
	private FileConfiguration dataConfig;
	private PlayerFunction playerFunction;
	
	private String Key;
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
	
	private Material tempMaterial;
	
	private String IngredientRow1;
	private String IngredientRow2;
	private String IngredientRow3;
	    
	public List<String> KeyList = new ArrayList<String>();
	
	public CustomCraftRecipe(Plugin plugin, FileConfiguration dataConfig) {
		
		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomCraftRecipes.key");
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			this.Shaped = dataConfig.getBoolean(key+".shaped");
			this.Result = new ItemStack(Material.valueOf(dataConfig.getString(key+".result")));
			this.Amount = dataConfig.getInt(key+".amount");
			this.IngredientA = dataConfig.getString(key+".IngredientA");
			this.IngredientB = dataConfig.getString(key+".ingredientB");
			this.IngredientC = dataConfig.getString(key+".ingredientC");
			this.IngredientD = dataConfig.getString(key+".ingredientD");
			this.IngredientE = dataConfig.getString(key+".ingredientE");
			this.IngredientF = dataConfig.getString(key+".ingredientF");
			this.IngredientG = dataConfig.getString(key+".ingredientG");
			this.IngredientH = dataConfig.getString(key+".ingredientH");
			this.IngredientI = dataConfig.getString(key+".ingredientI");
			this.Rows = dataConfig.getInt(key+".Rows");
			this.Cols = dataConfig.getInt(key+".cols");			
			
			Result.setAmount(Amount);
			
			if(Shaped) {
				
				ShapedRecipe addRecipe = new ShapedRecipe(new NamespacedKey(plugin, key), this.Result);
			
				if(this.IngredientA == "AIR" && Rows > 0) {
					IngredientAIngredient = " ";
				}
				else {
					IngredientAIngredient = "A";
				}
			
				if(this.IngredientB == "AIR" && Rows > 0) {
					IngredientBIngredient = " ";
				}
				else {
				IngredientBIngredient = "B";
				}
			
				if(this.IngredientC == "AIR" && Rows > 0) {
					IngredientCIngredient = " ";
				}
				else {
					IngredientCIngredient = "C";
				}
			
				if(this.IngredientD == "AIR" && Rows > 1) {
					IngredientDIngredient = " ";
				}
				else {
					IngredientDIngredient = "D";
				}
			
				if(this.IngredientE == "AIR" && Rows > 1) {
					IngredientEIngredient = " ";
				}
				else {
					IngredientEIngredient = "E";
				}
			
				if(this.IngredientF == "AIR" && Rows > 1) {
					IngredientFIngredient = " ";
				}
				else {
					IngredientFIngredient = "F";
				}
			
				if(this.IngredientG == "AIR" && Rows > 2) {
					IngredientGIngredient = " ";
				}
				else {
					IngredientGIngredient = "G";
				}
			
				if(this.IngredientH == "AIR" && Rows > 2) {
					IngredientHIngredient = " ";
				}
				else {
					IngredientHIngredient = "H";
				}
			
				if(this.IngredientI == "AIR" && Rows > 2) {
					IngredientIIngredient = " ";
				}
				else {
					IngredientIIngredient = "I";
				}

				if (Cols == 1) IngredientRow1 = IngredientAIngredient;
				if (Cols == 2) IngredientRow1 = IngredientAIngredient + IngredientBIngredient;
				if (Cols == 3) IngredientRow1 = IngredientAIngredient + IngredientBIngredient + IngredientCIngredient;

				if (Cols == 1) IngredientRow2 = IngredientDIngredient;
				if (Cols == 2) IngredientRow2 = IngredientDIngredient + IngredientEIngredient;
				if (Cols == 3) IngredientRow2 = IngredientDIngredient + IngredientEIngredient + IngredientFIngredient;			
			
				if (Cols == 1) IngredientRow3 = IngredientGIngredient;
				if (Cols == 2) IngredientRow3 = IngredientGIngredient + IngredientHIngredient;
				if (Cols == 3) IngredientRow3 = IngredientGIngredient + IngredientHIngredient + IngredientIIngredient;			

				if(Rows == 1) addRecipe.shape(IngredientRow1);
				if(Rows == 2) addRecipe.shape(IngredientRow1, IngredientRow2);
				if(Rows == 3) addRecipe.shape(IngredientRow1, IngredientRow2, IngredientRow3);	
			
				if(this.IngredientA != "AIR") addRecipe.setIngredient('A', Material.getMaterial(IngredientA));
				if(this.IngredientB != "AIR") addRecipe.setIngredient('B', Material.getMaterial(IngredientB));
				if(this.IngredientC != "AIR") addRecipe.setIngredient('C', Material.getMaterial(IngredientC));
				if(this.IngredientD != "AIR") addRecipe.setIngredient('D', Material.getMaterial(IngredientD));
				if(this.IngredientE != "AIR") addRecipe.setIngredient('E', Material.getMaterial(IngredientE));
				if(this.IngredientF != "AIR") addRecipe.setIngredient('F', Material.getMaterial(IngredientF));
				if(this.IngredientG != "AIR") addRecipe.setIngredient('G', Material.getMaterial(IngredientG));
				if(this.IngredientH != "AIR") addRecipe.setIngredient('H', Material.getMaterial(IngredientH));
				if(this.IngredientI != "AIR") addRecipe.setIngredient('I', Material.getMaterial(IngredientI));
			
				Bukkit.addRecipe(addRecipe);
			}
			else {
				
				ShapelessRecipe addRecipe = new ShapelessRecipe(new NamespacedKey(plugin, key), this.Result);
				
				if(this.IngredientA != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientA));
				if(this.IngredientB != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientB));
				if(this.IngredientC != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientC));
				if(this.IngredientD != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientD));
				if(this.IngredientE != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientE));
				if(this.IngredientF != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientF));
				if(this.IngredientG != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientG));
				if(this.IngredientH != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientH));
				if(this.IngredientI != "AIR") addRecipe.addIngredient(1, Material.getMaterial(IngredientI));
				
				Bukkit.addRecipe(addRecipe);
			}
		}
	}
}

	
	
	

