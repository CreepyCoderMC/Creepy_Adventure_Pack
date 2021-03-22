package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import CreepyCoder.AdventurePack.Function.BlockFunction;
import CreepyCoder.AdventurePack.Function.DispenserFunction;
import CreepyCoder.AdventurePack.Function.IngredientFunction;

public class CustomDispenserEvent implements Listener {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	private BlockFunction BlockFunction = new BlockFunction();

	//private boolean Enable;
	private String Source;
	private String Result;
	private ItemStack ItemUsed;
	private boolean ItemReduce;
	private String Particle;
	private boolean Break;
	private boolean Drop;
	private boolean AddInventory;
	private boolean Replace;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;

	public List<String> KeyList = new ArrayList<String>();
	private IngredientFunction IngredientFunction = new IngredientFunction();

	@SuppressWarnings({ "unchecked" })
	public CustomDispenserEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomDispenserEvent.key");

	}

	@EventHandler
	public void onDispence(BlockDispenseEvent event) {
		
		Block block = BlockFunction.InFrontOfBlock(event.getBlock());
		
		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.Result = dataConfig.getString(key+".result");
				this.Source = dataConfig.getString(key+".source");			
				this.ItemUsed = new ItemStack(Material.valueOf(dataConfig.getString(key+".itemUsed")));
				this.ItemReduce = dataConfig.getBoolean(key+".itemReduce");
				this.Particle = dataConfig.getString(key+".particle");
				this.Break = dataConfig.getBoolean(key+".break");
				this.Drop = dataConfig.getBoolean(key+".drop");
				this.AddInventory = dataConfig.getBoolean(key+".addInventory");
				this.Replace = dataConfig.getBoolean(key+".replace");
				
				for(@SuppressWarnings("unchecked")
				ListIterator<Material> newSource = IngredientFunction.IngredientToMaterialList(Source).listIterator(); newSource.hasNext(); ) {
					String tmpSource = newSource.next().toString();
					if(block.getType().toString().equals(tmpSource) && event.getItem().getType().toString().equals(ItemUsed.getType().toString())) {
						event.setCancelled(true);
						BlockFunction.Interact(block, Result, Drop, AddInventory, Replace, Break, Particle);
						String tmpItemUsed = ItemUsed.getType().toString();
						if(ItemReduce) {
							new BukkitRunnable() {
								@Override
								public void run() {
									DispenserFunction DispenserFunction = new DispenserFunction();
									DispenserFunction.ReduceDispenserItem(event, tmpItemUsed);	
								}
							}.runTaskLater(plugin, 1L);
						}
					}
				}
			}
			catch (Exception e) {
				Bukkit.getLogger().log(Level.WARNING, "Error with dispenser event "+key);
			}
		}
	}
}