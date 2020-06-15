package cs2.antFarm;

import info.gridworld.actor.Actor;

public abstract class Ant extends Actor implements Processable {
    private int food;

    public Ant() {
        food = 0;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    @Override
    public void act() {}

    @Override
    public String toString() {
        return super.toString() + " Food: " + food;
    }
}
