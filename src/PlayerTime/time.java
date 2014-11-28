package PlayerTime;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

public class time {

	
	static int timeNoUse = 30*60*1000;
	//set<Chest, Player>
	public static void addOpenChest(Location loc, String name){
		String fn = loc.getWorld().getName() + "#" +  loc.getBlockX() + "#" +loc.getBlockZ();
		File f = new File("Chest/Chests", fn + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		long l = System.currentTimeMillis();
		
		cfg.set("User." + name, l);
		
		try {
			cfg.save(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//rdy for next use<Chest, Player>
	public static boolean canUseChest(Location loc, String name){
		String fn = loc.getWorld().getName() + "#" +  loc.getBlockX() + "#" +loc.getBlockZ();
		File f = new File("Chest/Chests", fn + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
		
		if(cfg.contains("User." + name.toLowerCase())){
		long l = cfg.getLong("User." + name.toLowerCase());
		if(l + timeNoUse <= System.currentTimeMillis()){
			
			
			
			return true;
		}else{
			return false;
		}
		}else{
			return true;
		}
		
	}
	

	

	
	
}
