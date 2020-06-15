package cs2.maze;

import info.gridworld.actor.*;

public class MazeRunner {
    public static void main(String[] args) {
        Maze maze = new Maze(20,20);
        ActorWorld world = maze.printWorld(new Mouse(),new Cheese());
        world.show();

    }

}
