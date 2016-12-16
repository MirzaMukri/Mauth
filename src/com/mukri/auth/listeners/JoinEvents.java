package com.mukri.auth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

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
		
		if(data.isExists()) {
			//THIS WILL NOT WORK YET!
			if(data.getLastIp().equals(p.getAddress().toString())) {
				p.sendMessage("//YOU ARE ALREADY LOGGED IN (SAME IP W/PREVIOUS LOG IN)");
			} else {
				plugin.notLogged.add(p.getName());
				p.sendMessage("//YOU HAVE TO LOG IN FIRST");
			}
		} else {
			data.createPlayer(p);
			plugin.notLogged.add(p.getName());
			p.sendMessage("//YOU HAVE TO LOG IN FIRST");
		}
	}
	
}
