package com.cainkilgore.jetpack;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class ScoreboardInit {
	
	public static void startScoreboard(Player player, int durability) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		Objective o = board.registerNewObjective("test", "dummy");
		o.setDisplaySlot(DisplaySlot.SIDEBAR);
		o.setDisplayName(ChatColor.GOLD + "Jetpack Durability");
		
		Score score = o.getScore(Bukkit.getServer().getOfflinePlayer(ChatColor.GREEN + "Durability: "));
		score.setScore(durability);
		
		player.setScoreboard(board);
	}

}
