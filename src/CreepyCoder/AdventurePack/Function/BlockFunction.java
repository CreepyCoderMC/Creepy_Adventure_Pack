package CreepyCoder.AdventurePack.Function;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.inventory.ItemStack;

public class BlockFunction {

	public Block InFrontOfBlock(Block block) {
		
		int xMod = 0;
		int zMod = 0;
		String faceTo = "";
		
		if (block.getBlockData() instanceof Directional) {
		    final Directional directional = (Directional) block.getBlockData();
		    faceTo = directional.getFacing().toString();
		}
		
		switch(faceTo) {
			case "NORTH": zMod = -1; break;
			case "SOUTH": zMod = 1; break;
			case "EAST": xMod = 1; break;
			case "WEST": xMod = -1; break;
		}
		
		return block.getRelative(0 + xMod, 0, 0 + zMod);
	}
	
	public Block BelowBlock(Block block) {
		
		return block.getRelative(0, -1, 0);
	}
	
	public void Interact(Block currentBlock, String Result, boolean Drop, boolean InventoryAdd, boolean Replace, boolean Break, String Particle) {
		
		Location location = currentBlock.getLocation();
		location.getWorld().playEffect(location, Effect.valueOf(Particle) , 10);
		String oldData = location.getBlock().getBlockData().toString();
		
		if(Replace) {
			location.getBlock().setType(Material.valueOf(Result));
			try {
				if(oldData.contains("waterlogged=")) {	
					Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
					if(oldData.contains("waterlogged=false")) newWaterlogged.setWaterlogged(false);
					if(oldData.contains("waterlogged=true")) newWaterlogged.setWaterlogged(true);
					location.getBlock().setBlockData(newWaterlogged, false);
				}
				else {
					Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
					newWaterlogged.setWaterlogged(false);
					location.getBlock().setBlockData(newWaterlogged, false);
				}
			}
			catch (Exception e) {}		
			try {
				if(oldData.contains("facing=")) {
					Directional newDirectional = (Directional) location.getBlock().getBlockData();
					if(oldData.contains("facing=west")) newDirectional.setFacing(BlockFace.WEST);
					if(oldData.contains("facing=north")) newDirectional.setFacing(BlockFace.NORTH);
					if(oldData.contains("facing=south")) newDirectional.setFacing(BlockFace.SOUTH);
					if(oldData.contains("facing=east")) newDirectional.setFacing(BlockFace.EAST);
					location.getBlock().setBlockData(newDirectional, false);
				}				
			}
			catch (Exception e) {}
		}		
		if(Drop) location.getWorld().dropItemNaturally(location, new ItemStack(Material.getMaterial(Result, false)));
		if(Break) location.getBlock().setType(Material.AIR);
	}
}
