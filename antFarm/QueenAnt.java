package cs2.antFarm;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class QueenAnt extends Ant {
    private int antGenerator = 1;

    public QueenAnt() {
        setColor(Color.MAGENTA);
    }

    public void process(WorkerAnt worker) {
        int amountFood = worker.getFood();
        this.setFood(this.getFood() + amountFood);

        worker.setFood(0);
        worker.setQueenLoc(this.getLocation());
        worker.setColor(Color.BLACK);
    }

    public void act() {
        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        ArrayList<Location> empty = gr.getEmptyAdjacentLocations(loc);
        int rand = (int)(Math.random()*empty.size());

        if ((antGenerator % 20 == 0) && (empty.size() > 0)) {
            Location randLoc = empty.get(rand);
            WorkerAnt newAnt = new WorkerAnt();
            newAnt.putSelfInGrid(gr, randLoc);
        }
        antGenerator++;
    }
}
