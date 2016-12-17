package com.mukri.auth.commands;

import java.io.UnsupportedEncodingException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mukri.auth.Mauth;
import com.mukri.auth.files.PlayerData;
import com.mukri.auth.security.Base64Con;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 12:29:25 PM 
 */

public class LoginCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		PlayerData data = new PlayerData(p.getUniqueId().toString());
		
		if(cmd.getName().equalsIgnoreCase("login")) {
			if(args.length < 1) {
				p.sendMessage("//PLEASE DO LOGIN USING /LOGIN");
			}
			else if(args.length == 1) {
				try {
					String password = args[0];
					String storedPass = Base64Con.baseToString(data.getPassword());
					
					if(password.equals(storedPass)) {
						if(Mauth.getIns().notLogged.contains(p.getName())) {
							Mauth.getIns().notLogged.remove(p.getName());
						}
						
						p.sendMessage("//YOU ARE NOW LOGGED IN!");
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
			} else {
				p.sendMessage("///LOGIN [PASS]");
			}
		}
		
		return false;
	}

}
