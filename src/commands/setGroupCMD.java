package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ChestData.ChestGroup;
import V3Chest.msg;

public class setGroupCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command arg1, String arg2,
			String[] args) {
		// TODO Auto-generated method stub
		if (cs instanceof Player) {
			Player p = (Player) cs;
			if (args.length == 2) {
				int menge = 0;
				try {
					menge = Integer.valueOf(args[1]);
				} catch (Exception e) {
					return false;
				}
				if (p.hasPermission("chest.setgroup")) {
					setGroup(args[0], p, menge);
					p.sendMessage("Gruppe " + args[0] + " wurde erstellt!");
				} else {
					cs.sendMessage(msg.noPermissin);
				}
			} else {
				cs.sendMessage(msg.WrongUseSetGrouop);
			}
		} else {

		}
		return false;
	}

	public void setGroup(String gr, Player p, int menge) {
		if (!ChestGroup.grooupExist(gr)) {
			ChestGroup.createGroup(p, gr, menge);
		} else {
			p.sendMessage(msg.groupalreadyExist);
		}
	}
}
