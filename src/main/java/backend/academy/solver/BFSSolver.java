package backend.academy.solver;

import backend.academy.enums.CellType;
import backend.academy.model.Maze;
import backend.academy.model.Cell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSSolver implements Solver {
    public BFSSolver() {

    }

    @Override
    public boolean solve(Maze maze) {
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        Cell start = grid[1][0];
        Cell end = grid[height - 2][width - 1];
        boolean[][] visited = new boolean[height][width];
        Cell[][] parent = new Cell[height][width];

        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        visited[start.y()][start.x()] = true;

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.equals(end)) {
                markPath(parent, current);
                return true;
            }

            for (Cell neighbor : getNeighbors(current, grid)) {
                if (!visited[neighbor.y()][neighbor.x()] && neighbor.type() == CellType.PASSAGE) {
                    queue.add(neighbor);
                    visited[neighbor.y()][neighbor.x()] = true;
                    neighbor.type(CellType.VISITED);
                    parent[neighbor.y()][neighbor.x()] = current;
                }
            }
        }
        return false;
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
