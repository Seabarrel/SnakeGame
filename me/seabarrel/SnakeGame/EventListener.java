package me.seabarrel.SnakeGame;

import me.seabarrel.SnakeGame.Game.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class EventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClose(final InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) return;
        Player player = (Player) event.getPlayer();
        if (Game.inGame(player)) {
            Inventory inventory = event.getInventory();
            Bukkit.getScheduler().scheduleSyncDelayedTask(SnakeGame.getPlugin(), new Runnable() {
                @Override
                public void run() {
                    player.openInventory(inventory);
                }
            }, 1L);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof Player)) return;
        Player player = (Player) event.getInventory().getHolder();
        if (Game.inGame(player)) {
            if (event.getCurrentItem().getType() == Material.BARRIER) {
                player.sendMessage(ChatColor.RED + "Successfully stopped game.");
                Game.getGame(player).remove();
            } else if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Turn Left")) {
                    Game.getGame(player).setDirection("L");
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Turn Right")) {
                    Game.getGame(player).setDirection("R");
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Turn Up")) {
                    Game.getGame(player).setDirection("U");
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Turn Down")) {
                    Game.getGame(player).setDirection("D");
                }

            }
            event.setCancelled(true);
        }
    }

}
