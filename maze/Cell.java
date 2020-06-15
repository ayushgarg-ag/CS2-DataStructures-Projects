package cs2.maze;

public class Cell {
    public final int U = 0;
    public final int R = 1;
    public final int D = 2;
    public final int L = 3;
    private boolean visited; 	// This variable is only for maze generation
    private boolean[] walls;
    private Cell[] cells;		// Adjacent Cells References

    private int[] location;

    public Cell (int x, int y) {
        walls = new boolean[4];
        cells = new Cell[4];
        location = new int[2];
        location[0] = x;
        location[1] = y;
        walls[U] = true;
        walls[R] = true;
        walls[D] = true;
        walls[L] = true;
        visited = false;
    }

    public int[] getLoc(){
        return location;
    }

    public void setCells (Cell l,Cell r,Cell u,Cell d) {
        // Set the references for adjacent squares.
        cells[U] = u;
        cells[R] = r;
        cells[D] = d;
        cells[L] = l;
    }
    public Cell getCell(int w){
        return cells[w];
    }

    public boolean getVisited(){
        return visited;
    }

    public void setVisited(boolean b){
        visited = b;
    }

    public boolean getWall (int w) {
        return walls[w];
    }

    public void setWall (int w, boolean b) {
        // Set the wall status on edge "w" to "b"
        walls[w] = b;
        // Call the setWall function in adjacent square
        int l = w + 2;
        if (l > 3) l = l - 4;
        if (cells[w] != null)
            if (cells[w].getWall(l) != walls[w])
                cells[w].setWall(l,b);
    }

    public String toString(){
        return "U:" + walls[U] + ", L:" + walls[L] + ", R:" + walls[R] + ", D:" + walls[D];
    }

    public boolean[][] prntGrid(){
        boolean[][] grid = {{true,walls[D],true},
                {walls[R],false,walls[L]},
                {true,walls[U],true}};
        grid[1][0] = walls[U];
        grid[1][2] = walls[D];
        grid[0][1] = walls[L];
        grid[2][1] = walls[R];
        return grid;
    }

    public String prntLine(int l){
        String line = "";
        switch (l){
            case 1:
                line = (walls[U])?"+--+":"+  +"; // Top Wall
                break;
            case 2:
                line = ((walls[L])?"|  ":"   ") + ((walls[R])?"|":" "); // Left and right walls
                break;
            case 3:
                line = (walls[D])?"+--+":"+  +"; // Bottom Wall
                break;
        }
        return line;
    }
}