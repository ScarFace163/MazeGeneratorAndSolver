package backend.academy.model;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;

@Getter
@Setter
public class Maze {
    private int height;
    private int width;
    private Cell[][] grid;
    private Cell start;
    private Cell end;
    private int visitedCellsCount;
    private int passageCellsCount;
    private int percentageOfVisitedCells;
    private int optimalPathLength;

    public Maze(int height, int width, Cell[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    @Override
    public boolean equals(Object obj) {
        Maze maze = (Maze) obj;
        return Arrays.deepEquals(this.grid, maze.grid);
    }
}
