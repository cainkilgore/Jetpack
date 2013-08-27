package com.cainkilgore.jetpack;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class JetpackEvents implements Listener {

	@EventHandler
	public void onPlayerToggleSneak(final PlayerToggleSneakEvent e) {
		if(e.isSneaking()) {
			if(Check.isWearingJetpack(e.getPlayer())) {
				if(Check.isInWater(e.getPlayer())) return;
				
				if(Check.canUseJetpack(e.getPlayer(), "standard") || Check.canUseJetpack(e.getPlayer(), "advanced")) {
					Random r = new Random();
					int randomDamage = r.nextInt(3);
					
					ItemStack curChestplate = e.getPlayer().getInventory().getChestplate();
					ItemMeta ChestPlateName = curChestplate.getItemMeta();
					if(ChestPlateName.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Advanced Jetpack")) {
						randomDamage = r.nextInt(2);
					}
					short jetpackDurability = e.getPlayer().getInventory().getChestplate().getDurability();
					e.getPlayer().getInventory().getChestplate().setDurability((short) (jetpackDurability + randomDamage));
					if(ChestPlateName.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Advanced Jetpack")) {
						if(jetpackDurability > 527) {
							e.getPlayer().getInventory().setChestplate(null);
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1, 1);
						}
					} else {
						if(jetpackDurability > 111) {
							e.getPlayer().getInventory().setChestplate(null);
							e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ITEM_BREAK, 1, 1);
						}
					}
					Vector nearBlock = new Vector(e.getPlayer().getLocation().getDirection().getX() * getXSpeed(e.getPlayer()), getRecommendedVelocity(e.getPlayer()), e.getPlayer().getLocation().getDirection().getZ() * getZSpeed(e.getPlayer()));
					 e.getPlayer().setVelocity(nearBlock);
					
					final BukkitTask smoke = Bukkit.getServer().getScheduler().runTaskTimer(Jetpack.plugin, new Runnable() {
						public void run() {
							e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 1);
							e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
						}
					}, 2L, 2L);
					Bukkit.getServer().getScheduler().runTaskLater(Jetpack.plugin, new Runnable() {
						public void run() {
							smoke.cancel();
						}
					}, 35L);
				}
			}
		}
	}
	
	@EventHandler
	public void onCraftItem(CraftItemEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(!Check.canCraftJetpack(player, "standard")) {
	        if(e.getRecipe().getResult().equals(Jetpack.jetpack)) {
	        	e.setResult(Result.DENY);
	        	e.setCancelled(true);
			}
		}
		
		if(!Check.canRepairJetpack(player)) {
			if(e.getRecipe().getResult().equals(Jetpack.featherOfRegeneration)) {
				e.setResult(Result.DENY);
				e.setCancelled(true);
			}
		}
		
		if(!Check.canCraftBoots(player)) {
			if(e.getRecipe().getResult().equals(Jetpack.longFallBoots)) {
				e.setResult(Result.DENY);
				e.setCancelled(true);
			}
		}
		
		if(!Check.canCraftJetpack(player, "advanced")) {
			if(e.getRecipe().getResult().equals(Jetpack.advancedJetpack)) {
				e.setResult(Result.DENY);
				e.setCancelled(true);
			}
		}
		
//		if(!Check.canCraftSolarHelmet(player)) {
//			if(e.getRecipe().getResult().equals(Jetpack.solarHelmet)) {
//				e.setResult(Result.DENY);
//				e.setCancelled(true);
//			}
//		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		Action action = e.getAction();
		if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			
			if(player.getItemInHand().equals(Jetpack.featherOfRegeneration) || player.getItemInHand().equals(Jetpack.emeraldOfFlight)) {
				if(!Check.canRepairJetpack(player)) {
					player.sendMessage(ChatColor.RED + "You cannot regenerate your Jetpack.");
					return;
				}
				
				if(!Check.isWearingJetpack(player)) {
					player.sendMessage(ChatColor.RED + "You must be wearing your jetpack.");
					return;
				}
				
				int upDaDurability = 0;
				if(player.getItemInHand().equals(Jetpack.featherOfRegeneration)) {
					upDaDurability = 40;
				}
				if(player.getItemInHand().equals(Jetpack.emeraldOfFlight)) {
					upDaDurability = 250;
				}
				
				ItemStack chestPlate = player.getInventory().getChestplate();
				chestPlate.setDurability((short) (chestPlate.getDurability() - upDaDurability));
				player.updateInventory();
				
				Misc.removeItemInHand(player);
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
				player.sendMessage(ChatColor.GREEN + "Jetpack Repaired.");
			}
		}
	}
	
	// For teh boots
	@EventHandler
	public void onEntityDamage(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL) {
					if(Check.canUseBoots(player)) {
						if(Check.isWearingBoots(player)) {
							e.setCancelled(true);
							ItemStack boots = player.getInventory().getBoots();
							boots.setDurability((short) (boots.getDurability() - player.getVelocity().getY()));
						}
					}
			}
		}
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		boolean debug = false;
		if(!debug) return;
		double x = e.getPlayer().getVelocity().getX();
		double y = e.getPlayer().getVelocity().getY();
		double z = e.getPlayer().getVelocity().getZ();
		
		e.getPlayer().sendMessage("X: " + x + ", Y: " + y + ", Z: " + z);
		
	}
	
