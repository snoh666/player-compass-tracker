package me.snoh666.manhunt;

import org.bukkit.plugin.java.JavaPlugin;

public class InitCommand extends JavaPlugin {

    @Override
    public void onEnable() {
        new CompassTracker(this).runTaskTimer(this, 0L, 20L);
    }

    @Override
    public void onDisable() {}
}
