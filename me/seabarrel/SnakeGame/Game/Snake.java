package me.seabarrel.SnakeGame.Game;

import java.util.ArrayList;

public class Snake{

    private String direction;
    private SnakeFragment head;
    private ArrayList<SnakeFragment> body;
    private Game game;

    public Snake(Game game) {
        this.direction = null;
        this.head = new SnakeFragment(22);

        this.body = new ArrayList<SnakeFragment>();

        this.game = game;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        if (this.direction == "D" && direction == "U") return;
        if (this.direction == "U" && direction == "D") return;
        if (this.direction == "R" && direction == "L") return;
        if (this.direction == "L" && direction == "R") return;
        this.direction = direction;
    }

    public int getHead() {
        return this.head.getSlotNumber();
    }

    public void moveSnake() {
        if (this.body.size() > 0) {
            this.body.remove(0);
            this.body.add(new SnakeFragment(head.getSlotNumber()));
        }
        this.head.travel(this.direction);
    }

    public void moveSnakeWithAdding() {
        this.body.add(new SnakeFragment(head.getSlotNumber()));
        this.head.travel(this.direction);
    }

    public ArrayList<Integer> getBody() {
        ArrayList<Integer> locations = new ArrayList<Integer>();
        for (SnakeFragment location : this.body) {
            locations.add(location.getSlotNumber());
        }
        return locations;
    }

    public Game getGame() {
        return this.game;
    }
}
