package com.cainkilgore.jetpack;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateListener implements Listener {
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if(!Jetpack.updateAvailable) return;
		if(!e.getPlayer().isOp()) return;
		
		e.getPlayer().sendMessage(ChatColor.GOLD + "[Super Jetpack] There's an update available.");
		e.getPlayer().sendMessage(ChatColor.GOLD + "[] http://dev.bukkit.org/bukkit-plugins/Super-Jetpack");
	}

}
