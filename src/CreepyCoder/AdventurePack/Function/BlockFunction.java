package CreepyCoder.AdventurePack.Function;

import org.bukkit.block.Block;
import org.bukkit.block.data.Directional;
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
	
	public void Interact(Block currentBlock, ItemStack newBlock, boolean Drop, boolean InventoryAdd, boolean replace, boolean Break) {
		
	}
}
