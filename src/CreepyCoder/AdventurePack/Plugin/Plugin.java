package CreepyCoder.AdventurePack.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import CreepyCoder.AdventurePack.FlowerExpansion.BonemealFlower;
import CreepyCoder.AdventurePack.FlowerExpansion.BonemealWitherRose;

public class Plugin  extends JavaPlugin {

    public void onEnable() {
    	
    	getServer().getPluginManager().registerEvents(new BonemealFlower(), this);
    	getServer().getPluginManager().registerEvents(new BonemealWitherRose(), this);
    	
    }
 
    public void onDisable() {

    }  
	
}
