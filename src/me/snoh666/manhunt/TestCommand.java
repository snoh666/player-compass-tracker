package me.snoh666.manhunt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TestCommand extends JavaPlugin {

    @Override
    public void onEnable() {}

    @Override
    public void onDisable() {}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("manhunt")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("[manhunt]: Hey , plugin works correctly");
            } else {
                sender.sendMessage("[manhunt]: Hello server, plugin works correctly");
            }
            return true;
        }
        return false;
    }
}
