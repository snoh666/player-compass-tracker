package me.snoh666.manhunt;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CompassTracker extends BukkitRunnable {
    private final JavaPlugin plugin;
    private int counter = 0;
    private boolean hasSentMessage = false;

    public CompassTracker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (counter > 10) {
            this.cancel();
            return;
        }
        if (!this.hasSentMessage) {
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                player.sendMessage("One compass tick, track. Player: " + player.getName());
            }
            this.hasSentMessage = true;
        }
        this.counter++;
    }
}
