package com.cainkilgore.jetpack;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Jetpack extends JavaPlugin {
	
	public static boolean updateAvailable = false;
	
	static ItemStack jetpack = new ItemStack(Material.GOLD_CHESTPLATE, 1);
	static ItemStack featherOfRegeneration = new ItemStack(Material.FEATHER, 1);
	static ItemStack longFallBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
//	static ItemStack solarHelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
	static ItemStack emeraldOfFlight = new ItemStack(Material.EMERALD, 1);
	static ItemStack advancedJetpack = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
	
	public static Jetpack plugin;
	
	public void onEnable() {
		plugin = this;
		getServer().getLogger().setFilter(new KickMessageFilter());
		Misc.setupJetpack();
		Misc.setupFeather();
		Misc.setupBoots();
//		Misc.setupSolarHelmet();
		Misc.setupEmeraldOfFlight();
		Misc.setupAdvancedJetpack();
		Recipes.registerJetpackRecipe();
		Recipes.registerFeatherRecipe();
		Recipes.registerLongFallBootsRecipe();
//		Recipes.registerSolarHelmetRecipe();
		Recipes.registerEmeraldOfFlightRecipe();
		Recipes.registerAdvancedJetpackRecipe();
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		    System.out.println("[Super Jetpack] Metrics started.");
		} catch (IOException e) {
		    System.out.println("[Super Jetpack] Jetpack wasn't able to connect to MCStats.org. :(");
		}
		
		System.out.println("[Super Jetpack] Super Jetpack is now enabled.");
		Config.registerDefaults();
		if(Config.allowUpdateMessages) Updater.checkForUpdates(false, null);
		this.getServer().getPluginManager().registerEvents(new JetpackEvents(), this);
		this.getServer().getPluginManager().registerEvents(new UpdateListener(), this);
		this.getServer().getPluginCommand("jetpack").setExecutor(new JetpackCommand());
		
	}
	
	public void onDisable() {
		System.out.println("[Super Jetpack] Super Jetpack is now disabled.");
	}

}
