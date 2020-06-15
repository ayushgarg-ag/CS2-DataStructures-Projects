package cs2.maze;

import cs2.stack.CS2Stack;
import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.*;
import java.util.ArrayList;

public class Mouse extends Actor {
    private CS2Stack<Location> stack;
    private CS2Stack<Location> prevPath;
    private boolean foundCheese;
    private boolean pathEntered;
    private boolean end;

    public Mouse() {
        stack = new CS2Stack<Location>();
        prevPath = new CS2Stack<Location>();
        foundCheese = false;
        pathEntered = false;
        end = false;
        setColor(new Color(255, 165, 0));
    }

    @Override
    public void act() {
        if (foundCheese == false) {
            Grid gr = this.getGrid();
            Location mouseLoc = this.getLocation();

            Location cheese = findCheese();
            if (cheese != null) {
                this.setDirection(mouseLoc.getDirectionToward(cheese));
                this.moveTo(cheese);
                gr.put(mouseLoc, new Breadcrumb());
                foundCheese = true;
                return;
            }

            if (pathEntered == false && stack.size() == 0) {
                ArrayList<Location> locations = this.getLocs();
                for (Location loc : locations) {
                    stack.push(loc);
                }
            }
                Location popped;
            if (pathEntered == false) {
                if (stack.size() == 1) {
                    popped = stack.pop();
                    this.setDirection(mouseLoc.getDirectionToward(popped));
                    this.moveTo(popped);
                    pathEntered = false;
                }
                else {
                    prevPath.push(mouseLoc);
                    popped = stack.pop();
                    prevPath.push(popped);
                    this.setDirection(mouseLoc.getDirectionToward(popped));
                    this.moveTo(popped);
                    pathEntered = true;
                }
            }
            else {
                inThePath();
                if (prevPath.size() == 0) {
                    pathEntered = false;
                }
            }
            gr.put(mouseLoc, new Breadcrumb());
        }
    }

    private void inThePath () {
        ArrayList<Location> locs = this.getLocs();
        if (locs.size() > 0) {
            if (locs.size() > 1) {
                prevPath.push(this.getLocation());
            }
            prevPath.push(locs.get(0));
            Location prevPathPopped = prevPath.pop();
            this.setDirection(this.getLocation().getDirectionToward(prevPathPopped));
            prevPath.push(prevPathPopped);
            this.moveTo(prevPathPopped);
            end = true;
        }
        else {
            if(end) {
                prevPath.pop();
            }
            end = false;
            Location prevPathPopped = prevPath.pop();
            this.setDirection(this.getLocation().getDirectionToward(prevPathPopped));
            this.getGrid().remove(prevPathPopped);
            this.moveTo(prevPathPopped);

        }
    }

    private ArrayList<Location> getLocs() {
        ArrayList<Location> cardinal = new ArrayList<Location>();
        Grid gr = this.getGrid();
        Location loc = this.getLocation();
        for (int i = 0; i < 360; i+=90) {
            Location card = loc.getAdjacentLocation(i);
            if (gr.isValid(card) && (gr.get(card) == null)) {
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
