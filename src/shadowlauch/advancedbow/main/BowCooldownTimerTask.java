package shadowlauch.advancedbow.main;

import java.util.TimerTask;
import org.bukkit.entity.Player;

public class BowCooldownTimerTask extends TimerTask {
	Player p;
	public BowCooldownTimerTask(Player np) {
		p=np;
	}

	@Override
	public void run() {
		BowListener.disabled.remove(p);
		BowListener.disabled.remove(BowListener.disabled.indexOf(p)+1);
	}

}
