package shadowlauch.advancedbow.main;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class AdvancedBow extends JavaPlugin {
	
	private final BowListener bl = new BowListener(this);
	private final ArrowDamageListener adl = new ArrowDamageListener(this);
	private boolean UsePermissions;
	public static PermissionHandler Permissions;

	private void setupPermissions() {
		Plugin permtest = this.getServer().getPluginManager().getPlugin("Permissions");
		if (Permissions == null) {
			if (permtest != null) {
				UsePermissions = true;
				Permissions = ((Permissions) permtest).getHandler();
				if(config.perm.equals("permission")){
					log.info("[AdvancedBow] Permissions Plugin detected!");
				}
				
			} else {
				if(config.perm.equals("permission")){
					log.info("[AdvancedBow] Permissions Plugin not detected. Using Bukkit Default Permission Pulgin.");
				}
				UsePermissions = false;
			}
		}
	}
	public boolean hasPerm(Player p, String string) {
		if (config.perm.equals("permission")) {
			if(UsePermissions){
				return Permissions.has(p, string);
			}
			else{
				return p.isOp();
			}
			
		}
		else if(config.perm.equals("bukkit-permission")){
			return p.hasPermission(string);
		}
		else if(config.perm.equals("op")){
			return p.isOp();
		}
		else{
			return true;
		}
	}

	// CONFIG
	public ConfigLoader config=new ConfigLoader(this);


	// LOG
	protected static final Logger log = Logger.getLogger("Minecraft");
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_INTERACT, bl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.ENTITY_DAMAGE, adl, Event.Priority.Normal, this);
		pm.registerEvent(Event.Type.PROJECTILE_HIT, adl, Event.Priority.Normal, this);
		config.configCheck();
		setupPermissions();
		getCommand("advancedbow").setExecutor(new Commands(this));
		log.info("[AdvancedBow] Version " + this.getDescription().getVersion()
				+ " by Shadowlauch enabled.");
		if(Update.getVersion()>Double.valueOf(this.getDescription().getVersion())){
			log.info("[AdvancedBow] >>> A new version is aviable! <<<");
		}
		
	}

}
