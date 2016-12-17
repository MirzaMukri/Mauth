package com.mukri.auth;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mukri.auth.commands.LoginCmd;
import com.mukri.auth.commands.RegisterCmd;
import com.mukri.auth.listeners.BreakPlaceEvent;
import com.mukri.auth.listeners.JoinEvents;
import com.mukri.auth.listeners.MoveEvents;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 1:31:54 AM 
 */

public class Mauth extends JavaPlugin {
	
	public static Mauth instance;
	
	public List<String> notLogged = new ArrayList<>();
	public List<String> notRegistered = new ArrayList<>();
	
	public void onEnable() {
		instance = this;
		
		listen();
		commands();
		
		reminderMsg();
		
		saveConfig();
	}
	
	public void onDisable() {
		
	}
	
	public static Mauth getIns() {
		return instance;
	}

	public void commands() {
		getCommand("register").setExecutor(new RegisterCmd());
		getCommand("login").setExecutor(new LoginCmd());
	}
	
	public void listen() {
		getServer().getPluginManager().registerEvents(new MoveEvents(this), this);
		getServer().getPluginManager().registerEvents(new JoinEvents(this), this);
		getServer().getPluginManager().registerEvents(new BreakPlaceEvent(this), this);
	}
	
	public void reminderMsg() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					
					if(p != null) {
						if(notLogged.contains(p.getName())) {
							if(notRegistered.contains(p.getName())) {
								p.sendMessage("//REGISTER [PASS]");
							} else {
								p.sendMessage("//LOGIN [PASS]");
							}
						}
					}
					
				}
				
			}
		}, 0, 20*5);
	}
}
