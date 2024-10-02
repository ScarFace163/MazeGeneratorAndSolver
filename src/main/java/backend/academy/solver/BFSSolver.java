package backend.academy.solver;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;

import java.util.*;

public class BFSSolver implements Solver {
    private int visitedCellsCount;
    private int passagesCount;

    @Override
    public boolean solve(Maze maze) {
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        boolean[][] visited = new boolean[height][width];
        Cell[][] parent = new Cell[height][width];

        Queue<Cell> queue = new LinkedList<>();
        queue.add(grid[1][0]);
        visited[1][0] = true;
        visitedCellsCount = 1;
        passagesCount = countPassages(grid);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.equals(grid[height - 2][width - 2])) {
                markPath(parent, current);
                updateMazeStats(maze);
                return true;
            }

            for (Cell neighbor : getNeighbors(current, grid)) {
                if (!visited[neighbor.y()][neighbor.x()] && neighbor.type() == CellType.PASSAGE) {
                    queue.add(neighbor);
                    visited[neighbor.y()][neighbor.x()] = true;
                    neighbor.type(CellType.VISITED);
                    parent[neighbor.y()][neighbor.x()] = current;
                    visitedCellsCount++;
                }
            }
        }
        updateMazeStats(maze);
        return false;
    }

    private void updateMazeStats(Maze maze) {
        maze.visitedCellsCount(visitedCellsCount);
        maze.passageCellsCount(passagesCount);
        maze.percentageOfVisitedCells((int) ((double) visitedCellsCount / passagesCount * 100));
    }

    private int countPassages(Cell[][] grid) {
        int count = 0;
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.type() == CellType.PASSAGE) {
                    count++;
                }
            }
        }
        return count;
    }

    private void markPath(Cell[][] parent, Cell end) {
        Cell current = end;
        while (current != null) {
            current.type(CellType.PATH);
            current = parent[current.y()][current.x()];
        }
    }

    private List<Cell> getNeighbors(Cell cell, Cell[][] grid) {
        List<Cell> neighbors = new ArrayList<>();
        int row = cell.y();
        int col = cell.x();

        if (row > 0) neighbors.add(grid[row - 1][col]);
        if (row < grid.length - 1) neighbors.add(grid[row + 1][col]);
        if (col > 0) neighbors.add(grid[row][col - 1]);
        if (col < grid[0].length - 1) neighbors.add(grid[row][col + 1]);

        return neighbors;
    }
}
