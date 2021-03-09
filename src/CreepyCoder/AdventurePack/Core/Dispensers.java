package CreepyCoder.AdventurePack.Core;

import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;

public class Dispensers {

	public void ReduceDispencerItem(BlockDispenseEvent event, Material material) {
		
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
