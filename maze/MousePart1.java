package cs2.maze;

import cs2.stack.CS2Stack;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class MousePart1 extends Actor {
    private CS2Stack<Location> stack;
    private boolean foundCheese;

    public MousePart1() {
        stack = new CS2Stack<Location>();
        foundCheese = false;
        setColor(new Color(255, 165, 0));
    }

    @Override
    public void act() {
        if (foundCheese == false) {
            Grid gr = this.getGrid();
            Location mouseLoc = this.getLocation();

            Location cheese = findCheese();
            if (cheese != null) {
                //            gr.remove(cheese);
                this.setDirection(mouseLoc.getDirectionToward(cheese));
                this.moveTo(cheese);
                gr.put(mouseLoc, new Breadcrumb());
                foundCheese = true;
                return;
            } else {
                ArrayList<Location> locations = this.getLocs();
                //            CS2Stack<Location> stack = new CS2Stack<Location>();
                for (Location loc : locations) {
                    stack.push(loc);
                }
            }
            Location popped = stack.pop();
            this.setDirection(mouseLoc.getDirectionToward(popped));
            this.moveTo(popped);
            gr.put(mouseLoc, new Breadcrumb());
        }
    }

    private ArrayList<Location> getLocs() {
        ArrayList<Location> cardinal = new ArrayList<Location>();
        Grid gr = this.getGrid();
        Location loc = this.getLocation();
        for (int i = 0; i < 360; i+=90) {
            Location card = loc.getAdjacentLocation(i);
            if (gr.isValid(card) && (gr.get(card) == null) && !(gr.get(card) instanceof Breadcrumb)) {
                cardinal.add(card);
            }
        }
        return cardinal;
    }

    private Location findCheese() {
        Grid gr = this.getGrid();
        Location loc = this.getLocation();
        for (int i = 0; i <= 360; i+=90) {
            Location card = loc.getAdjacentLocation(i);
            if (gr.isValid(card) && (gr.get(card) instanceof Cheese)) {
                return card;
            }
        }
        return null;
    }

}
