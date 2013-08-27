package com.cainkilgore.jetpack;

public class Config {
	
	public static boolean allowUpdateMessages = false;
	public static boolean permissionsEnabled = false;
	
	
	public static void registerDefaults() {
		if(Jetpack.plugin.getConfig().get("settings.update-messages") == null) {
			Jetpack.plugin.getConfig().set("settings.update-messages", true);
			Jetpack.plugin.saveConfig();
		}
		
		if(Jetpack.plugin.getConfig().get("settings.use-permissions") == null) {
			Jetpack.plugin.getConfig().set("settings.use-permissions", true);
			Jetpack.plugin.saveConfig();
		}
		
		if(Jetpack.plugin.getConfig().getBoolean("settings.update-messages")) {
			allowUpdateMessages = true;
		}
		
		if(Jetpack.plugin.getConfig().getBoolean("settings.use-permissions")) {
			permissionsEnabled = true;
		}
	}

}
