package backend.academy;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeTest {
    private Cell[][] grid;

    @BeforeEach
    public void setUp() {
        grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Cell(j, i, CellType.WALL);
            }
        }
    }

    @Test
    public void testMazeInitialization() {
        Maze maze = new Maze(5, 5, grid);
        assertEquals(5, maze.height());
        assertEquals(5, maze.width());
        assertEquals(grid, maze.grid());
    }

    @Test
    public void testSetStart() {
        Maze maze = new Maze(5, 5, grid);
        Cell start = new Cell(0, 0, CellType.PASSAGE);
        maze.start(start);
        assertEquals(start, maze.start());
    }

    @Test
    public void testSetEnd() {
        Maze maze = new Maze(5, 5, grid);
        Cell end = new Cell(4, 4, CellType.PASSAGE);
        maze.end(end);
        assertEquals(end, maze.end());
    }
}
