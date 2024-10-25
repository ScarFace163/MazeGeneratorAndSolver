package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

@SuppressFBWarnings(value = {"PREDICTABLE_RANDOM", "IM_AVERAGE_COMPUTATION_COULD_OVERFLOW",
    "CLI_CONSTANT_LIST_INDEX", "CNC_COLLECTION_NAMING_CONFUSION"})
public class RandomWalkGeneratorStrategy implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }

        Deque<Cell> deque = new ArrayDeque<>();
        Cell start = new Cell(1, 1, CellType.PASSAGE);
        grid[1][1] = start;
        deque.push(start);

        while (!deque.isEmpty()) {
            Cell current = deque.peek();
            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                deque.push(next);
                grid[next.y()][next.x()].type(CellType.PASSAGE);
                grid[(current.y() + next.y()) / 2][(current.x() + next.x()) / 2].type(CellType.PASSAGE);
            } else {
                deque.pop();
            }
        }

        return new Maze(height, width, grid);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private Cell getRandomNeighbor(Cell[][] grid, Cell cell) {
        int x = cell.x();
        int y = cell.y();
        Cell[] neighbors = new Cell[4];
        int count = 0;

        if (x > 1 && grid[y][x - 2].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x - 2, y, CellType.PASSAGE);
        }
        if (x < grid[0].length - 2 && grid[y][x + 2].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x + 2, y, CellType.PASSAGE);
        }
        if (y > 1 && grid[y - 2][x].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x, y - 2, CellType.PASSAGE);
        }
        if (y < grid.length - 2 && grid[y + 2][x].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x, y + 2, CellType.PASSAGE);
        }

        if (count == 0) {
            return null;
        }
        return neighbors[random.nextInt(count)];
    }

}
