package shadowlauch.advancedbow.main;

import java.util.List;
import java.util.ListIterator;

import net.minecraft.server.ItemStack;

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
            	  if(plugin.hasPerm(p, "advancedbow.arrows.fire")){
            		  sender.sendMessage(ChatColor.GOLD + "/advancedbow fire" + ChatColor.BLUE + " - Enables Fire-Arrows.");
            	  }
            	  if(plugin.hasPerm(p, "advancedbow.arrows.explosive")){
            		  sender.sendMessage(ChatColor.GOLD + "/advancedbow explosive" + ChatColor.BLUE + " - Enables Explosive-Arrows.");
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
	            	  if(plugin.hasPerm(p,"advancedbow.arrows.fire")){
	            		  if(hasItems(p,0)){
	            			  if(!ArrowDamageListener.enabledfire.contains(p)){
	            				  ArrowDamageListener.enabledfire.add(p);
	            				  sender.sendMessage(ChatColor.RED + plugin.config.temp_fe);
	            				  if(ArrowDamageListener.enabledexplosive.contains(p)){
	            					  ArrowDamageListener.enabledexplosive.remove(p);
	            				  }
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
              if(arg1.equals("explosive")){
            	  if(plugin.config.eae){
	            	  if(plugin.hasPerm(p,"advancedbow.arrows.explosive")){
	            		  if(hasItems(p,1)){
	            			  if(!ArrowDamageListener.enabledexplosive.contains(p)){
	            				  ArrowDamageListener.enabledexplosive.add(p);
	            				  sender.sendMessage(ChatColor.RED + plugin.config.temp_ee);
	            				  if(ArrowDamageListener.enabledfire.contains(p)){
	            					  ArrowDamageListener.enabledfire.remove(p);
	            				  }
	            			  }
	            			  else{
	            				  ArrowDamageListener.enabledexplosive.remove(p);
	            				  sender.sendMessage(ChatColor.RED + plugin.config.temp_ed);
	            			  }
	            		  }
	            		  else sender.sendMessage(ChatColor.RED + plugin.config.temp_neri);
	            	  }
	            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_nep);
            	  }
            	  else sender.sendMessage(ChatColor.RED + plugin.config.temp_fane);
            	  return true;
              }
          }
          return false;
       }
      private boolean hasItems(Player p, int i){
    	List<Integer> items;
    	if(i==0){items=plugin.config.fari;}
    	else{items=plugin.config.eari;}
      	ListIterator<Integer> it = items.listIterator();
      	while(it.hasNext()){
      		if(!p.getInventory().contains(Integer.valueOf(it.next()).intValue())){
      			return false;
      		}
      	}
      	return true;
      }
}