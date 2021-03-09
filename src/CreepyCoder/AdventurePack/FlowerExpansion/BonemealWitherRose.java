package CreepyCoder.AdventurePack.FlowerExpansion;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import CreepyCoder.AdventurePack.Global.Function;

public class BonemealWitherRose implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		// This event will make the wither rose give a drop when bone mealed
		// with a poisonous patato
		
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		Function function = new Function();
		Location location = block.getLocation();
		Material material = block.getType();
		
		if(function.IsCurrentHand(event)) {
			if(function.IsItemInHandUsed(player, action, Material.POISONOUS_POTATO)) {
				if(block.getType() == Material.WITHER_ROSE) {
					player.swingMainHand();
					location.getWorld().playEffect(location, Effect.VILLAGER_PLANT_GROW, 10);
					player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
					location.getWorld().dropItemNaturally(location, new ItemStack(material));
				}
			}			
		}
	}
	
	@EventHandler
	public void onDispence(BlockDispenseEvent event) {
		// This event will make the wither rose give a drop when infront of dispencer
		// when dispencer dispence poisenous patato
	
		Function function = new Function();
		Block block = function.BlockInfrontDispencer(event);
		Location location = block.getLocation();
		Material material = block.getType();
		
		if(!function.IsItemUsedByDispencer(event, Material.POISONOUS_POTATO)) {
			return;
		}
		
		if(block.getType() != Material.WITHER_ROSE) {
			return;
		}
		
		function.ReduceDispencerItem(event, Material.POISONOUS_POTATO);
		
		location.getWorld().playEffect(location, Effect.VILLAGER_PLANT_GROW, 10);
		location.getWorld().playEffect(location, Effect.SMOKE, 10);
		location.getWorld().dropItemNaturally(location, new ItemStack(material));
		
		event.setCancelled(true);
	}
}
