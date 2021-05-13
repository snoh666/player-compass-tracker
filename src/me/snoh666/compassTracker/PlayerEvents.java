package me.snoh666.compassTracker;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerEvents implements Listener {

     final PluginMessages message = new PluginMessages();

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Material item = Material.COMPASS;
        ItemStack itemStack = new ItemStack(item, 1);
        Player player = event.getPlayer();
        player.getInventory().addItem(itemStack);
    }

}
