package me.seabarrel.SnakeGame.Game;

public class SnakeFragment {

    private int location;

    public SnakeFragment(int slot) {
        this.location = slot;
    }

    public int getSlotNumber() {
        return this.location;
    }

    public void travel(String direction) {
        if (direction == "U") {

            if (this.location < 9) {
                this.location = this.location + 36;
            } else {
                this.location = this.location - 9;
            }

        } else if (direction == "D") {

            if (this.location > 35) {
                this.location = this.location - 36;
            } else {
                this.location = this.location + 9;
            }

        } else if (direction == "L") {

            if (this.location % 9 == 0) {
                this.location = this.location + 8;
            } else {
                this.location--;
            }

        } else if (direction == "R") {

            if ((this.location - 8) % 9 == 0) {
                this.location = this.location - 8;
            } else {
                this.location++;
            }

        } else {
            return;
        }
        return;
    }
}
