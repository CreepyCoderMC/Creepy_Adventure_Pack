package CreepyCoder.AdventurePack.CustomDispenser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

import CreepyCoder.AdventurePack.Core.Blocks;
import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomDispenserPlaceBlockData {

	private List<String> listRecipes = new ArrayList<String>();
	
	private String key;
	private String source;
	private String result;
	private String itemUsed;
	private String particle;
	private boolean breakBlock;
	
	public void loadRecipes(Plugin plugin, DataManager CustomBlockPlaceDispenseData) {
		
		this.listRecipes = (List<String>) CustomBlockPlaceDispenseData.getConfig().getList("CustomBlockPlaceByDispenser.key");
		
	}
	
	public boolean canBlockPlace(BlockDispenseEvent event) {
		
		Block block = Blocks.BlockInFrontDispenser(event);
	
		if(Plugin.CustomDispenserPlaceBlockData.getConfig().getList("CustomBlockPlaceByDispenser.key").contains(event.getItem().getType().toString())) {
			if(block.getType() == Material.AIR) return true;
		}
		return false;
	}
	
	public void doBlockPlace(BlockDispenseEvent event) {
		
		Block block = Blocks.BlockInFrontDispenser(event);
		Location location = block.getLocation();
		Material itemUsed = event.getItem().getType();
		
		String oldData = location.getBlock().getBlockData().toString();
		
		block.setType(Material.valueOf(itemUsed.toString()));
		
		try {
			if(oldData.contains("waterlogged=")) {
			
				Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
				if(oldData.contains("waterlogged=false")) {
					newWaterlogged.setWaterlogged(false);
				}
				else {
					newWaterlogged.setWaterlogged(true);
				}
				location.getBlock().setBlockData(newWaterlogged, false);
				
		}
			else {
				Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
				newWaterlogged.setWaterlogged(false);
				location.getBlock().setBlockData(newWaterlogged, false);
			}
				
			} catch (Exception e) {}

		try {
			if(oldData.contains("facing=")) {		
				
				Directional newDirectional = (Directional) location.getBlock().getBlockData();
				if(oldData.contains("facing=west")) newDirectional.setFacing(BlockFace.WEST);
				if(oldData.contains("facing=north")) newDirectional.setFacing(BlockFace.NORTH);
				if(oldData.contains("facing=south")) newDirectional.setFacing(BlockFace.SOUTH);
				if(oldData.contains("facing=east")) newDirectional.setFacing(BlockFace.EAST);
				
				location.getBlock().setBlockData(newDirectional, false);
			}
		} catch (Exception e) {}
		
	}
}
