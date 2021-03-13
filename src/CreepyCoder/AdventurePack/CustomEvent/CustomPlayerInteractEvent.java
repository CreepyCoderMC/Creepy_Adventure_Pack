package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import CreepyCoder.AdventurePack.Function.PlayerFunction;

public class CustomPlayerInteractEvent implements Listener {
	
	private Plugin plugin;
	private FileConfiguration dataConfig;
	private PlayerFunction playerFunction;
	
	private String Key;
	private boolean Enable;
	private String Source;
	private String Result;
	private String ItemUsed;
	private String Particle;
	private boolean Drop;
	private boolean Replace;
	private boolean Break;
    private String Group;
    private boolean Permission;
    private String AddedBy;
    private String Version;
	
	public List<String> KeyList = new ArrayList<String>();

	public CustomPlayerInteractEvent(Plugin plugin, FileConfiguration dataConfig) {
		
		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomPlayerInteractEvent.key");
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		try {
			if(!PlayerFunction.IsCurrentHand(event)) return;
			if(!PlayerFunction.OffHandEmpty(event)) return;
			if(IsCustomInteract(event)) DoCustomInteract(event);
		}
		catch (Exception e) {}	
	}
	
	public boolean IsCustomInteract(PlayerInteractEvent event) {
	
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			this.Key = i.next();
			try {
				this.Source = dataConfig.getString(this.Key+".source");
				this.ItemUsed = dataConfig.getString(this.Key+".itemUsed");
			
				if(Source.contentEquals(event.getClickedBlock().getType().toString()) && ItemUsed.contentEquals(event.getPlayer().getItemInHand().getType().toString())) 
				{   
					this.Result = dataConfig.getString(this.Key+".result");
					this.Particle = dataConfig.getString(this.Key+".particle");
					this.Drop = dataConfig.getBoolean(this.Key+".drop");
					this.Replace = dataConfig.getBoolean(this.Key+".replace");
					this.Break = dataConfig.getBoolean(this.Key+".break");
					
					return true;
				}
			}
			catch (Exception e) {}
		}
		return false;
	}
	
	public void DoCustomInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		Location location = event.getClickedBlock().getLocation();
		
		player.swingMainHand();
		location.getWorld().playEffect(location, Effect.valueOf(Particle), 10);
		player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
		
		String oldData = location.getBlock().getBlockData().toString();
		
		if(this.Replace) {
			location.getBlock().setType(Material.valueOf(Result));
			
			try {
				if(oldData.contains("waterlogged=")) {	
					Waterlogged newWaterlogged = (Waterlogged) location.getBlock().getBlockData();
					if(oldData.contains("waterlogged=false")) newWaterlogged.setWaterlogged(false);
					if(oldData.contains("waterlogged=true")) newWaterlogged.setWaterlogged(true);
					
					location.getBlock().setBlockData(newWaterlogged, false);
				}
			}
			catch (Exception e) {}
			
			try {
				if(oldData.contains("facing=")) {
					Directional newDirectional = (Directional) location.getBlock().getBlockData();
					if(oldData.contains("facing=west")) newDirectional.setFacing(BlockFace.WEST);
					if(oldData.contains("facing=north")) newDirectional.setFacing(BlockFace.NORTH);
					if(oldData.contains("facing=south")) newDirectional.setFacing(BlockFace.SOUTH);
					if(oldData.contains("facing=east")) newDirectional.setFacing(BlockFace.EAST);
				
					location.getBlock().setBlockData(newDirectional, false);
				}
			}
			catch (Exception e) {}
		}
		
		if(this.Drop) location.getWorld().dropItemNaturally(location, new ItemStack(Material.getMaterial(this.Result, false)));
	}	
}