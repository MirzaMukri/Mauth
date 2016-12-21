package com.mukri.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.mukri.auth.commands.LoginCmd;
import com.mukri.auth.commands.RegisterCmd;
import com.mukri.auth.files.SettingsData;
import com.mukri.auth.listeners.BreakPlaceEvent;
import com.mukri.auth.listeners.JoinEvents;
import com.mukri.auth.listeners.MoveEvents;
import com.mukri.auth.mysql.MySql;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 1:31:54 AM 
 */

public class Mauth extends JavaPlugin {
	
	public static Mauth instance;
	
	public List<String> notLogged = new ArrayList<>();
	public List<String> notRegistered = new ArrayList<>();
	public Map<Player, ItemStack[]> inventory = new HashMap<>();
	public Map<Player, ItemStack[]> armor = new HashMap<>();
	
	public SettingsData settings = new SettingsData();
	public MySql sql;

	
	public void onEnable() {
		instance = this;
		
		listen();
		commands();
		
		reminderMsg();
				
		if(!settings.isExists()) {
			settings.addDefaullt();
		}
		
		sql = new MySql(settings.getMysqlIp(), settings.getMysqlPort(), settings.getMysqlDatabase(), settings.getMysqlUser(), settings.getMysqlPassword());
		//TODO Add sql.createTable();
		
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
								p.sendMessage(settings.getConfig().getString("Message.Register").replace("&", "¤"));
							} else {
								p.sendMessage(settings.getConfig().getString("Message.Login").replace("&", "¤"));
							}
						}
					}
					
				}
				
			}
		}, 0, 20*5);
	}
}
