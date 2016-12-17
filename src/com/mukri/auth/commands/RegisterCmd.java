package com.mukri.auth.commands;

import java.io.UnsupportedEncodingException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mukri.auth.Mauth;
import com.mukri.auth.files.PlayerData;
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
		PlayerData data = new PlayerData(p.getUniqueId().toString());
		
		if(cmd.getName().equalsIgnoreCase("register")) {
			if(args.length < 1) {
				p.sendMessage("IP: " + p.getAddress());
			}
			
			else if(args.length == 1) {
				String password = args[0];
				if(!Mauth.getIns().notRegistered.contains(p.getName())) {
					p.sendMessage("///LOGIN [PASS]");
					return true;
				}
				if(password.length() < 5) {
					p.sendMessage("//YOUR PASSWORD MUST BE HIGHER THAN 5");
					return true;
				}
				if(password.equals("none")) {
					p.sendMessage("//YOU ARE NOT ALLOWED TO USE THAT PASSWORD");
					return true;
				}
				p.sendMessage("//YOU SET YOUR PASSWORD TO " + password);
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
			
			else if(args[0].equalsIgnoreCase("force")) {
				Mauth.getIns().notLogged.remove(p.getName());
				p.sendMessage("//YOU ARE NOW LOGGED IN");
			}
		}
		return false;
	}

}