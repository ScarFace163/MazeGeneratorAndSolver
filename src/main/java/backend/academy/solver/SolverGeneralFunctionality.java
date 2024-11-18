package backend.academy.solver;

import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.List;

public class SolverGeneralFunctionality {
    @SuppressWarnings("checkstyle:MagicNumber")
    public static void updateMazeStats(Maze maze, int visitedCellsCount,
        int passagesCount, int optimalPathLength) {
        maze.visitedCellsCount(visitedCellsCount);
        maze.passageCellsCount(passagesCount);
        maze.percentageOfVisitedCells((int) ((double) visitedCellsCount / passagesCount * 100));
        maze.optimalPathLength(optimalPathLength);
    }

    public static List<Cell> getNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.y();
        int col = cell.x();

        if (row > 0) {
            neighbors.add(grid[row - 1][col]);
        }
        if (row < grid.length - 1) {
            neighbors.add(grid[row + 1][col]);
        }
        if (col > 0) {
            neighbors.add(grid[row][col - 1]);
        }
        if (col < grid[0].length - 1) {
            neighbors.add(grid[row][col + 1]);
        }

        return neighbors;
    }

}
