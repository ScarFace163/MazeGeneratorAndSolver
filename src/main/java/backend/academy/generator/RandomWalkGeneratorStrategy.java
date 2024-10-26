package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayDeque;
import java.util.Deque;
import static backend.academy.generator.RandomNeighbor.getRandomNeighbor;

@SuppressWarnings("PMD")
@SuppressFBWarnings(value = {"IM_AVERAGE_COMPUTATION_COULD_OVERFLOW", "CLI_CONSTANT_LIST_INDEX",
    "CNC_COLLECTION_NAMING_CONFUSION"})
public class RandomWalkGeneratorStrategy implements GeneratorStrategy {

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

        startAlgorithm(deque, grid);

        return new Maze(height, width, grid);
    }

    private void startAlgorithm(Deque<Cell> deque, Cell[][] grid) {
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
    }

}
