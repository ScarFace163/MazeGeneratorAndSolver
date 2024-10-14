package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;

import java.util.Random;
import java.util.Stack;

public class RandomWalkGeneratorStrategy implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        if (height % 2 == 0) height++;
        if (width % 2 == 0) width++;

        Cell[][] grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }

        Stack<Cell> stack = new Stack<>();
        Cell start = new Cell(1, 1, CellType.PASSAGE);
        grid[1][1] = start;
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                stack.push(next);
                grid[next.y()][next.x()].type(CellType.PASSAGE);
                grid[(current.y() + next.y()) / 2][(current.x() + next.x()) / 2].type(CellType.PASSAGE);
            } else {
                stack.pop();
            }
        }

        Cell startCell = getRandomBorderCell(grid);
        Cell finishCell = getRandomBorderCell(grid);
        startCell.type(CellType.PASSAGE);
        finishCell.type(CellType.PASSAGE);

        Maze maze = new Maze(height, width, grid);
        maze.start(startCell);
        maze.end(finishCell);

        return maze;
    }

    private Cell getRandomNeighbor(Cell[][] grid, Cell cell) {
        int x = cell.x();
        int y = cell.y();
        Cell[] neighbors = new Cell[4];
        int count = 0;

        if (x > 1 && grid[y][x - 2].type() == CellType.WALL) neighbors[count++] = new Cell(x - 2, y, CellType.PASSAGE);
        if (x < grid[0].length - 2 && grid[y][x + 2].type() == CellType.WALL) neighbors[count++] = new Cell(x + 2, y, CellType.PASSAGE);
        if (y > 1 && grid[y - 2][x].type() == CellType.WALL) neighbors[count++] = new Cell(x, y - 2, CellType.PASSAGE);
        if (y < grid.length - 2 && grid[y + 2][x].type() == CellType.WALL) neighbors[count++] = new Cell(x, y + 2, CellType.PASSAGE);

        if (count == 0) return null;
        return neighbors[random.nextInt(count)];
    }

    private Cell getRandomBorderCell(Cell[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int side = random.nextInt(4);
        int x = 0, y = 0;

        switch (side) {
            case 0:
                x = random.nextInt(width);
                y = 0;
                break;
            case 1:
                x = random.nextInt(width);
                y = height - 1;
                break;
            case 2:
                x = 0;
                y = random.nextInt(height);
                break;
            case 3:
                x = width - 1;
                y = random.nextInt(height);
                break;
        }

        return grid[y][x];
    }
}
