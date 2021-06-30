package me.seabarrel.SnakeGame.Game;

import me.seabarrel.SnakeGame.SnakeGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Game {

    private static Map<Game, Player> instances = new ConcurrentHashMap<>();
    private Snake snake;
    private Player player;
    private Inventory updater;
    private Food food;
    private int score;

    public Game(Player player) {
        this.player = player;
        instances.put(this, player);

        score = 0;
        updater = getGui();
        player.openInventory(updater);

        snake = new Snake(this);
        food = new Food(getCorrectLocation());
        progress();
        start();
    }

    public void progress() {

        ItemStack field = Items.makeItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, ChatColor.GRAY + "-");
        for (int x = 0; x < 45; x++) {
            updater.setItem(x, field);
        }

        if (snake.getHead() == food.getLocation()) {
            score += 10;
            food = new Food(getCorrectLocation());
            ItemStack statistics = Items.makeItemWithLore(Material.CLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + "Statistics", Arrays.asList(ChatColor.GRAY + "Score: " + ChatColor.AQUA + "" + score));
            updater.setItem(45, statistics);
            snake.moveSnakeWithAdding();
        } else {
            snake.moveSnake();
        }
        updater.setItem(food.getLocation(), food.getItem());

        if (snake.getBody().contains(snake.getHead())) {
            dead();
            return;
        }
        for (int bodyPart : snake.getBody()) {
            updater.setItem(bodyPart, Items.getSnakeBody());
        }
        updater.setItem(snake.getHead(), Items.getSnakeHead());

    }

    public Inventory getGui() {

        Inventory snakeGui = Bukkit.createInventory(player, 54, ChatColor.DARK_GREEN + "Snake Game");


        ItemStack statistics = Items.makeItemWithLore(Material.CLOCK, ChatColor.GREEN + "" + ChatColor.BOLD + "Statistics", Arrays.asList(ChatColor.GRAY + "Score: " + ChatColor.AQUA + "" + score));
        snakeGui.setItem(45, statistics);

        ItemStack border = Items.makeItem(Material.GRAY_STAINED_GLASS_PANE, ChatColor.DARK_GRAY + "-");
        snakeGui.setItem(46, border);
        snakeGui.setItem(51, border);
        snakeGui.setItem(52, border);

        ItemStack field = Items.makeItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE, ChatColor.GRAY + "-");
        for (int x = 0; x < 45; x++) {
            snakeGui.setItem(x, field);
        }

        snakeGui.setItem(47, Items.renameItem(Items.getArrowLeft(), ChatColor.GREEN + "Turn Left"));
        snakeGui.setItem(48, Items.renameItem(Items.getArrowUp(), ChatColor.GREEN + "Turn Up"));
        snakeGui.setItem(49, Items.renameItem(Items.getArrowDown(), ChatColor.GREEN + "Turn Down"));
        snakeGui.setItem(50, Items.renameItem(Items.getArrowRight(), ChatColor.GREEN + "Turn Right"));

        ItemStack close = Items.makeItem(Material.BARRIER, ChatColor.RED + "Close Game");
        snakeGui.setItem(53, close);

        return snakeGui;
    }

    public int getCorrectLocation() {
        int random = (int)(Math.random() * 44);
        while (random == snake.getHead() || snake.getBody().contains(random)) {
            random = (int)(Math.random() * 44);
        }
        return random;
    }

    public void setDirection(String direction) {
        snake.setDirection(direction);
    }

    public void start() {
        new Progression(this);
    }

    public void remove() {
        instances.remove(this);
        Progression.remove(this);
        player.closeInventory();
    }

    public void dead() {

        for (int bodyPart : snake.getBody()) {
            updater.setItem(bodyPart, Items.makeItem(Material.BONE, ChatColor.RED + "DEAD"));
        }
        updater.setItem(snake.getHead(), Items.makeItem(Material.SKELETON_SKULL, ChatColor.RED + "DEAD"));

        Progression.remove(this);
    }

    public Player getPlayer() {
        return player;
    }

    public static Game getGame(Player player) {
        for (Game game : instances.keySet()) {
            if (instances.get(game) == player) return game;
        }
        return null;
    }

    public static boolean inGame(Player player) {
        if (instances.get(getGame(player)) == null) return false;
        return true;
    }

    public static void removeAll() {
        instances.clear();
    }
}
