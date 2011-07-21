package shadowlauch.advancedbow.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowDamageListener extends EntityListener {
	
    public static AdvancedBow plugin;
    public static List<Player> enabledfire=new ArrayList<Player>();
    public static List<Player> enabledexplosive=new ArrayList<Player>();

    public ArrowDamageListener(AdvancedBow instance) {
        plugin = instance;
    }
    
    public void onEntityDamage(EntityDamageEvent e) {
    	if(e instanceof EntityDamageByProjectileEvent){
	    	EntityDamageByProjectileEvent f=(EntityDamageByProjectileEvent)e;
	    	if(f.getProjectile() instanceof Arrow){
	    		if(f.getProjectile().getShooter() instanceof Player){
	    			Player p=(Player) f.getProjectile().getShooter();
	    			e.setDamage((int) (e.getDamage()*plugin.config.ad));
	    			if(plugin.hasPerm(p, "advancedbow.arrows.fire") && (enabledfire.contains(p) || BowListener.lastfirearrow.contains(p)) && plugin.config.fae){
	    				if(BowListener.lastfirearrow.contains(p))BowListener.lastfirearrow.remove(p);
	    				if(!(e.getEntity() instanceof Player) || (p.getWorld().getPVP())){
		    				e.getEntity().setFireTicks(plugin.config.ft);
	    				}
	    			}
	    			else if(plugin.hasPerm(p, "advancedbow.arrows.explosive") &&  (enabledexplosive.contains(p) || BowListener.lastexplosivearrow.contains(p)) && plugin.config.eae){
	    				if(BowListener.lastexplosivearrow.contains(p))BowListener.lastexplosivearrow.remove(p);
	    				if(!(e.getEntity() instanceof Player) || (p.getWorld().getPVP())){
		    				e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), plugin.config.eaer);
	    				}
	    			}
	    		}
	    	}
    	}
    }
    
    public void onProjectileHit(ProjectileHitEvent e){
    	if(e.getEntity() instanceof Arrow){
    		Arrow a=(Arrow) e.getEntity();
	    	if(a.getShooter() instanceof Player){
	    		Player p=(Player)a.getShooter();
	    		if(BowListener.lastfirearrow.contains(p))BowListener.lastfirearrow.remove(p);
	    		if(plugin.hasPerm(p, "advancedbow.arrows.fire") &&  (enabledfire.contains(p) || BowListener.lastfirearrow.contains(p))  && plugin.config.fae){
	    			a.setFireTicks(plugin.config.ft);
	    		}
	    		else if(plugin.hasPerm(p, "advancedbow.arrows.explosive") && (enabledexplosive.contains(p) || BowListener.lastexplosivearrow.contains(p)) && plugin.config.eae){
	    			if(BowListener.lastexplosivearrow.contains(p))BowListener.lastexplosivearrow.remove(p);
	    			e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), plugin.config.eaer);
	    		}
	    	}
    	}
    }

  	
}
