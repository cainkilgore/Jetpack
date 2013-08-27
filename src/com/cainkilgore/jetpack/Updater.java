package com.cainkilgore.jetpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class Updater {
	
	static boolean startUpdates = false;
	static boolean naggingStarted = false;

	public static int getCurrentVersion() {
		return Integer.parseInt(Jetpack.plugin.getDescription().getVersion().replace(".", ""));
	}
	
	public static void checkForUpdates(final boolean ranbyPlayer, final Player player) {

		
		Bukkit.getServer().getScheduler().runTaskAsynchronously(Jetpack.plugin, new Runnable() {
			public void run() {
				try {
					if(getNewestVersion() > getCurrentVersion()) {
						Jetpack.updateAvailable = true;
						System.out.println("[Super Jetpack] There is an update available.");
						startUpdates = true;
						
						if(ranbyPlayer) {
							player.sendMessage(ChatColor.GOLD + "[Super Jetpack] There is an update available.");
							player.sendMessage(ChatColor.GOLD + "[] http://dev.bukkit.org/bukkit-plugins/Super-Jetpack");
						}
						return;
					}
					System.out.println("[Super Jetpack] No updates available.");
					if(ranbyPlayer) {
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] There are no updates available.");
					}
				} catch (IOException e) {
					System.out.println("[Super Jetpack] Wasn't able to check for updates. Uh oh.");
					if(ranbyPlayer) {
						player.sendMessage(ChatColor.GOLD + "I wasen't able to check for updates.");
					}
				}
			}
		});
		
		if(startUpdates == true) {
			if(!naggingStarted) startNagging();
		}
		// startNagging();
	}
	
	public static int getNewestVersion() throws IOException {
		String curVer = "00";
		URL url = new URL("http://cain.im/dl/currentVersion.txt");
		URLConnection connect = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		String inputLine;
		while((inputLine = in.readLine()) !=null) {
			// inputLine = in.readLine();
			curVer = inputLine;
		}
		in.close();
		return Integer.parseInt(curVer);
	}
	
	public static void startNagging() {
		naggingStarted = true;
		Bukkit.getServer().getScheduler().runTaskTimer(Jetpack.plugin, new Runnable() {
			public void run() {
				if(!Jetpack.updateAvailable) return;
				for(Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
					if(onlinePlayers.isOp()) {
						onlinePlayers.sendMessage(ChatColor.GOLD + "[Super Jetpack] There's an update available.");
						onlinePlayers.sendMessage(ChatColor.GOLD + "[] http://dev.bukkit.org/bukkit-plugins/Super-Jetpack");
					}
				}
			}
		}, 20L, ((20 * 60) * 60) * 3);
	}
}