//	@EventHandler
//	public void onInventoryChange(InventoryClickEvent e) {
//		final Player player = (Player) e.getWhoClicked();
//		System.out.println("Inventory changed.");
//		if(!Check.isWearingHelmet(player)) return;
//		if(!Check.isWearingJetpack(player)) return;
//		if(!Check.canUseSolarHelmet(player)) return;
//		
//		System.out.println("Passed all checks, ticks SHOULD be running.");
//		final BukkitTask tickDurability = Bukkit.getServer().getScheduler().runTaskTimer(Jetpack.plugin, new Runnable() {
//			public void run() {
//				ItemStack helmet = player.getInventory().getHelmet();
//				helmet.setDurability((short) (helmet.getDurability() - 3));
//				System.out.println("Set durability.");
//				player.updateInventory();
//			}
//		}, 20L, 120L);
//		
//		final BukkitTask tickTester = Bukkit.getServer().getScheduler().runTaskTimer(Jetpack.plugin, new Runnable() {
//			public void run() {
//				if(!Check.isWearingJetpack(player)) { tickDurability.cancel(); System.out.println("Cancelled"); }
//				if(!Check.isWearingHelmet(player)) { tickDurability.cancel(); System.out.println("Cancelled"); }
//			}
//		}, 20L, 20L);
//		
//	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		String message = e.getReason();
		
		if(Check.canUseJetpack(e.getPlayer(), "standard") || Check.canUseJetpack(e.getPlayer(), "advanced")) {
			if(Check.isWearingJetpack(e.getPlayer())) {
				if(message.contains("Flying")) {
					e.setCancelled(true);
					e.setReason(null);
					e.setLeaveMessage(null);
				}
			}
		}
		
	}
	
	public double getRecommendedVelocity(Player player) {
		if(Check.wearingJetpack(player)) {
			if(player.getLocation().getY() > 250) {
				return -6;
			}
		}
		
		if(Check.wearingAdvancedJetpack(player)) {
			if(player.getLocation().getY() > 512) {
				return 0;
			}
		}
		return 0.8;
	}
	
	public double getXSpeed(Player player) {
		if(Check.wearingJetpack(player)) {
			return 1;
		}
		
		if(Check.wearingAdvancedJetpack(player)) {
			return 3;
		}
		return 0;
	}
	
	public double getZSpeed(Player player) {
		if(Check.wearingJetpack(player)) {
			return 1;
		}
		
		if(Check.wearingAdvancedJetpack(player)) {
			return 2;
		}
		return 0;
	}
	
	
}
