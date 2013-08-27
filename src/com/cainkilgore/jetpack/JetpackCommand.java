package com.cainkilgore.jetpack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

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
												 "featherOfRegeneration", "bootsOfFalling" };
					
					StringBuilder x = new StringBuilder();
					for(String i : availableItems) {
						x.append(i + ", ");
					}
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] Items you can spawn: ");
					player.sendMessage(ChatColor.GOLD + "[Super Jetpack] " + x.toString().trim());
					return false;
				}
			}
			
		}
		return true;
	}

}
