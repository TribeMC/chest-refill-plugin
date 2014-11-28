package ChestData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import PlayerTime.time;
import V3Chest.msg;

public class Chest implements Listener {

	// Open Chest #
	@SuppressWarnings("deprecation")
	public static void openChest(Player p, Location loc) {
		p.updateInventory();
		p.openInventory(create(loc));
		p.playSound(loc, Sound.CHEST_OPEN, 1F, 1F);

		time.addOpenChest(loc, p.getName().toLowerCase());

	}

	// Create ChestInv #
	public static Inventory create(Location loc) {
		Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);

		// FileName erstellen:
		String fn = loc.getWorld().getName() + "#" + loc.getBlockX() + "#"
				+ loc.getBlockZ();
		File f = new File("Chest/Chests", fn + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		String gr = cfg.getString("group");
		List<ItemStack> items = ChestGroup.getItems(gr);
		Random r = new Random();
		int menge = ChestGroup.getItemMenge(gr);
		for (int i = 0; i < menge; i++) {
			int pos = r.nextInt(27);
			int it = r.nextInt(items.size());
			inv.setItem(pos, items.get(it));
			items.remove(it);
			// Setzt Items im Inv / Maximale Items sind die menge
		}

		return inv;

	}

	// addChest <Group> #
	public static void addChest(String gr, Player p, Location loc) {
		String fn = loc.getWorld().getName() + "#" + loc.getBlockX() + "#"
				+ loc.getBlockZ();

		File f = new File("Chest/Chests", fn + ".yml");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		cfg.set("group", gr.toLowerCase());
		cfg.set("creator", p.getName());

		try {
			cfg.save(f);
			p.sendMessage(msg.chestSetSuzess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean chestExist(Location loc) {
		String fn = loc.getWorld().getName() + "#" + loc.getBlockX() + "#"
				+ loc.getBlockZ();
		File f = new File("Chest/Chests", fn + ".yml");
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

}
