package commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ChestData.Chest;
import V3Chest.msg;

public class setChestCMD implements CommandExecutor{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if(cs instanceof Player){
		if(args.length == 1){
			Player p = (Player) cs;
			if(p.hasPermission("chest.setchest")){
				
				if(p.getTargetBlock(null, 0).getType().equals(Material.CHEST)){
					if(!Chest.chestExist(p.getTargetBlock(null, 0).getLocation())){
						Chest.addChest(args[0], p, p.getTargetBlock(null, 0).getLocation());
					}else{
						p.sendMessage(msg.chestExistAlready);
					}
				}else{
					p.sendMessage(msg.noTargetChest);
				}
				
			}else{
				cs.sendMessage(msg.noPermissin);
			}
		}else{
			cs.sendMessage(msg.wrongUseSetChest);
		}
		}else{
			cs.sendMessage("Only Player");
		}
		return false;
	}

}
