package backend.academy.solver;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.LinkedList;
import java.util.Queue;

@SuppressWarnings("PMD")
public class BFSSolverStrategy implements SolverStrategy {
    int optimalPathLength;

    @Override
    public boolean solve(Maze maze) {
        int height = maze.height();
        int width = maze.width();
        Cell[][] grid = maze.grid();
        boolean[][] visited = new boolean[height][width];
        Cell[][] parent = new Cell[height][width];

        Cell start = maze.start();
        Cell end = maze.end();

        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        visited[start.y()][start.x()] = true;
        int visitedCellsCount = 1;
        int passagesCount = countPassages(grid);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            if (current.equals(end)) {
                markPath(parent, current);
                SolverGeneralFunctionality.updateMazeStats(maze, visitedCellsCount, passagesCount, optimalPathLength);
                return true;
            }

            for (Cell neighbor : SolverGeneralFunctionality.getNeighbors(current, grid)) {
                if (!visited[neighbor.y()][neighbor.x()] && neighbor.type() == CellType.PASSAGE) {
                    queue.add(neighbor);
                    visited[neighbor.y()][neighbor.x()] = true;
                    neighbor.type(CellType.VISITED);
                    parent[neighbor.y()][neighbor.x()] = current;
                    visitedCellsCount++;
                }
            }
        }
        SolverGeneralFunctionality.updateMazeStats(maze, visitedCellsCount, passagesCount, optimalPathLength);
        return false;
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
            optimalPathLength++;
        }
    }

}
