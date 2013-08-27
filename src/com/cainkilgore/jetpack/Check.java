package com.cainkilgore.jetpack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class Check {
	
	public static boolean isWearingJetpack(Player player) {
		ItemStack playerCurrentChestplate = player.getInventory().getChestplate();
		if(playerCurrentChestplate == null) return false;
		ItemMeta curChestPlateMeta = playerCurrentChestplate.getItemMeta();
		if(curChestPlateMeta.getDisplayName() == null) return false;
		if(playerCurrentChestplate.getType() == Material.GOLD_CHESTPLATE) {
			if(curChestPlateMeta.getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Jetpack")) {
				return true;
			}
		}
		if(playerCurrentChestplate.getType() == Material.DIAMOND_CHESTPLATE) {
			if(curChestPlateMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Advanced Jetpack")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isInWater(Player player) {
		if(player.getLocation().getBlock().getRelative(0, 1, 0).getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
			return true;
		}
		return false;
	}
	
	public static boolean canUseJetpack(Player player, String type) {
		if(!Config.permissionsEnabled) return true;
		
		if(player.hasPermission("superjetpack." + type + ".use")) {
			return true;
		}
		return false;
	}
	
	public static boolean canRepairJetpack(Player player) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack.repair")) {
			return true;
		}
		return false;
	}
	
	public static boolean canCraftJetpack(Player player, String type) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack." + type + ".craft")) {
			return true;
		}
		return false;
	}
	
	public static boolean canCraftBoots(Player player) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack.boots.craft")) {
			return true;
		}
		return false;
	}
	
	public static boolean canUseBoots(Player player) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack.boots.use")) {
			return true;
		}
		return false;
	}
	
	public static boolean isWearingBoots(Player player) {
		ItemStack playerCurrentBoots = player.getInventory().getBoots();
		if(playerCurrentBoots == null) return false;
		ItemMeta curBootsMeta = playerCurrentBoots.getItemMeta();
		if(playerCurrentBoots.getType() == Material.DIAMOND_BOOTS) {
			if(curBootsMeta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Boots made for Falling")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean canCraftSolarHelmet(Player player) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack.solarhelmet.craft")) {
			return true;
		}
		return false;
	}
	
	public static boolean canUseSolarHelmet(Player player) {
		if(!Config.permissionsEnabled) return true;
		if(player.hasPermission("superjetpack.solarhelmet.use")) {
			return true;
		}
		return false;
	}
	
	public static boolean isWearingHelmet(Player player) {
		ItemStack playerCurrentHelmet = player.getInventory().getHelmet();
		if(playerCurrentHelmet == null) return false;
		ItemMeta curHelmetMeta = playerCurrentHelmet.getItemMeta();
		if(playerCurrentHelmet.getType() == Material.DIAMOND_HELMET) {
			if(curHelmetMeta.getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Solar Regeneration Unit")) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasPermission(Player player, String node) {
		if(!Config.permissionsEnabled) return true;
		
		if(player.hasPermission(node)) {
			return true;
		}
		return false;
	}
	
	public static void checkDataWearingJetpack(Player player) {
		ItemStack i = player.getInventory().getChestplate();
		MaterialData md = i.getData();
		
		if(i.getType() == Material.GOLD_CHESTPLATE && md.equals(12)) {
			System.out.println("Holy balls it worked.");
		}
		
		System.out.println(md);
		
	}
	
	public static boolean wearingJetpack(Player player) {
		ItemStack i = player.getInventory().getChestplate();
		ItemMeta meta = i.getItemMeta();
		if(meta.getDisplayName().equalsIgnoreCase(ChatColor.YELLOW + "Jetpack")) {
			return true;
		}
		return false;
	}
	
	public static boolean wearingAdvancedJetpack(Player player) {
		ItemStack i = player.getInventory().getChestplate();
		ItemMeta meta = i.getItemMeta();
		if(meta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Advanced Jetpack")) {
			return true;
		}
		return false;
	}
	
	
	
	

	

}
