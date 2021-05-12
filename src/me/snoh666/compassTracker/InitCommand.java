package me.snoh666.compassTracker;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InitCommand extends JavaPlugin {

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
                    sender.sendMessage(this.createMessage("Giving compass on respawn is: " + sayValue));
                }
            } else {
                sender.sendMessage(this.createMessage("Commands works correctly"));
                return true;
            }
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.CompassTracker.cancel();
    }

    @EventHandler public void onRespawn(PlayerRespawnEvent event) {
        Material item = Material.COMPASS;
        ItemStack itemStack = new ItemStack(item, 1);
        event.getPlayer().getInventory().addItem(itemStack);
    }

    private String createMessage(String msg) {
        return ChatColor.BLUE + "[compass_tracker]: " + ChatColor.WHITE + msg;
    }

}
