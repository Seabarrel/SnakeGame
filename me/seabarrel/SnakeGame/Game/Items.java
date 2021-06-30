package me.seabarrel.SnakeGame.Game;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class Items {

    public static ItemStack getSkull(String url) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        if(url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public static ItemStack makeItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemData = item.getItemMeta();

        itemData.setDisplayName(name);
        item.setItemMeta(itemData);

        return item;

    }

    public static ItemStack makeItemWithLore(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemData = item.getItemMeta();

        itemData.setDisplayName(name);
        itemData.setLore(lore);
        item.setItemMeta(itemData);

        return item;

    }

    public static ItemStack renameItem(ItemStack item, String name) {
        ItemMeta itemData = item.getItemMeta();
        itemData.setDisplayName(name);
        item.setItemMeta(itemData);

        return item;
    }

    public static ItemStack getSnakeHead() {
        return getSkull("http://textures.minecraft.net/texture/51b4b20189d902bc6c3fabe23d9f578697f544cf415317787374118393da4");
    }

    public static ItemStack getSnakeBody() {
        return getSkull("http://textures.minecraft.net/texture/94d00b47f2a99c3fa9cba5d9ab8d3b19c5e02f213ad8217f7d49bac2b4d4fe");
    }

    public static ItemStack getArrowLeft() {
        return getSkull("http://textures.minecraft.net/texture/8550b7f74e9ed7633aa274ea30cc3d2e87abb36d4d1f4ca608cd44590cce0b");
    }

    public static ItemStack getArrowUp() {
        return getSkull("http://textures.minecraft.net/texture/5da027477197c6fd7ad33014546de392b4a51c634ea68c8b7bcc0131c83e3f");
    }

    public static ItemStack getArrowDown() {
        return getSkull("http://textures.minecraft.net/texture/ff7416ce9e826e4899b284bb0ab94843a8f7586e52b71fc3125e0286f926a");
    }

    public static ItemStack getArrowRight() {
        return getSkull("http://textures.minecraft.net/texture/96339ff2e5342ba18bdc48a99cca65d123ce781d878272f9d964ead3b8ad370");
    }
}
