package me.snoh666.compassTracker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InitCommand extends JavaPlugin {

    private final BukkitRunnable CompassTracker = new CompassTracker(this);

    @Override
    public void onEnable() {
        this.CompassTracker.runTaskTimer(this, 0L, 20L);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("compass")) {
            if (args.length > 0) {
                // detect arg
            } else {
                sender.sendMessage(this.createMessage("Commands works correctly"));
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.CompassTracker.cancel();
    }

    private String createMessage(String msg) {
        return ChatColor.BLUE + "[compass_tracker]: " + ChatColor.WHITE + msg;
    }

}
