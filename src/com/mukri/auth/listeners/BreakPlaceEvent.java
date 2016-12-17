package com.mukri.auth.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mukri.auth.Mauth;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 3:46:00 PM 
 */

public class BreakPlaceEvent implements Listener {
	
	public Mauth plugin;
	
	public BreakPlaceEvent(Mauth plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.notLogged.contains(p.getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(plugin.notLogged.contains(p.getName())) {
			e.setCancelled(true);
		}
	}

}
