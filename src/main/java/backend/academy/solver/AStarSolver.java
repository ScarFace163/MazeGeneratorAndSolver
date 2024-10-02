package backend.academy.solver;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;

import java.util.*;

public class AStarSolver implements Solver {

    private static class Node implements Comparable<Node> {
        Cell cell;
        Node parent;
        int g; // Cost f
        int h; // Heuristic cost to the end

        Node(Cell cell, Node parent, int g, int h) {
            this.cell = cell;
            this.parent = parent;
            this.g = g;
            this.h = h;
        }

        int getF() {
            return g + h;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.getF(), other.getF());
        }
    }

    @Override
    public boolean solve(Maze maze) {
        Cell start = maze.grid()[1][0];
        Cell end = maze.grid()[maze.height() - 2][maze.width() - 2];

        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Cell> closedList = new HashSet<>();

        openList.add(new Node(start, null, 0, heuristic(start, end)));

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.cell.equals(end)) {
                markPath(current);
                return true;
            }

            closedList.add(current.cell);

            for (Cell neighbor : getNeighbors(current.cell, maze.grid())) {
                if (closedList.contains(neighbor) || neighbor.type() != CellType.PASSAGE) {
                    continue;
                }

                int tentativeG = current.g + 1;
                Node neighborNode = new Node(neighbor, current, tentativeG, heuristic(neighbor, end));

                if (openList.contains(neighborNode) && tentativeG >= neighborNode.g) {
                    continue;
                }

                openList.add(neighborNode);
                neighbor.type(CellType.VISITED);
            }
        }
        return false;
    }

    private int heuristic(Cell a, Cell b) {
        return Math.abs(a.y() - b.y()) + Math.abs(a.x() - b.x());
    }

    private void markPath(Node endNode) {
        Node current = endNode;
        while (current != null) {
            current.cell.type(CellType.PATH);
            current = current.parent;
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
