package me.snoh666.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InitCommand extends JavaPlugin {
    private boolean isCompassTrackerStarted = false;
    private final BukkitRunnable CompassTracker = new CompassTracker(this);

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("manhunt")) {
            if (args[0] != null) {
                if (this.isCompassTrackerStarted && args[0].equals("stop")) {
                    this.CompassTracker.cancel();
                    sender.sendMessage("[manhunt]: updating compass stopped");
                    return true;
                } else if(!this.isCompassTrackerStarted && args[0].equals("start")) {
                    this.CompassTracker.runTaskTimer(this, 0L, 20L);
                    sender.sendMessage("[manhunt]: updating compass started");
                    return true;
                }
                return false;
            } else {
                sender.sendMessage("[manhunt]: Correct command usage: /manhunt <start | stop>");
                return true;
            }
        }
        return false;
    }
}
