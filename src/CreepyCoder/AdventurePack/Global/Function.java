package CreepyCoder.AdventurePack.Global;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class Function {

	public boolean IsCurrentHand(PlayerInteractEvent event) {
		// This function is used to determine in PlayerInteractEvent if the item
		// used is in the main hand.
		//
		// Returns true if players current hand contains the item in equipment slot
		// Returns false if players current hand does not contains the item in equipment slot
		
		if(event.getHand() == EquipmentSlot.HAND) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean IsItemInHandUsed(Player player, Action action, Material material) {
		// This function is used to determine if item used on right click is a specified material
		//
		// Returns true if item used is as specified
		// Returns false if item used is not as specified
		
		if(action.equals(Action.RIGHT_CLICK_BLOCK ) & (player.getItemInHand().getType() == material)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean IsSmallFlower(Block block) {
		// This function is used to determine if a block is a small flower but excluding the
		// wither rose
		//
		// Return true if block is a small flower
		// Return false if block is not a small flower
		
		switch(block.getType().toString()) {
			case "OXEYE_DAISY":
			case "DANDELION":
			case "POPPY":
			case "BLUE_ORCHID":
			case "ALLIUM":
			case "AZURE_BLUET":
			case "RED_TULIP":
			case "ORANGE_TULIP":
			case "WHITE_TULIP":
			case "PINK_TULIP":
			case "CORNFLOWER":
			case "LILY_OF_THE_VALLEY": return true;
			default: return false;	
		}
	}
	
	public boolean IsItemUsedByDispencer(BlockDispenseEvent event, Material material) {
		// This function is used to determine if a dispencer are dispensing a specified item
		//
		// Returns true if dispencer dispence a specified item
		// Returns false if dispencer does not dispence a specified item
				
		ItemStack item = event.getItem();
		
		if(item.getType() == material) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Block BlockInfrontDispencer(BlockDispenseEvent event) {
		// This function is used to return the block in front of dispencer
		
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
	
	public void ReduceDispencerItem(BlockDispenseEvent event, Material material) {
		// This function removes one specified item from a dispencer
		
		Container container = (Container) event.getBlock().getState();
		
        for (ItemStack li : container.getInventory().getStorageContents()) {
            if (li != null) {
            	if (li.getType() == material) {
            		li.setAmount(li.getAmount()-1);
            		return;
            	}
            }
        }	
	}
}