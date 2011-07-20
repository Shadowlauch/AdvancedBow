package shadowlauch.advancedbow.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private final AdvancedBow plugin;

      public Commands( AdvancedBow instance) {
      plugin = instance;
      }
      
      public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
          if(!(sender instanceof Player)) {
              return false;
          }
          Player p=(Player)sender;
          if(args.length == 1) {
              String arg1 = args[0];
              if(arg1.equals("help")) {
            	  sender.sendMessage(ChatColor.GOLD + "===AdvancedBow Help=="); 
            	  if(plugin.hasPerm(p, "advancedbow.fire")){
            		  sender.sendMessage(ChatColor.GOLD + "/advancedbow fire" + ChatColor.BLUE + " - Enables Fire-Arrows.");
            	  }
            	  if(plugin.hasPerm(p, "advancedbow.admin")){
            		  sender.sendMessage(ChatColor.GOLD + "/advancedbow reload" + ChatColor.BLUE + " - Reloads the Config-File.");
            		  sender.sendMessage(ChatColor.GOLD + "/advancedbow version" + ChatColor.BLUE + " - Shows the Version.");
            	  }
            	  return true;
              }
              if(arg1.equals("reload")) {
            	  if(plugin.hasPerm(p, "advancedbow.admin")){
            		  plugin.config.configCheck();
            		  sender.sendMessage(ChatColor.GOLD + "Config-File reloaded."); 
            	  }
            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_nep);
            	  return true;
              }
              if(arg1.equals("version")) {
            	  if(plugin.hasPerm(p, "advancedbow.admin")){
            	  sender.sendMessage(ChatColor.GOLD + "Version: " + plugin.getDescription().getVersion()
				+ " by Shadowlauch"); 
            	  }
            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_nep);
            	  return true;
              }
              if(arg1.equals("fire")){
            	  if(plugin.config.fae){
	            	  if(plugin.hasPerm(p,"advancedbow.fire")){
	            		  if(p.getInventory().contains(327)){
	            			  if(!ArrowDamageListener.enabledfire.contains(p)){
	            				  ArrowDamageListener.enabledfire.add(p);
	            				  sender.sendMessage(ChatColor.RED + plugin.config.temp_fe);
	            			  }
	            			  else{
	            				  ArrowDamageListener.enabledfire.remove(p);
	            				  sender.sendMessage(ChatColor.RED + plugin.config.temp_fd);
	            			  }
	            		  }
	            		  else sender.sendMessage(ChatColor.RED + plugin.config.temp_nlb);
	            	  }
	            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_nep);
            	  }
            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_fane);
            	  return true;
              }
          }
          return false;
       }
}