package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static backend.academy.generator.RandomNeighbor.getRandomNeighbor;

@SuppressWarnings("PMD")
@SuppressFBWarnings(value = {"PREDICTABLE_RANDOM", "IM_AVERAGE_COMPUTATION_COULD_OVERFLOW"})
public class GrowingTreeGeneratorStrategy implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        int startInd = 1;
        // Инициализация сетки как стен
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }

        List<Cell> cells = new ArrayList<>();
        Cell start = new Cell(1, 1, CellType.PASSAGE);
        if (height > 1 && width > 1) {
            grid[startInd][startInd] = start;
        } else {
            throw new IllegalArgumentException("Height and width must be greater than 1");
        }
        cells.add(start);

        while (!cells.isEmpty()) {
            Cell current;
            if (random.nextBoolean()) {
                current = cells.getLast();
            } else {
                current = cells.get(random.nextInt(cells.size()));
            }

            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                grid[next.y()][next.x()].type(CellType.PASSAGE);
                grid[(current.y() + next.y()) / 2][(current.x() + next.x()) / 2].type(CellType.PASSAGE);
                cells.add(next);
            } else {
                cells.remove(current);
            }
        }
        return new Maze(height, width, grid);
    }

}
