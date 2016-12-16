package com.mukri.auth.commands;

import java.io.UnsupportedEncodingException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mukri.auth.security.Base64Converter;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 1:57:14 AM 
 */

public class RegisterCmd implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("register")) {
			if(args.length < 1) {
				p.sendMessage("IP: " + p.getAddress());
			}
			
			else if(args[0].equalsIgnoreCase("test")) {
				try {
					String base = Base64Converter.stringToBase("Doom");
					p.sendMessage("Base: " + base);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			
			else if(args[0].equalsIgnoreCase("testo")) {
				try {
					String base = Base64Converter.baseToString("RG9vbQ==");
					
					p.sendMessage("NotBase: " + base);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

}
