package CreepyCoder.AdventurePack.CustomPlayerInteract;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import CreepyCoder.AdventurePack.Core.PlayerHand;
import CreepyCoder.AdventurePack.Main.Plugin;
import CreepyCoder.AdventurePack.YAML.DataManager;

public class CustomOnImteractListener implements Listener{

	private String keyDyeItem;
	private PlayerHand playerHand = new PlayerHand();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		try {
		if(!playerHand.IsCurrentHand(event)) {return;}
		if(!(event.getPlayer().getInventory().getItemInOffHand().getType().toString() == "AIR")) {return;}
		keyDyeItem = Plugin.CustomOnInteractDyeItem.canDyeBlock(event.getClickedBlock().getType().toString(), event.getItem().getType().toString(), Plugin.CustomInteractDyeData);
		if(keyDyeItem != null) {
			Material newMaterial = Material.getMaterial(Plugin.CustomInteractDyeData.getConfig().get(keyDyeItem + ".result").toString());
			boolean drop = Plugin.CustomInteractDyeData.getConfig().getBoolean(keyDyeItem + ".drop");
			boolean replace = Plugin.CustomInteractDyeData.getConfig().getBoolean(keyDyeItem + ".replace");
			boolean breakBlock = Plugin.CustomInteractDyeData.getConfig().getBoolean(keyDyeItem + ".break");
			
			interactBlock(event.getPlayer(), Effect.VILLAGER_PLANT_GROW, event.getClickedBlock().getLocation(), newMaterial, drop, replace, breakBlock);
			
			return;
		}
		}
		catch (Exception e) {}
		}
	
	private void interactBlock (Player player, Effect effect, Location location, Material newMaterial, boolean drop, boolean replace, boolean breakBlock) {
		
		player.swingMainHand();
		location.getWorld().playEffect(location, effect, 10);
		player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
		
		String oldData = location.getBlock().getBlockData().toString();
		
		if(replace) {
			location.getBlock().setType(newMaterial);

			try {
	if(oldData.contains("waterlogged=")) {
	
		Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
		if(oldData.contains("waterlogged=false")) {
			newWaterlogged.setWaterlogged(false);
		}
		else {
			newWaterlogged.setWaterlogged(true);
		}
		location.getBlock().setBlockData(newWaterlogged, false);
		
}} catch (Exception e) {}

try {
	if(oldData.contains("facing=")) {		
		
		Directional newDirectional = (Directional) location.getBlock().getBlockData();
		if(oldData.contains("facing=west")) newDirectional.setFacing(BlockFace.WEST);
		if(oldData.contains("facing=north")) newDirectional.setFacing(BlockFace.NORTH);
		if(oldData.contains("facing=south")) newDirectional.setFacing(BlockFace.SOUTH);
		if(oldData.contains("facing=east")) newDirectional.setFacing(BlockFace.EAST);
		
		location.getBlock().setBlockData(newDirectional, false);
	}
} catch (Exception e) {}

	}
	if(drop) location.getWorld().dropItemNaturally(location, new ItemStack(Material.getMaterial(newMaterial.toString(), false)));
	}
}
