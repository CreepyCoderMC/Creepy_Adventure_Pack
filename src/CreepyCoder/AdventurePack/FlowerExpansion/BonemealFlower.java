package CreepyCoder.AdventurePack.FlowerExpansion;

import CreepyCoder.AdventurePack.Global.Function;

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

public class BonemealFlower implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		// This event will make all small flowers give a drop when bone mealed
		
		Action action = event.getAction();
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		Function function = new Function();
		Location location = block.getLocation();
		Material material = block.getType();
		
		if(!function.IsCurrentHand(event)) {
			if(function.IsItemInHandUsed(player, action, Material.BONE_MEAL)) {
				if(function.IsSmallFlower(block)) {
					event.setCancelled(true);
				}
			}
		}
		else {
			if(function.IsItemInHandUsed(player, action, Material.BONE_MEAL)) {
				if(function.IsSmallFlower(block)) {
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
		// This event will make all small flowers give a drop when infront of dispencer
		// when dispencer dispence bone meal
	
		Function function = new Function();
		Block block = function.BlockInfrontDispencer(event);
		Location location = block.getLocation();
		Material material = block.getType();
		
		if(!function.IsItemUsedByDispencer(event, Material.BONE_MEAL)) {
			return;
		}
		
		if(!function.IsSmallFlower(block)) {
			return;
		}
		
		function.ReduceDispencerItem(event, Material.BONE_MEAL);
		
		location.getWorld().playEffect(location, Effect.VILLAGER_PLANT_GROW, 10);
		location.getWorld().dropItemNaturally(location, new ItemStack(material));
	}
}
