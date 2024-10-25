package backend.academy;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import backend.academy.service.MazeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MazeServiceTest {
    MazeService mazeService;
    Cell[][] grid;

    @BeforeEach
    public void setUp() {
        mazeService = new MazeService();
        grid = new Cell[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                grid[i][j] = new Cell(j,i,CellType.WALL);
            }
        }
    }

    @Test
    public void setStartCellTest() {
        Maze maze = new Maze(5, 5, grid);
        mazeService.setStartCell(maze, new int[] {0, 0});
        assertEquals(maze.grid()[0][0].type(), CellType.PASSAGE);
        assertEquals(maze.start(), maze.grid()[0][0]);
    }

    @Test
    public void setEndCellTest() {
        Maze maze = new Maze(5, 5, grid);
        mazeService.setEndCell(maze, new int[] {4, 4});
        assertEquals(maze.grid()[4][4].type(), CellType.PASSAGE);
        assertEquals(maze.end(), maze.grid()[4][4]);
    }
}
