package backend.academy.serviceImpl;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import backend.academy.service.OutputService;

public class OutputServiceImpl implements OutputService {
    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void printMaze(Maze maze) {
        Cell[][] grid = maze.grid();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                switch (grid[y][x].type()) {
                    case WALL -> System.out.print("██");
                    case PASSAGE -> System.out.print("  ");
                    case VISITED -> System.out.print("••");
                    case PATH -> System.out.print("░░");
                }
            }
            System.out.println();
        }
    }

}
