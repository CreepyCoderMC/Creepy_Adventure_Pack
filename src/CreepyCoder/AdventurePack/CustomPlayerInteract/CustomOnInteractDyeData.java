package CreepyCoder.AdventurePack.CustomPlayerInteract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;

import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomOnInteractDyeData {

	public List<String> listRecipes = new ArrayList<String>();
	
	public void loadBlockData(Plugin plugin, DataManager CustomInteractDyeData) {
		
		this.listRecipes = (List<String>) CustomInteractDyeData.getConfig().getList("CustomDyeItemByHandList.key");
	}
	
	public String canDyeBlock(String block, String itemInHand, DataManager CustomInteractDyeData) {
		
		String checkBlock;
		String checkItemInHand;
		
		for(Iterator<String> i = this.listRecipes.iterator(); i.hasNext(); ) {
			String key = i.next();
		
			checkBlock = CustomInteractDyeData.getConfig().getString(key+".source");
			checkItemInHand = CustomInteractDyeData.getConfig().getString(key+".itemUsed");
			
			if(checkBlock.contentEquals(block) && checkItemInHand.contentEquals(itemInHand)) {
				return key.toString();
			}
		}
		return null;
	}
}
