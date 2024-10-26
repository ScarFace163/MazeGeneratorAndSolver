package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.Random;

public class RandomNeighbor {
    @SuppressFBWarnings(value = {"PREDICTABLE_RANDOM"})
    @SuppressWarnings({"checkstyle:MagicNumber"})
    public static Cell getRandomNeighbor(Cell[][] grid, Cell cell) {
        Random random = new Random();
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

    private RandomNeighbor() {
    }
}
