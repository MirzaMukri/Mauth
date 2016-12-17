package com.mukri.auth.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mukri.auth.Mauth;
import com.mukri.auth.files.PlayerData;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 2:43:01 AM 
 */

public class JoinEvents implements Listener {

	public Mauth plugin;
	
	public JoinEvents(Mauth plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PlayerData data = new PlayerData(p.getUniqueId().toString());
		
		if(plugin.settings.getConfig().getBoolean("Add-Blindness")) {
			p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 3));
		}
		
		if(plugin.settings.getConfig().getBoolean("Gamemode.Force-Survival")) {
			p.setGameMode(GameMode.SURVIVAL);
		}
		
		if(plugin.settings.getConfig().getBoolean("Gamemode.Clear-If-Creative")) {
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
		}
		
		if(plugin.settings.getConfig().getBoolean("Secure-Inventory")) {
						
			plugin.inventory.put(p, p.getInventory().getContents());
			plugin.armor.put(p, p.getInventory().getArmorContents());
			
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			
			//TODO Adding back items when logged in.
		}
		
		if(data.isExists()) {
			plugin.notLogged.add(p.getName());
			
			if(data.getPassword().equals("none")) {
				plugin.notRegistered.add(p.getName());
			}
			
		} else {
			data.createPlayer(p);
			plugin.notLogged.add(p.getName());
			p.sendMessage("//YOU HAVE TO LOG IN FIRST");
			
			plugin.notRegistered.add(p.getName());
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
				//TODO Kick players
				
			}
		}, 20*plugin.settings.getConfig().getInt("Login.Time-Login"));
	}
	
}
