package CreepyCoder.AdventurePack.Function;

import org.bukkit.block.Container;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

public class DispenserFunction {

	public void ReduceDispenserItem(BlockDispenseEvent event, String material) {
		
		Container container = (Container) event.getBlock().getState();
		
        for (ItemStack li : container.getInventory().getStorageContents()) {
            if (li != null) {
            	if (li.getType().toString().equals(material)) {
            		li.setAmount(li.getAmount()-1);
            		return;
            	}
            }
        }
	}
}