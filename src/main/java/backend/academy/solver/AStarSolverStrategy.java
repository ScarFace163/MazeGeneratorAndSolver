package backend.academy.solver;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

@SuppressWarnings("PMD")
@SuppressFBWarnings(value = {"CNC_COLLECTION_NAMING_CONFUSION",
    "PSC_PRESIZE_COLLECTIONS", "EQ_COMPARETO_USE_OBJECT_EQUALS"})
public class AStarSolverStrategy implements SolverStrategy {
    private int optimalPathLength;

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

    private int heuristic(Cell a, Cell b) {
        return Math.abs(a.y() - b.y()) + Math.abs(a.x() - b.x());
    }

    private void markPath(Node endNode) {
        Node current = endNode;
        while (current != null) {
            current.cell.type(CellType.PATH);
            current = current.parent;
            optimalPathLength++;
        }
    }

    @Override
    public boolean solve(Maze maze) {
        Cell start = maze.start();
        Cell end = maze.end();

        PriorityQueue<Node> openList = new PriorityQueue<>();
        Set<Cell> closedList = new HashSet<>();

        openList.add(new Node(start, null, 0, heuristic(start, end)));
        int visitedCellsCount = 0;
        int passagesCount = countPassages(maze.grid());

        while (!openList.isEmpty()) {
            Node current = openList.poll();
            if (current.cell.equals(end)) {
                markPath(current);
                SolverGeneralFunctionality.updateMazeStats(maze, visitedCellsCount, passagesCount, optimalPathLength);
                return true;
            }

            closedList.add(current.cell);

            for (Cell neighbor : SolverGeneralFunctionality.getNeighbors(current.cell, maze.grid())) {
                if (closedList.contains(neighbor) || neighbor.type() != CellType.PASSAGE) {
                    continue;
                }

                int tentativeG = current.costFromStart + 1;
                Node neighborNode = new Node(neighbor, current, tentativeG, heuristic(neighbor, end));

                if (openList.contains(neighborNode) && tentativeG >= neighborNode.costFromStart) {
                    continue;
                }

                openList.add(neighborNode);
                neighbor.type(CellType.VISITED);
                visitedCellsCount++;
            }
        }
        SolverGeneralFunctionality.updateMazeStats(maze, visitedCellsCount, passagesCount, optimalPathLength);
        return false;
    }

    private static class Node implements Comparable<Node> {
        Cell cell;
        Node parent;
        int costFromStart;
        int heuristicCost;

        Node(Cell cell, Node parent, int g, int h) {
            this.cell = cell;
            this.parent = parent;
            this.costFromStart = g;
            this.heuristicCost = h;
        }

        int getF() {
            return costFromStart + heuristicCost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.getF(), other.getF());
        }
    }

}
