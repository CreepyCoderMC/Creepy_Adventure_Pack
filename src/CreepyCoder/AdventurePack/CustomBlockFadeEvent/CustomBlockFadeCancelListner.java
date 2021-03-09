package CreepyCoder.AdventurePack.CustomBlockFadeEvent;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFadeEvent;

import CreepyCoder.AdventurePack.Main.Plugin;

public class CustomBlockFadeCancelListner implements Listener {
	
	@EventHandler
	public void blockFade(BlockFadeEvent event){	

			if(Plugin.CustomBlockFadeCancelItem.isBlockInList(event.getBlock().getType().toString())) event.setCancelled(true);
		}
	}