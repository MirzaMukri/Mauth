package com.mukri.auth.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 8:42:10 PM 
 */

public class MauthCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("mauth")) {
			if(args.length < 1) {
				
			}
			
			else if(args[0].equalsIgnoreCase("reload")) {
				
			}
			
			else if(args[0].equalsIgnoreCase("")) {
				
			}
		}
		
		return false;
	}

}
