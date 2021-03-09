package CreepyCoder.AdventurePack.CustomDispenser;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import CreepyCoder.AdventurePack.Core.Blocks;
import CreepyCoder.AdventurePack.Core.Dispensers;
import CreepyCoder.AdventurePack.CustomDispenser.CustomDispenserDyeData;
import CreepyCoder.AdventurePack.CustomDispenser.CustomDispenserPlaceBlockData;
import CreepyCoder.AdventurePack.Main.Plugin;

public class CustomDispenserListener implements Listener {
	
	Plugin refPlugin;
	
	public CustomDispenserListener(Plugin plugin) {
		this.refPlugin = plugin;
	}
	
	@EventHandler
	public void onDispence(BlockDispenseEvent event) {
		
		CustomDispenserPlaceBlockData DispenserPlaceBlock = new CustomDispenserPlaceBlockData();
		
		Blocks blocks = new Blocks();
		CustomDispenserDyeData customDispenser = new CustomDispenserDyeData();
		
		Block block = blocks.BlockInFrontDispenser(event);
		Material itemDispensed = event.getItem().getType();
		
		String keyDyeItem = Plugin.CustomDispenserDyeItem.canDyeBlock(block.getBlockData().getMaterial().toString(), itemDispensed.toString(), Plugin.CustomDispenserDyeData);
		
		if(keyDyeItem != null) {
			customDispenser.doBlockDye(keyDyeItem,event);
			event.setCancelled(true);
			new BukkitRunnable() {
			
				@Override
				public void run() {
	            	Dispensers dispensers = new Dispensers();
	            	dispensers.ReduceDispencerItem(event, itemDispensed);	
				}
			}.runTaskLater(refPlugin, 1L);
		}
		
		if(DispenserPlaceBlock.canBlockPlace(event)) {
			DispenserPlaceBlock.doBlockPlace(event);
			event.setCancelled(true);
			new BukkitRunnable() {
			
				@Override
				public void run() {
	            	Dispensers dispensers = new Dispensers();
	            	dispensers.ReduceDispencerItem(event, itemDispensed);	
				}
			}.runTaskLater(refPlugin, 1L);
		}
		
	}
}
