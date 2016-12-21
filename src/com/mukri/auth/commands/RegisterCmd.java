package com.mukri.auth.commands;

import java.io.UnsupportedEncodingException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mukri.auth.Mauth;
import com.mukri.auth.files.PlayerData;

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
		PlayerData data = new PlayerData(p.getUniqueId().toString());
		
		if(cmd.getName().equalsIgnoreCase("register")) {
			if(args.length < 1) {
				p.sendMessage(Mauth.getIns().settings.getConfig().getString("Message.Register").replace("&", "¤"));
			}
			
			else if(args.length == 1) {
				String password = args[0];
				if(!Mauth.getIns().notRegistered.contains(p.getName())) {
					p.sendMessage(Mauth.getIns().settings.getConfig().getString("Message.Login").replace("&", "¤"));
					return true;
				}
				if(password.length() < 5) {
					p.sendMessage(Mauth.getIns().settings.getConfig().getString("Message.Error-Invalid").replace("&", "¤"));
					return true;
				}
				if(password.equals("none")) {
					p.sendMessage(Mauth.getIns().settings.getConfig().getString("Message.Error-Length").replace("&", "¤"));
					return true;
				}
				p.sendMessage(Mauth.getIns().settings.getConfig().getString("Message.Successfull-Register").replace("&", "¤").replace("{PASSWORD}", password));
				try {
					data.setPassword(password);
					data.save();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				if(Mauth.getIns().notLogged.contains(p.getName())) {
					Mauth.getIns().notLogged.remove(p.getName());
				}
				if(Mauth.getIns().notRegistered.contains(p.getName())) {
					Mauth.getIns().notRegistered.remove(p.getName());
				}
			}
		}
		return false;
	}

}