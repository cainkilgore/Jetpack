package com.cainkilgore.jetpack;

import java.io.IOException;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.ScoreboardManager;

public class Jetpack extends JavaPlugin {
	
	public static boolean updateAvailable = false;
	public static HashSet<String> playersDeniedParticles = new HashSet<String>();
	public static HashSet<String> playersDeniedScoreboard = new HashSet<String>();
	
	
	static ItemStack jetpack = new ItemStack(Material.GOLD_CHESTPLATE, 1);
	static ItemStack featherOfRegeneration = new ItemStack(Material.FEATHER, 1);
	static ItemStack longFallBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
	static ItemStack solarHelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
	static ItemStack emeraldOfFlight = new ItemStack(Material.EMERALD, 1);
	static ItemStack advancedJetpack = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
	
	// Update Section
	// v1.7
	// Made the Advanced Jetpack fly 2x faster
	// Gave boots durability, depends on how far they fall.
	
	// v1.8
	// Added the Solar Regeneration Unit
	// Added a sound to the jetpacks
	
	
	public static Jetpack plugin;
	
	public void onEnable() {
		plugin = this;
		getServer().getLogger().setFilter(new KickMessageFilter());
		Misc.setupJetpack();
		Misc.setupFeather();
		Misc.setupBoots();
		Misc.setupSolarHelmet();
		Misc.setupEmeraldOfFlight();
		Misc.setupAdvancedJetpack();
		Recipes.registerJetpackRecipe();
		Recipes.registerFeatherRecipe();
		Recipes.registerLongFallBootsRecipe();
		Recipes.registerSolarHelmetRecipe();
		Recipes.registerEmeraldOfFlightRecipe();
		Recipes.registerAdvancedJetpackRecipe();
		beginScoreboardTicking();
		beginSolarTicking();
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
	
	public void beginSolarTicking() {
		Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {
				for(Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
					if(Check.isWearingHelmet(onlinePlayers) && Check.isWearingJetpack(onlinePlayers) && Check.canUseSolarHelmet(onlinePlayers)) {
						if(Check.canUseJetpack(onlinePlayers, "standard") || Check.canUseJetpack(onlinePlayers, "advanced")) {
							if(!shouldSolarWork(onlinePlayers)) return;
							onlinePlayers.getInventory().getChestplate().setDurability((short) (onlinePlayers.getInventory().getChestplate().getDurability() - 2));
						}
					}
				}
			}
		}, 40L, 40L);
	}
	
	public void beginScoreboardTicking() {
		Bukkit.getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {
				for(Player onlinePlayers : Bukkit.getServer().getOnlinePlayers()) {
					ScoreboardManager m = Bukkit.getScoreboardManager();
					if(playersDeniedScoreboard.contains(onlinePlayers.getName())) {
						onlinePlayers.setScoreboard(m.getNewScoreboard());
					}
					if(Check.isWearingJetpack(onlinePlayers)) return;
					//System.out.println("Not wearing jetpack, remove scoreboard.");
					onlinePlayers.setScoreboard(m.getNewScoreboard());
				}
			}
		}, 20L, 20L);
	}
	
	public boolean shouldSolarWork(Player player) {
		long time = player.getWorld().getTime();
		
		if(time > 0 && time < 12200) return true;
		return false;
	}

}
