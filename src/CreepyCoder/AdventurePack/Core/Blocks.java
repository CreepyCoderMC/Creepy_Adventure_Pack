package CreepyCoder.AdventurePack.Core;

import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
import org.bukkit.event.block.BlockDispenseEvent;

public class Blocks {

	public static Block BlockInFrontDispenser(BlockDispenseEvent event) {
		
		Block block = event.getBlock();
		
		int xMod = 0;
		int zMod = 0;
		String faceTo = "";
		
		if (event.getBlock().getBlockData() instanceof Directional) {
		    final Directional directional = (Directional) block.getBlockData();
		    faceTo = directional.getFacing().toString();
		}
		
		switch(faceTo) {
			case "NORTH": zMod = -1; break;
			case "SOUTH": zMod = 1; break;
			case "EAST": xMod = 1; break;
			case "WEST": xMod = -1; break;
		}
		
		return event.getBlock().getRelative(0 + xMod, 0, 0 + zMod);
	}	
}
