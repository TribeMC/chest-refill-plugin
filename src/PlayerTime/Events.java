package PlayerTime;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

import ChestData.Chest;
import V3Chest.main;
import V3Chest.msg;

public class Events implements Listener{

	public Events(main main) {
		// TODO Auto-generated constructor stub
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	//InteractChest
	 @EventHandler
	 public void onInterAct(PlayerInteractEvent e){
		 if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			 if(e.getClickedBlock().getType().equals(Material.CHEST)){
				 if(ChestData.Chest.chestExist(e.getClickedBlock().getLocation())){
					 e.setCancelled(true);
					 if(time.canUseChest( e.getClickedBlock().getLocation(), e.getPlayer().getName().toLowerCase())){
					 Chest.openChest(e.getPlayer(), e.getClickedBlock().getLocation());
					 }else{
						 e.getPlayer().openInventory(Bukkit.createInventory(null, InventoryType.CHEST));
						 e.getPlayer().playSound(e.getClickedBlock().getLocation(), Sound.CHEST_OPEN, 1F, 1F);
						 e.getPlayer().sendMessage(msg.cantUseChestNow);
					 }
				 }else{
					 if(e.getPlayer().hasPermission("chest.notify")){
						 e.getPlayer().sendMessage(msg.notregistert);
					 }
				 }
			 }
		 }
	 }
}
