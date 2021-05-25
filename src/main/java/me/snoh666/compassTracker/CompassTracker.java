package me.snoh666.compassTracker;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.meta.CompassMeta;
import java.util.Objects;
import java.util.UUID;

public class CompassTracker extends BukkitRunnable {
    private final JavaPlugin plugin;

    public CompassTracker(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
            for (Player player : this.plugin.getServer().getOnlinePlayers()) {
                UUID closestPlayerUid = this.getClosestPlayer(player.getLocation(), player.getUniqueId());

                if (closestPlayerUid != null) {
                    Location closestPlayerLoc = getPlayerFromUid(closestPlayerUid).getLocation();
                       if(player.getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
                           ItemStack compass = player.getInventory().getItemInMainHand();
                           CompassMeta compassMeta = (CompassMeta) compass.getItemMeta();
                           if(player.getWorld().getEnvironment() == World.Environment.NETHER) {
                               compassMeta.setLodestoneTracked(false);
                               compassMeta.setLodestone(closestPlayerLoc);
                               compass.setItemMeta(compassMeta);
                           } else {
                               compassMeta.setLodestoneTracked(true);
                               compassMeta.setLodestone(null);
                               player.setCompassTarget(closestPlayerLoc);
                           }
                           compass.setItemMeta(compassMeta);
                       }
                }
            }
    }

    private UUID getClosestPlayer(Location loc, UUID exceptPlayerId) {
        if (Objects.requireNonNull(loc.getWorld()).getEnvironment() != World.Environment.NORMAL && Objects.requireNonNull(loc.getWorld()).getEnvironment() != World.Environment.NETHER) {
            return null;
        }
        UUID closestPlayer = null;
        double distanceToClosestPlayer = 0.0D;
        double xLoc = loc.getX();
        double yLoc = loc.getY();

        for (Player player : this.plugin.getServer().getOnlinePlayers()) {
            if (player.getUniqueId() != exceptPlayerId && player.getGameMode() != GameMode.SPECTATOR) {
                double p2xLoc = player.getLocation().getX();
                double p2yLoc = player.getLocation().getY();
                double distance = Math.sqrt((p2yLoc - yLoc) * (p2yLoc - yLoc) + (p2xLoc - xLoc) * (p2xLoc - xLoc));

                if (closestPlayer == null) {
                    distanceToClosestPlayer = distance;
                    closestPlayer = player.getUniqueId();
                } else {
                    if (distance < distanceToClosestPlayer) {
                        distanceToClosestPlayer = distance;
                        closestPlayer = player.getUniqueId();
                    }
                }
            }
        }

        return closestPlayer;
    }

    private Player getPlayerFromUid(UUID uid) {
        return plugin.getServer().getPlayer(uid);
    }
}
