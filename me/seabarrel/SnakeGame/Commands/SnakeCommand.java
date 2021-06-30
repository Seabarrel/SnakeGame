package me.seabarrel.SnakeGame.Commands;

import me.seabarrel.SnakeGame.Game.Game;
import me.seabarrel.SnakeGame.SnakeGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SnakeCommand implements CommandExecutor {

    private SnakeGame plugin;

    public SnakeCommand(SnakeGame plugin) {
        this.plugin = plugin;
        plugin.getCommand("snakegame").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can play the snake game!");
            return true;
        }
        Player player = (Player) sender;
        if (player.hasPermission("snake.command.execute")) {
            player.sendMessage(ChatColor.AQUA + "Opening gui..");
            new Game(player);
        } else {
            player.sendMessage(ChatColor.RED + "You don't have permissions to play the snake game!");
        }
        return true;
    }
}
