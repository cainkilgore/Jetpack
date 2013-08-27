package com.cainkilgore.jetpack;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

public class Misc {
	
	public static ItemStack getJetpack(Player player) {
		return player.getInventory().getChestplate();
	}
	
	public static void setupJetpack() {
		ItemMeta jetpackMeta = Jetpack.jetpack.getItemMeta();
		Jetpack.jetpack.setData(new MaterialData(12));
		jetpackMeta.setDisplayName(ChatColor.YELLOW + "Jetpack");
		ArrayList<String> list = new ArrayList<String>();
		list.add(ChatColor.GOLD + "Take to the skys and fly away!");
		jetpackMeta.setLore(list);
		Jetpack.jetpack.setItemMeta(jetpackMeta);
		System.out.println("[Super Jetpack] Successfully registered Super Jetpack's properties.");
	}
	
	public static void setupFeather() {
		ItemMeta featherMeta = Jetpack.featherOfRegeneration.getItemMeta();
		featherMeta.setDisplayName(ChatColor.DARK_PURPLE + "Feather of Regeneration");
		Jetpack.featherOfRegeneration.setItemMeta(featherMeta);
		System.out.println("[Super Jetpack] Successfully registered Feather properties.");
	}
	
	public static void setupBoots() {
		ItemMeta bootsMeta = Jetpack.longFallBoots.getItemMeta();
		bootsMeta.setDisplayName(ChatColor.GREEN + "Boots made for Falling");
		ArrayList<String> list = new ArrayList<String>();
		list.add(ChatColor.DARK_AQUA + "Jump, jump, jump around!");
		bootsMeta.setLore(list);
		Jetpack.longFallBoots.setItemMeta(bootsMeta);
		System.out.println("[Super Jetpack] Successfully registered Long Fall Boot properties.");
	}
	
	public static void setupEmeraldOfFlight() {
		ItemMeta emeraldMeta = Jetpack.emeraldOfFlight.getItemMeta();
		emeraldMeta.setDisplayName(ChatColor.GREEN + "Emerald of Flight");
		ArrayList<String> list = new ArrayList<String>();
		list.add(ChatColor.DARK_AQUA + "Recharges 250 Jetpack Durability");
		emeraldMeta.setLore(list);
		Jetpack.emeraldOfFlight.setItemMeta(emeraldMeta);
		System.out.println("[Super Jetpack] Successfully registered Emerald of Flight properties.");
	}
	
	public static void setupAdvancedJetpack() {
		ItemMeta advancedMeta = Jetpack.advancedJetpack.getItemMeta();
		advancedMeta.setDisplayName(ChatColor.GOLD + "Advanced Jetpack");
		ArrayList<String> list = new ArrayList<String>();
		list.add(ChatColor.YELLOW + "Better, Stronger, Shiny-er");
		advancedMeta.setLore(list);
		Jetpack.advancedJetpack.setItemMeta(advancedMeta);
		System.out.println("[Super Jetpack] Successfully registered Advanced Jetpack properties.");
	}
	
//	public static void setupSolarHelmet() {
//		ItemMeta solarMeta = Jetpack.solarHelmet.getItemMeta();
//		solarMeta.setDisplayName(ChatColor.YELLOW + "Solar Regeneration Unit");
//		ArrayList<String> list = new ArrayList<String>();
//		list.add(ChatColor.GREEN + "Harness the power of the sun!");
//		solarMeta.setLore(list);
//		Jetpack.solarHelmet.setItemMeta(solarMeta);
//		System.out.println("[Super Jetpack] Successfully registered Solar Helmet properties.");
//	}
	
	
	public static void removeItemInHand(Player player) {
		if(player.getItemInHand().getAmount() == 1) {
			player.setItemInHand(null);
			return;
		}
		player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
	}
	

}
