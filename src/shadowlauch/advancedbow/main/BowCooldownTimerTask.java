package shadowlauch.advancedbow.main;

import java.util.TimerTask;
import org.bukkit.entity.Player;

public class BowCooldownTimerTask extends TimerTask {
	Player p;
	int artype;
	public BowCooldownTimerTask(Player np,int nartype) {
		p=np;
		artype=nartype;
	}

	@Override
	public void run() {
		if(artype==0){
			BowListener.fadisabled.remove(p);
			BowListener.fadisabled.remove(BowListener.fadisabled.indexOf(p)+1);
		}
		else if(artype==1){
			BowListener.eadisabled.remove(p);
			BowListener.eadisabled.remove(BowListener.eadisabled.indexOf(p)+1);
		}
		else{
			BowListener.disabled.remove(p);
			BowListener.disabled.remove(BowListener.disabled.indexOf(p)+1);
		}
	}

}
