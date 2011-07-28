package shadowlauch.advancedbow.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
	
	protected static void send(Player p,String m){
		m=m.replaceAll("<white>", ChatColor.WHITE.toString()).replaceAll("<aqua>", ChatColor.AQUA.toString()).replaceAll("<black>",ChatColor.BLACK.toString()).replaceAll("<darkaqua>", ChatColor.DARK_AQUA.toString()).replaceAll("<darkblue>",ChatColor.DARK_BLUE.toString()).replaceAll("<darkgreen>", ChatColor.DARK_GREEN.toString()).replaceAll("<darkaqua>", ChatColor.DARK_AQUA.toString()).replaceAll("<darkred>", ChatColor.DARK_RED.toString()).replaceAll("<darkpurple>",ChatColor.DARK_PURPLE.toString()).replaceAll("<gold>", ChatColor.GOLD.toString()).replaceAll("<gray>", ChatColor.GRAY.toString()).replaceAll("<darkgray>", ChatColor.DARK_GRAY.toString()).replaceAll("<blue>", ChatColor.BLUE.toString()).replaceAll("<green>",ChatColor.GREEN.toString()).replaceAll("<red>", ChatColor.RED.toString()).replaceAll("<lightpurple>", ChatColor.LIGHT_PURPLE.toString()).replaceAll("<yellow>", ChatColor.YELLOW.toString());
		p.sendMessage(m);
	}

}
