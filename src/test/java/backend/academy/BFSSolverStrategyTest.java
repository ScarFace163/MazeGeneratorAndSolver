package backend.academy;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import backend.academy.solver.BFSSolverStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BFSSolverStrategyTest {
    private BFSSolverStrategy solver;
    private Cell[][] grid;

    @BeforeEach
    public void setUp() {
        solver = new BFSSolverStrategy();
        grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Cell(j, i, CellType.WALL);
            }
        }
    }

    @Test
    public void testSolveWithPath() {
        grid[0][0] = new Cell(0, 0, CellType.PASSAGE);
        grid[4][4] = new Cell(4, 4, CellType.PASSAGE);
        grid[0][1] = new Cell(1, 0, CellType.PASSAGE);
        grid[1][1] = new Cell(1, 1, CellType.PASSAGE);
        grid[2][1] = new Cell(1, 2, CellType.PASSAGE);
        grid[3][1] = new Cell(1, 3, CellType.PASSAGE);
        grid[4][1] = new Cell(1, 4, CellType.PASSAGE);
        grid[4][2] = new Cell(2, 4, CellType.PASSAGE);
        grid[4][3] = new Cell(3, 4, CellType.PASSAGE);

        Maze maze = new Maze(5, 5, grid);
        maze.start(grid[0][0]);
        maze.end(grid[4][4]);

        assertTrue(solver.solve(maze));
    }

    @Test
    public void testSolveWithoutPath() {
        grid[0][0] = new Cell(0, 0, CellType.PASSAGE);
        grid[4][4] = new Cell(4, 4, CellType.PASSAGE);

        Maze maze = new Maze(5, 5, grid);
        maze.start(grid[0][0]);
        maze.end(grid[4][4]);

        assertFalse(solver.solve(maze));
    }
}
