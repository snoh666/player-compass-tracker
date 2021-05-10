package me.snoh666.manhunt;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TestCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().broadcastMessage("[manhunt]: Manhunt plugin started.");
    }

    @Override
    public void onDisable() {}

    public boolean onCommand(CommandSender sender, String label, String[] args) {
        if (label.equalsIgnoreCase("manhunt")) {
            if (sender instanceof Player) {
                // player
                Player player = (Player) sender;
                String playerName = player.getName();
                player.sendMessage("[manhunt]: Hey " + playerName + ", plugin works correctly");
            } else {
                // server
                sender.sendMessage("[manhunt]: Hello server, plugin works correctly");
            }
            return true;
        }
        return false;
    }
}
