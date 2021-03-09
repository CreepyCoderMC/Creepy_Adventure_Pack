package CreepyCoder.AdventurePack.Function;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerFunction {

	public static boolean IsCurrentHand(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.HAND) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static boolean OffHandEmpty(PlayerInteractEvent event) {
		return event.getPlayer().getInventory().getItemInOffHand().getType().toString() == "AIR";
	}
}
