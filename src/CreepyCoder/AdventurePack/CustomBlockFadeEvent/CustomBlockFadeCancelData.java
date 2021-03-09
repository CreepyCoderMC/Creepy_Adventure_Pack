package CreepyCoder.AdventurePack.CustomBlockFadeEvent;

import java.util.ArrayList;
import java.util.List;

import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomBlockFadeCancelData {

	public List<String> listRecipes = new ArrayList<String>();
	
	public void loadBlockData(Plugin plugin, DataManager CustomBlockFadeData) {
		
		this.listRecipes = (List<String>) CustomBlockFadeData.getConfig().getList("BlockFadeCancelList.key");
		
	}
	
	public boolean isBlockInList(String block) {
				
		return this.listRecipes.contains(block);
		
	}
}