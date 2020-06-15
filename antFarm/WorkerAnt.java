package cs2.antFarm;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.*;
import java.util.ArrayList;

public class WorkerAnt extends Ant{
    private Location queenLoc;
    private Location foodLoc;
    private int lifeCount;

    public WorkerAnt() {
        setColor(Color.BLACK);
        lifeCount = 50;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Location getQueenLoc() {
        return queenLoc;
    }

    public void setQueenLoc(Location queenLoc) {
        this.queenLoc = queenLoc;
    }

    public Location getFoodLoc() {
        return foodLoc;
    }

    public void setFoodLoc(Location foodLoc) {
        this.foodLoc = foodLoc;
    }

    @Override
    public void act() {
        Location loc = this.getLocation();
        Grid<Actor> gr = this.getGrid();
        ArrayList<Location> emptyAdjacentLocations = gr.getEmptyAdjacentLocations(loc);
        Location newLoc;

        if (gr.getEmptyAdjacentLocations(loc).size() == 0) {
            return;
        }

        if (this.getFood() > 0 && this.getQueenLoc() != null) {
            int dirTowardQueen = loc.getDirectionToward(this.getQueenLoc());
            Location locTowardQueen = loc.getAdjacentLocation(dirTowardQueen);
            newLoc = locTowardQueen;
            if (gr.isValid(locTowardQueen) == false || gr.get(locTowardQueen) != null) {
                newLoc = checkNeighborLocs(this, locTowardQueen, dirTowardQueen);
            }

        }
        else if (this.getFood() == 0 && this.getFoodLoc() != null && (gr.get(this.getFoodLoc()) instanceof Food)) {
            int dirTowardFood = loc.getDirectionToward(this.getFoodLoc());
            Location locTowardFood = loc.getAdjacentLocation(dirTowardFood);
            newLoc = locTowardFood;
            if (gr.isValid(locTowardFood) == false || gr.get(locTowardFood) != null) {
                newLoc = checkNeighborLocs(this, locTowardFood, dirTowardFood);
            }
        }
        else {
            int rand = (int)(Math.random()*emptyAdjacentLocations.size());
            newLoc = emptyAdjacentLocations.get(rand);
        }

        int dir = loc.getDirectionToward(newLoc);
        this.setDirection(dir);
        this.moveTo(newLoc);

        ArrayList<Actor> neighbors = gr.getNeighbors(loc);
        for (int i = 0; i < neighbors.size(); i++) {
            Processable neighbor = (Processable)(neighbors.get(i));
            neighbor.process(this);
        }
        lifeCount--;
        if (lifeCount < 0) {
            this.removeSelfFromGrid();
        }
    }

    public static Location checkNeighborLocs(Ant example, Location locToward, int dirToward) {
        Location loc = example.getLocation();
        Grid<Actor> gr = example.getGrid();

        while (gr.isValid(locToward) == false || gr.get(locToward) != null) {
            int checker = 2; int multiplier = 1;
            if (checker % 2 == 0) {dirToward += 45*multiplier;}
            else {dirToward -= 45*multiplier;}
            checker++; multiplier++;
            locToward = loc.getAdjacentLocation(dirToward);
        }
        return locToward;
    }

    public void process(WorkerAnt worker) {
        if (worker.getQueenLoc() == null) {
            worker.setQueenLoc(this.getQueenLoc());
        }
        if (worker.getFoodLoc() == null) {
            worker.setFoodLoc(this.getFoodLoc());
        }
    }

    public String toString() {
            return super.toString() + "  Life Count = " + lifeCount + "  Food Location: " + foodLoc + "  Queen Location: " + queenLoc;
        }
}
