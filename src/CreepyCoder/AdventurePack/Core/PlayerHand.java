package CreepyCoder.AdventurePack.Core;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;


public class PlayerHand {

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
	
}
