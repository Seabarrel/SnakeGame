package me.seabarrel.SnakeGame.Game;

import org.bukkit.entity.Player;
import java.util.ArrayList;

public class Progression {

    private static ArrayList<Game> instances = new ArrayList<>();
    private Game game;

    public Progression(Game game) {
        this.game = game;
        instances.add(game);
    }

    public static void manage() {
        instances.forEach(Game::progress);
    }

    public static void remove(Game game) {
        instances.remove(game);
    }

    public static void removeAll() {
        instances.clear();
    }

}
