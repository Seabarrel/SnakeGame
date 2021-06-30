package me.seabarrel.SnakeGame;

import me.seabarrel.SnakeGame.Commands.SnakeCommand;
import me.seabarrel.SnakeGame.Game.Game;
import me.seabarrel.SnakeGame.EventListener;
import me.seabarrel.SnakeGame.Game.Progression;
import org.bukkit.plugin.java.JavaPlugin;

public class SnakeGame extends JavaPlugin {

    private static SnakeGame plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        new SnakeCommand(this.getPlugin());
        System.out.println("Successfully enabled" + this.getName() + "!");

        Progression.removeAll();
        Game.removeAll();
        getServer().getScheduler().scheduleSyncRepeatingTask(this, Progression::manage, 0, 12);

    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }

    public static SnakeGame getPlugin() {
        return plugin;
    }

    public String getAuthor() {
        return "Seabarrel";
    }

    public String getVersion() {
        return "1.1.0";
    }

}
