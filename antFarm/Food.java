package cs2.antFarm;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public abstract class Food extends Actor implements Processable {

    private int biteSize;
    private int numberOfBites;
    private int foodGenerator;

    public Food(int size) {
        biteSize = size;
    }

    public String toString() {
        return super.toString() + " Bite Size: " + biteSize;
    }

    public void act() {
        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        ArrayList<Location> empty = gr.getEmptyAdjacentLocations(loc);
        int rand = (int)(Math.random()*empty.size());
        Location randLoc;

        if (empty.size() > 0) {
            randLoc = empty.get(rand);
        }
        else { return; }

        if (numberOfBites >= 75) {
            this.removeSelfFromGrid();

            Cake cakeSlice = new Cake();
            cakeSlice.putSelfInGrid(gr, randLoc);
        }
        foodGenerator++;
    }

    public void process(WorkerAnt worker) {
        int amountFood = worker.getFood();
        if (amountFood == 0) {
            amountFood += biteSize;
            worker.setFood(amountFood);
            worker.setColor(Color.RED);
            worker.setLifeCount(worker.getLifeCount() + biteSize*8);
            numberOfBites++;
        }
        worker.setFoodLoc(this.getLocation());
    }

}
