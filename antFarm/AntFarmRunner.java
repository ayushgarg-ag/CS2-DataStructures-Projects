package cs2.antFarm;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;

//  Extensions:
//  optimizes behavior when the location in the direction of the food/queen is not empty/valid,
//  the food source gets slowly depleted,
//  new sources of food are generated when the old sources disappear,
//  the worker ants have a lifespan that slowly runs out if they don't have a steady supply of food, and
//  the queen ant generates new worker ants periodically

public class AntFarmRunner {

    public static void main(String[] args) {
        Grid<Actor> g = new BoundedGrid<Actor>(19, 19);
        ActorWorld world = new ActorWorld(g);
        world.add(new Cookie());
        world.add(new Cake());
        world.add(new QueenAnt());

        for (int i = 0; i < 20; i++) {
            world.add(new WorkerAnt());
        }

        world.show();
    }

}
