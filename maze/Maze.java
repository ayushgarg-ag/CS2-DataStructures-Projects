package cs2.maze;

import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class Maze
{
    Cell[][] maze;
    int width;
    int height;
    int[] startLoc;
    int[] endLoc;

    public Maze (int w, int h){
        maze = new Cell[w][h];
        width = w;
        height = h;
        startLoc = new int[2];
        endLoc = new int[2];
        generate();
    }
    public void generate(){
        // Fill maze will new cells
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)
                maze[x][y] = new Cell(x,y);
        // Set each cells adjacent squares
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++){
                Cell L;Cell R;Cell U;Cell D;
                // Fill Adjacent Squares
                L = (x == 0)?null:maze[x-1][y];
                R = (x == (width - 1))?null:maze[x+1][y];
                U = (y == 0)?null:maze[x][y-1];
                D = (y == (height - 1))?null:maze[x][y+1];
                maze[x][y].setCells(L,R,U,D);
            }
        int[] location = {0,0};
        int loc = rand(0,width-1);
        // Initiate entrance and exit points
        maze[loc][0].setWall(0,false);
        startLoc[0] = loc;
        startLoc[1] = 0;
        maze[(width-1)-loc][height-1].setWall(2,false);
        endLoc[0] = (width-1)-loc;
        endLoc[1] = height - 1;
        // Generate Maze
        recursiveBacktracker(location);
    }
    public void recursiveBacktracker(int[] loc){
        maze[loc[0]][loc[1]].setVisited(true);			// Set this cells visited to true.
        ArrayList<Cell> lst = new ArrayList<Cell>();	// Initiate neighbor array.
        for (int i = 0; i < 4; i++){					// Fill Neighbor Array
            if (maze[loc[0]][loc[1]].getCell(i) != null)
                lst.add(maze[loc[0]][loc[1]].getCell(i));

        }
        // Shuffle neighbor array
        Collections.shuffle(lst);
        for (Cell c: lst){							// For each neighbor...
            if (!c.getVisited()){ 					// ...that hasn't been visited,...
                for (int i = 0; i < 4; i++) 		// ...set the connected wall to open,...
                    if (c.getCell(i) == maze[loc[0]][loc[1]]) c.setWall(i,false);
                recursiveBacktracker(c.getLoc()); 	// ...and repeat method with that cell.
            }
        }
    }
    public int rand(int low,int high){
        return (int)(Math.random() * ((high+1) - low)) + low;
    }

    public Cell getCell(int x, int y){
        return maze[x][y];
    }
    public ActorWorld printWorld(Actor start, Actor end){
        Grid<Actor> gridMundo = new BoundedGrid<Actor>((width *2) + 1, (height *2) + 1);
        ActorWorld world = new ActorWorld(gridMundo);
        boolean[][] grid;
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++){
                grid = getCell(x,y).prntGrid();
                for (int xl = 0; xl < 3; xl++)
                    for (int yl = 0; yl < 3; yl++)
                        if (grid[xl][yl])world.add(new Location(((2*x) + xl),((2*y) + yl)), new Wall());
            }
        world.add(new Location((startLoc[0] * 2)+1,(startLoc[1] * 2)), start);
        world.add(new Location((endLoc[0] * 2)+1,(endLoc[1] * 2)+2), end);
        return world;
    }
    public void print(){
        for (int y = 0; y < height; y++){
            System.out.print(" ");
            for (int x = 0; x < width;x++){
                System.out.print((char)8);
                System.out.print(maze[x][y].prntLine(1));
            }
            System.out.print("\n ");
            for (int x = 0; x < width;x++){
                System.out.print((char)8);
                System.out.print(maze[x][y].prntLine(2));
            }
            System.out.print("\n");
        }
        System.out.print(" ");
        for (int x = 0; x < width;x++){
            System.out.print((char)8);
            System.out.print(maze[x][height-1].prntLine(3));
        }
        System.out.print("\n");
    }
}