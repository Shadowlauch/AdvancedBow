package shadowlauch.advancedbow.main;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;


public class BowListener extends PlayerListener{
    public static AdvancedBow plugin;
    protected static List<Object> disabled=new ArrayList<Object>();
    protected static List<Object> fadisabled=new ArrayList<Object>();
    protected static List<Object> eadisabled=new ArrayList<Object>();
    protected static List<Player> lastexplosivearrow=new ArrayList<Player>();
    protected static List<Player> lastfirearrow=new ArrayList<Player>();
    protected Timer timer= new Timer();

    public BowListener(AdvancedBow instance) {
        plugin = instance;
    }

    public void onPlayerInteract(PlayerInteractEvent e) {
    	int type;
    	if(e.getItem()!=null){
    		type=e.getItem().getTypeId();
    	}
    	else return;
    	String act=e.getAction().toString();
    	Boolean arrows=e.getPlayer().getInventory().contains(262);
    	if(type==261 && arrows && (act=="RIGHT_CLICK_BLOCK" || act=="RIGHT_CLICK_AIR")){
    		if((fadisabled.contains(e.getPlayer()) && ArrowDamageListener.enabledfire.contains(e.getPlayer())) || (eadisabled.contains(e.getPlayer()) && ArrowDamageListener.enabledexplosive.contains(e.getPlayer())) || disabled.contains(e.getPlayer())){
    			int artype;
    			if(ArrowDamageListener.enabledfire.contains(e.getPlayer())) artype=0;
    			else if(ArrowDamageListener.enabledexplosive.contains(e.getPlayer())) artype=1;
    			else artype=2;
    			Messages.send(e.getPlayer(),ChatColor.RED + "" + plugin.config.temp_cd.replaceAll("\\+sec", getLeftTime(e,artype)));
    			e.setCancelled(true);
    			e.getPlayer().updateInventory();
    		}
    		else{
	    			int artype = 0;
	    			long cooldown=0;
	    			boolean settimer=false;
	    			if(ArrowDamageListener.enabledfire.contains(e.getPlayer())){
	    				if(hasItems(e.getPlayer(),0)){
		    				fadisabled.add(e.getPlayer());
		    				fadisabled.add(System.currentTimeMillis()+plugin.config.facdm);
		    				artype=0;
		    				cooldown=plugin.config.facdm;
		    				setDur(e.getPlayer(),0);
		    				settimer=true;
	    				}
	    				else{
	    					lastfirearrow.add(e.getPlayer());
	    					Messages.send(e.getPlayer(),ChatColor.RED + plugin.config.temp_nlb);
	    					ArrowDamageListener.enabledfire.remove(e.getPlayer());
	    					Messages.send(e.getPlayer(),ChatColor.RED + plugin.config.temp_fgd);
	    					e.setCancelled(true);
	    				}
	    			}
	    			else if(ArrowDamageListener.enabledexplosive.contains(e.getPlayer())){
	    				if(hasItems(e.getPlayer(),1)){
		    				eadisabled.add(e.getPlayer());
		    				Messages.send(e.getPlayer(),ChatColor.RED +""+ plugin.config.eacdm);
		    				artype=1;
		    				cooldown=plugin.config.eacdm;
		    				settimer=true;
		    				setDur(e.getPlayer(),1);
	    				}
	    				else{ 
	    					lastexplosivearrow.add(e.getPlayer());
	    					Messages.send(e.getPlayer(),ChatColor.RED + plugin.config.temp_neri);
	    					ArrowDamageListener.enabledexplosive.remove(e.getPlayer());
	    					Messages.send(e.getPlayer(),ChatColor.RED + plugin.config.temp_egd);
	    					e.setCancelled(true);
	    				}
	    			}
	    			else{
	    				disabled.add(e.getPlayer());
	    				disabled.add(System.currentTimeMillis()+plugin.config.cdm);
	    				artype=2;
	    				cooldown=plugin.config.cdm;
	    				settimer=true;
	    			}
	    			if(plugin.hasPerm(e.getPlayer(),"advancedbow.ignorecooldown"))cooldown=0;
	    			if(settimer)timer.schedule(new BowCooldownTimerTask(e.getPlayer(),artype), cooldown);
    		}
    	}
    }
    
    private String getLeftTime(PlayerInteractEvent e,int artype){
    	long ret;
    	if(artype==0)ret=(Long) fadisabled.get(fadisabled.indexOf(e.getPlayer())+1);
    	else if(artype==1)ret=(Long) eadisabled.get(eadisabled.indexOf(e.getPlayer())+1);
    	else ret=(Long) disabled.get(disabled.indexOf(e.getPlayer())+1);
    	double erg=(ret-System.currentTimeMillis());
    	DecimalFormat df = new DecimalFormat( "0.00" );
    	return df.format( erg/1000 );
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
    private void setDur(Player p,int i){
  		int[] duritems={256,257,258,259,267,268,269,270,271,272,273,274,275,276,277,278,279,283,284,285,286,290,291,292,293,294,346,359};
  		List<Integer> items;
  		if(i==0){items=plugin.config.fari;}
      	else{items=plugin.config.eari;}
      	ListIterator<Integer> it = items.listIterator();
      	while(it.hasNext()){
      		int ite=it.next();
      		org.bukkit.inventory.ItemStack item=p.getInventory().getItem(p.getInventory().first(ite));
      		if(contains(duritems,ite)){
      			if((plugin.config.fadiwd && i==0) || (plugin.config.eadiwd && i==1))
      				if(item.getDurability()+1<item.getType().getMaxDurability()) item.setDurability((short) (item.getDurability()+1));
      				else p.getInventory().removeItem(item);
      		}
      		else{
      			if((plugin.config.fasiwd && i==0) || (plugin.config.easiwd && i==1)){
      				if(item.getAmount()>1){
      						item.setAmount(item.getAmount()-1);
      				}
      				else p.getInventory().removeItem(item);
      			}
      		}
      	}
      	p.updateInventory();
  	}
  	private static boolean contains(int[] duritems, Integer integer)
  	{
  	    for(int i=0;i<duritems.length-1;i++)
  	    {
  	        if (duritems[i]==integer)
  	        {
  	            return true;
  	        }
  	    }
  	    return false;
  	}

}
