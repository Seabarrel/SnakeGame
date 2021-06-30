package me.seabarrel.SnakeGame.Game;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Food {

    private ItemStack item;
    private int location;

    public Food(int location) {
        this.item = getRandomFood((int)(Math.random() * 3));
        this.location = location;
    }

    public ItemStack getRandomFood(int num) {
        ItemStack item = Items.makeItem(Material.SWEET_BERRIES, ChatColor.LIGHT_PURPLE + "Berries");
        switch (num) {
            case 0:
                item = Items.makeItem(Material.SWEET_BERRIES, ChatColor.LIGHT_PURPLE + "Berries");
                break;
            case 1:
                item = Items.makeItem(Material.APPLE, ChatColor.RED + "Apple");
                break;
            case 2:
                item = Items.makeItem(Material.GLOW_BERRIES, ChatColor.GOLD + "Glow Berries");
                break;
            case 3:
                item = Items.makeItem(Material.MELON_SLICE, ChatColor.RED + "Watermelon");
                break;
        }
        return item;
    }

    public int getLocation() {
        return this.location;
    }

    public ItemStack getItem() {
        return this.item;
    }

}
