package com.mukri.auth.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.mukri.auth.Mauth;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 2:14:32 AM 
 */

public class MoveEvents implements Listener {
	
	public Mauth plugin;
	
	public MoveEvents(Mauth plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onMoveNotLogged(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(plugin.notLogged.contains(p.getName())) {
			if((e.getFrom().getX() != e.getTo().getX()) || (e.getFrom().getY() != e.getTo().getY()) || (e.getFrom().getZ() != e.getTo().getZ())) {
				Location loc = e.getFrom();
				loc.setPitch(e.getTo().getPitch());
				loc.setYaw(e.getTo().getYaw());
				p.teleport(loc);
			}
		}
	}

}
