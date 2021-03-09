package CreepyCoder.AdventurePack.CustomDispenser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import CreepyCoder.AdventurePack.Core.Blocks;
import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomDispenserDyeData {

	private List<String> listRecipes = new ArrayList<String>();
	
	private String key;
	private String source;
	private String result;
	private String itemUsed;
	private String particle;
	private boolean breakBlock;
	
	public void loadRecipes(Plugin plugin, DataManager CustomDyeDispenseData) {
		
		this.listRecipes = (List<String>) CustomDyeDispenseData.getConfig().getList("CustomDyeItemByDispenser.key");
		
	}
	
	public String canDyeBlock(String block, String itemDispensed, DataManager CustomDispenserDyeData) {
		
		for(Iterator<String> i = this.listRecipes.iterator(); i.hasNext(); ) {
			key = i.next();
		
			source = CustomDispenserDyeData.getConfig().getString(key+".source");
			itemUsed = CustomDispenserDyeData.getConfig().getString(key+".itemUsed");
			
			if(source.contentEquals(block) && itemUsed.contentEquals(itemDispensed)) {
				return key.toString();
			}
		}
		return null;
	}
	
	public void doBlockDye(String key, BlockDispenseEvent event) {

		result = Plugin.CustomDispenserDyeData.getConfig().getString(key+".result");
		itemUsed = Plugin.CustomDispenserDyeData.getConfig().getString(key+".itemUsed");
		particle = Plugin.CustomDispenserDyeData.getConfig().getString(key+".particle");			
		breakBlock = Plugin.CustomDispenserDyeData.getConfig().getBoolean(key+".break");	
			
		Block block = Blocks.BlockInFrontDispenser(event);
		Location location = block.getLocation();
		
		location.getWorld().playEffect(location, Effect.VILLAGER_PLANT_GROW, 10);
		location.getWorld().dropItemNaturally(location, new ItemStack(Material.getMaterial(result, false)));
		
		if (breakBlock) {
			location.getBlock().setType(Material.AIR);
		}
	}
}
