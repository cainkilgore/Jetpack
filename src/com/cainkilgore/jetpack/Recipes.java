package com.cainkilgore.jetpack;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;

public class Recipes {

	public static void registerJetpackRecipe() {
		final ShapedRecipe jetpackRecipe = new ShapedRecipe(Jetpack.jetpack);
        jetpackRecipe.shape("FDF", "DJD", "FDF");
        jetpackRecipe.setIngredient('D', Material.DIAMOND);
        jetpackRecipe.setIngredient('J', Material.GOLD_CHESTPLATE);
        jetpackRecipe.setIngredient('F', Material.FEATHER);
        Bukkit.getServer().addRecipe(jetpackRecipe);
        System.out.println("[Super Jetpack] Successfully registered Super Jetpack's recipe.");
	}
	
	public static void registerFeatherRecipe() {
		final ShapedRecipe featherRecipe = new ShapedRecipe(Jetpack.featherOfRegeneration);
		featherRecipe.shape("GGG", "GFG", "GGG");
		featherRecipe.setIngredient('F', Material.FEATHER);
		featherRecipe.setIngredient('G', Material.GOLD_NUGGET);
		Bukkit.getServer().addRecipe(featherRecipe);
	}
	
	public static void registerLongFallBootsRecipe() {
		final ShapedRecipe bootsRecipe = new ShapedRecipe(Jetpack.longFallBoots);
		bootsRecipe.shape("FEF", "DBD", "FDF");
		bootsRecipe.setIngredient('F', Material.FEATHER);
		bootsRecipe.setIngredient('E', Material.EMERALD);
		bootsRecipe.setIngredient('D', Material.DIAMOND);
		bootsRecipe.setIngredient('B', Material.DIAMOND_BOOTS);
		Bukkit.getServer().addRecipe(bootsRecipe);
	}
	
	public static void registerEmeraldOfFlightRecipe() {
		final ShapedRecipe emeraldRecipe = new ShapedRecipe(Jetpack.emeraldOfFlight);
		emeraldRecipe.shape("FGF", "LEL", "FGF");
		emeraldRecipe.setIngredient('F', Material.FEATHER);
		emeraldRecipe.setIngredient('G', Material.GOLD_BLOCK);
		emeraldRecipe.setIngredient('L', Material.LAPIS_BLOCK);
		emeraldRecipe.setIngredient('E', Material.EMERALD);
		Bukkit.getServer().addRecipe(emeraldRecipe);
	}
	
	public static void registerAdvancedJetpackRecipe() {
		final ShapedRecipe advJetpackRecipe = new ShapedRecipe(Jetpack.advancedJetpack);
		advJetpackRecipe.shape("DED", "BCB", "FNF");
		advJetpackRecipe.setIngredient('D', Material.DAYLIGHT_DETECTOR);
		advJetpackRecipe.setIngredient('E', Material.EMERALD);
		advJetpackRecipe.setIngredient('B', Material.DIAMOND_BLOCK);
		advJetpackRecipe.setIngredient('C', Material.DIAMOND_CHESTPLATE);
		advJetpackRecipe.setIngredient('F', Material.FIREWORK);
		advJetpackRecipe.setIngredient('N', Material.NETHER_STAR);
		
		Bukkit.getServer().addRecipe(advJetpackRecipe);
	}
	
//	public static void registerSolarHelmetRecipe() {
//		final ShapedRecipe solarRecipe = new ShapedRecipe(Jetpack.solarHelmet);
//		solarRecipe.shape("SSS", "DCD", "ENE");
//		solarRecipe.setIngredient('S', Material.DAYLIGHT_DETECTOR);
//		solarRecipe.setIngredient('D', Material.DIAMOND_BLOCK);
//		solarRecipe.setIngredient('C', Material.CHAINMAIL_HELMET);
//		solarRecipe.setIngredient('E', Material.EMERALD_BLOCK);
//		solarRecipe.setIngredient('N', Material.NETHER_STAR);
//		Bukkit.getServer().addRecipe(solarRecipe);
//	}
	
}
