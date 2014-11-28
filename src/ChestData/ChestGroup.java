package ChestData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ChestGroup {

	// group exist
	public static boolean grooupExist(String gr) {
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		if (f.exists()) {
			return true;
		} else {
			return false;
		}
	}

	// setgroup <name> <Menge>

	public static void createGroup(Player p, String gr, int menge) {
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setInventorry(p.getInventory(), gr);
		setItemMenge(gr, menge);
	}

	// addInv 9 18 27 36 ?

	@SuppressWarnings("deprecation")
	public static void setInventorry(Inventory inv, String gr) {
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		List<String> items = new ArrayList<>();

		for (int i = 0; i < 35; i++) {

			if (inv.getItem(i) != null) {
				int type = inv.getItem(i).getTypeId();
				int dat = inv.getItem(i).getData().getData();
				int menge = inv.getItem(i).getAmount();
				String name = inv.getItem(i).getItemMeta().getDisplayName();
				List<String> Lore = inv.getItem(i).getItemMeta().getLore();
				String lor = "-";
				if (Lore != null) {
					for (String s : Lore) {
						lor = lor + s + "==";
					}
				}
				items.add(type + ", " + dat + ", " + menge + ", " + name + ", "
						+ lor);

			}
		}

		cfg.set("Items", items);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// set item/chest

	public static void setItemMenge(String gr, int menge) {
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		cfg.set("Menge", menge);

		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// get items
	public static List<ItemStack> getItems(String gr) {
		List<ItemStack> is = new ArrayList<>();
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		List<String> items = cfg.getStringList("Items");

		for (int i = 0; i < items.size(); i++) {
			int type = 0;
			int dat = 0;
			int menge = 0;
			String name = "";
			String lor = "";
			String it = items.get(i);
			String[] its = it.split(", ");

			type = Integer.valueOf(its[0]);
			dat = Integer.valueOf(its[1]);
			menge = Integer.valueOf(its[2]);
			name = its[3];

			@SuppressWarnings("deprecation")
			ItemStack item = new ItemStack(Material.getMaterial(type), menge,
					(short) dat);
			ItemMeta im = item.getItemMeta();

			if (!name.equals("null")) {
				im.setDisplayName(name);
			}
			if (!its[4].equalsIgnoreCase("-")) {
				lor = its[4];
				lor = lor.replaceFirst("-", "");
				String[] Lores = lor.split("==");
				List<String> Lore = new ArrayList<>();
				for (String s : Lores) {
					Lore.add(s);
				}
				im.setLore(Lore);
			}

			item.setItemMeta(im);

			is.add(item);
			// fügt items aus der Config hinzu
		}

		return is;
	}

	// get item/chest
	public static int getItemMenge(String gr) {
		int menge = 0;
		File f = new File("Chest/Groups", gr.toLowerCase() + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

		menge = cfg.getInt("Menge");

		return menge;

	}

	// remove group

	// target chest <Player>

	public void addItem(Player p, ItemStack item) {
		if (p.getInventory().firstEmpty() == -1) {
			p.getLocation().getWorld().dropItemNaturally(p.getLocation(), item);
		} else {
			p.getInventory().addItem(item);
		}
	}

	public ItemStack createItem(int itemID, int damage, int menge, String name,
			String lore) {
		@SuppressWarnings("deprecation")
		ItemStack item = new ItemStack(Material.getMaterial(itemID), menge,
				(short) damage);
		ItemMeta meta = item.getItemMeta();
		if (name != null) {
			meta.setDisplayName(name);
		}
		if (lore != null) {
			ArrayList<String> l = new ArrayList<>();
			String[] lores = lore.split("/n");
			for (String lor : lores) {
				l.add(lor);
			}
			meta.setLore(l);
		}

		item.setItemMeta(meta);
		return item;
	}
}
