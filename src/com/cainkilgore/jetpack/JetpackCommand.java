package com.cainkilgore.jetpack;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class JetpackCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("jetpack")) {
			if(!(s instanceof Player)) return false;
			Player player = (Player) s;
			
			if(args.length < 1) return false;
			
			if(args[0].equalsIgnoreCase("give")) {
				if(args.length < 2) return false;
				if(args[1].equalsIgnoreCase("featherOfRegeneration")) {
					if(Check.hasPermission(player, "superjetpack.give.featherofregeneration")) {
						player.getInventory().addItem(Jetpack.featherOfRegeneration);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Feather of Regeneration to your Inventory");
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("jetpack")) {
					if(Check.hasPermission(player, "superjetpack.give.jetpack")) {
						player.getInventory().addItem(Jetpack.jetpack);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Standard Jetpack to your Inventory");
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("advancedJetpack")) {
					if(Check.hasPermission(player, "superjetpack.give.advancedjetpack")) {
						player.getInventory().addItem(Jetpack.advancedJetpack);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Advanced Jetpack to your Inventory");
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("bootsOfFalling")) {
					if(Check.hasPermission(player, "superjetpack.give.bootsoffalling")) {
						player.getInventory().addItem(Jetpack.longFallBoots);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Boots of Falling to your Inventory");
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("emeraldOfFlight")) {
					if(Check.hasPermission(player, "superjetpack.give.emeraldofflight")) {
						player.getInventory().addItem(Jetpack.emeraldOfFlight);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Emerald of Flight to your Inventory");
					}
					return true;
				}
				
				if(args[1].equalsIgnoreCase("solarRegenerationUnit")) {
					if(Check.hasPermission(player, "superjetpack.give.solarregenerationunit")) {
						player.getInventory().addItem(Jetpack.solarHelmet);
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Added Solar Regeneration Unit to your Inventory");
					}
					return true;
				}
				
				
				player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Invalid item specified.");
			}
			
			if(args[0].equalsIgnoreCase("checkforupdates")) {
				if(Check.hasPermission(player, "superjetpack.checkforupdates")) {
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Checking for updates..");
					Updater.checkForUpdates(true, player);
				}
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				if(Check.hasPermission(player, "superjetpack.list")) {
					String [] availableItems = { "emeraldOfFlight", "jetpack", "advancedJetpack",
												 "featherOfRegeneration", "bootsOfFalling", "solarRegenerationUnit", };
					
					StringBuilder x = new StringBuilder();
					for(String i : availableItems) {
						x.append(i + ", ");
					}
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Items you can spawn: ");
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] " + x.toString().trim());
					return false;
				}
			}
			
			if(args[0].equalsIgnoreCase("toggleparticles")) {
				if(Check.hasPermission(player, "superjetpack.toggleparticles")) {
					if(!Jetpack.playersDeniedParticles.contains(player.getName())) {
						Jetpack.playersDeniedParticles.add(player.getName());
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] You will no longer see flying particles.");
						return true;
					}
					Jetpack.playersDeniedParticles.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] You will now see flying particles.");
				}
				return true;
			}
			
			if(args[0].equalsIgnoreCase("togglescoreboard")) {
				if(Check.hasPermission(player, "superjetpack.togglescoreboard")) {
					if(!Jetpack.playersDeniedScoreboard.contains(player.getName())) {
						Jetpack.playersDeniedScoreboard.add(player.getName());
						player.sendMessage(ChatColor.GOLD + "[Super Jetpack] You will no longer see the scoreboard.");
						return true;
					}
					Jetpack.playersDeniedScoreboard.remove(player.getName());
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] You will now see the scoreboard.");
				}
				return true;
			}
			
		}
		return true;
	}

}
