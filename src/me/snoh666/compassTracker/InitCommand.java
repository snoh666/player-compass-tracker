package me.snoh666.compassTracker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InitCommand extends JavaPlugin {

    final PluginMessages message = new PluginMessages();
    private final BukkitRunnable CompassTracker = new CompassTracker(this);
    private boolean giveCompassOnRespawn = false;

    @Override
    public void onEnable() {
        this.CompassTracker.runTaskTimer(this, 0L, 20L);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("compass")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("give")) {
                    this.giveCompassOnRespawn = !this.giveCompassOnRespawn;
                    String sayValue = "true";
                    if (!this.giveCompassOnRespawn) {
                        sayValue = "false";
                    }
                    sender.sendMessage(this.message.createMessage("Giving compass on respawn is: " + sayValue));
                    if (this.giveCompassOnRespawn) {
                        Bukkit.getPluginManager().registerEvents(new PlayerEvents(), this);
                    } else {
                        PlayerRespawnEvent.getHandlerList().unregister(new PlayerEvents());
                        HandlerList.unregisterAll();
                    }
                    return true;
                }
            } else {
                sender.sendMessage(this.message.createMessage("Commands works correctly"));
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.CompassTracker.cancel();
    }

}
