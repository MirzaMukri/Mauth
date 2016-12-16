package com.mukri.auth;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.mukri.auth.commands.RegisterCmd;
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
	
	public void onEnable() {
		instance = this;
		
		listen();
		commands();
		
		saveConfig();
	}
	
	public void onDisable() {
		
	}
	
	public static Mauth getIns() {
		return instance;
	}

	public void commands() {
		getCommand("register").setExecutor(new RegisterCmd());
	}
	
	public void listen() {
		getServer().getPluginManager().registerEvents(new MoveEvents(this), this);
		getServer().getPluginManager().registerEvents(new JoinEvents(this), this);
	}
}
