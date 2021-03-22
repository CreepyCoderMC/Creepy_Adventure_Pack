package CreepyCoder.AdventurePack.CustomEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.plugin.Plugin;

public class CustomLeavesDecayEvent implements Listener {

	public Plugin plugin;
	public FileConfiguration dataConfig;
	
	//private boolean Enable;
	private long BreakDelay;
	private long DecayDelay;
	private boolean OneByOne;
	private String Particles;
	private String PlaySound;
	//private String Group;
	//private boolean Permission;
	//private String AddedBy;
	//private String Version;
	
	private static final List<BlockFace> NEIGHBORS = Arrays
	        .asList(BlockFace.UP,
	                BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST,
	                BlockFace.DOWN);
	
	public List<String> KeyList = new ArrayList<String>();
	private List<Block> scheduledBlocks = new ArrayList<>();
	
	@SuppressWarnings({ "unchecked" })
	public CustomLeavesDecayEvent(Plugin plugin, FileConfiguration dataConfig) {

		this.plugin = plugin;
		this.dataConfig = dataConfig;
		this.KeyList = (List<String>) dataConfig.getList("CustomLeavesDecayEvent.key");

		for(Iterator<String> i = this.KeyList.iterator(); i.hasNext(); ) {
			String key = i.next();
			try {
				this.BreakDelay = dataConfig.getInt(key+".breakDelay");			
				this.DecayDelay = dataConfig.getInt(key+".decayDelay");
				this.OneByOne = dataConfig.getBoolean(key+".oneByOne");
				this.Particles = dataConfig.getString(key+".particles");
				this.PlaySound = dataConfig.getString(key+".playSound");
			}
			catch (Exception e) {
				Bukkit.getLogger().log(Level.WARNING, "Error with leaves decay event "+key);
			}
		}
		
	}
	
    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onLeavesDecay(LeavesDecayEvent event) {
        onBlockRemove(event.getBlock(), this.DecayDelay);
    }
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onBlockBreak(BlockBreakEvent event) {
		onBlockRemove(event.getBlock(), this.BreakDelay);
	}
	
    private void onBlockRemove(final Block oldBlock, long delay) {
        if (!Tag.LOGS.isTagged(oldBlock.getType())
            && !Tag.LEAVES.isTagged(oldBlock.getType())) {
            return;
        }
        Collections.shuffle(NEIGHBORS);
        for (BlockFace neighborFace: NEIGHBORS) {
            final Block block = oldBlock.getRelative(neighborFace);
            if (!Tag.LEAVES.isTagged(block.getType())) continue;
            Leaves leaves = (Leaves) block.getBlockData();
            if (leaves.isPersistent()) continue;
            if (scheduledBlocks.contains(block)) continue;
            if (this.OneByOne) {
                if (scheduledBlocks.isEmpty()) {
                    plugin.getServer().getScheduler().runTaskLater(plugin, this::decayOne, delay);
                }
                scheduledBlocks.add(block);
            } else {
                plugin.getServer().getScheduler().runTaskLater(plugin, () -> decay(block), delay);
            }
            scheduledBlocks.add(block);
        }
    }
    
    private boolean decay(Block block) {
        if (!scheduledBlocks.remove(block)) return false;
        if (!block.getWorld().isChunkLoaded(block.getX() >> 4, block.getZ() >> 4)) return false;
        if (!Tag.LEAVES.isTagged(block.getType())) return false;
        Leaves leaves = (Leaves) block.getBlockData();
        if (leaves.isPersistent()) return false;
        if (leaves.getDistance() < 7) return false;
        LeavesDecayEvent event = new LeavesDecayEvent(block);
        plugin.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) return false;
        if (!this.Particles.isEmpty()) {
            block.getWorld()
                .spawnParticle(Particle.valueOf(Particles),
                               block.getLocation().add(0.5, 0.5, 0.5),
                               8, 0.2, 0.2, 0.2, 0,
                               block.getType().createBlockData());
        }
        if (!this.PlaySound.isEmpty()) {
            block.getWorld().playSound(block.getLocation(),
                                       Sound.valueOf(PlaySound),
                                       SoundCategory.BLOCKS, 0.05f, 1.2f);
        }
        block.breakNaturally();
        return true;
    }

    private void decayOne() {
        boolean decayed = false;
        do {
            if (scheduledBlocks.isEmpty()) return;
            Block block = scheduledBlocks.get(0);
            decayed = decay(block);
        } while (!decayed);
        if (!scheduledBlocks.isEmpty()) {
            long delay = this.DecayDelay;
            if (delay <= 0) delay = 1L;
            plugin.getServer().getScheduler().runTaskLater(plugin, this::decayOne, delay);
        }
    }
}
