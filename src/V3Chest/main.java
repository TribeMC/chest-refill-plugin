package V3Chest;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import PlayerTime.Events;

import commands.setChestCMD;
import commands.setGroupCMD;

public class main extends JavaPlugin{

	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		registerEvents();
		registerCommands();
		loadMSG();
		
		File f = new File("Chest/Chests");
		if(!f.exists()){
			f.mkdir();
		}
		
		File f1 = new File("Chest/Groups");
		if(!f1.exists()){
			f1.mkdir();
		}
		super.onEnable();
	}

	private void loadMSG() {
		// TODO Auto-generated method stub
		msg.loadMSG();
	}

	private void registerCommands() {
		// TODO Auto-generated method stub
		getCommand("setchest").setExecutor(new setChestCMD());
		getCommand("setgroup").setExecutor(new setGroupCMD());
	}

	private void registerEvents() {
		// TODO Auto-generated method stub
		new Events(this);
	}
}
