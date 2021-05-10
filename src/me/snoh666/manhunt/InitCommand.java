package me.snoh666.manhunt;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InitCommand extends JavaPlugin {

    private final BukkitRunnable CompassTracker = new CompassTracker(this);

    @Override
    public void onEnable() {
        this.CompassTracker.runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() {
        this.CompassTracker.cancel();
    }
}
