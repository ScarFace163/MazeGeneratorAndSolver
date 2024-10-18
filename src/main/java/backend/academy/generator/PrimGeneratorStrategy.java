package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimGeneratorStrategy implements GeneratorStrategy {
    private final Random random = new Random();
    private static final int MAX_ATTEMPTS = 1000;

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        List<Cell> walls = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }
        int startX = random.nextInt(width / 2) * 2 + 1;
        int startY = random.nextInt(height / 2) * 2 + 1;
        grid[startY][startX].type(CellType.PASSAGE);
        addWalls(grid, walls, startX, startY);

        while (!walls.isEmpty()) {
            int index = random.nextInt(walls.size());
            Cell wall = walls.remove(index);

            if (canBePassage(grid, wall)) {
                wall.type(CellType.PASSAGE);
                addWalls(grid, walls, wall.x(), wall.y());
            }
        }

        return new Maze(height, width, grid);
    }

    private Cell getValidBoundaryCell(Cell[][] grid, int height, int width) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            int[] cell = getRandomBoundaryCell(height, width);
            int x = cell[0];
            int y = cell[1];
            if (hasAdjacentPassage(grid, x, y)) {
                grid[y][x].type(CellType.PASSAGE);
                return grid[y][x];
            }
            attempts++;
        }
        throw new RuntimeException("Failed to find a valid boundary cell after " + MAX_ATTEMPTS + " attempts");
    }

    private boolean hasAdjacentPassage(Cell[][] grid, int x, int y) {
        if (x > 0 && grid[y][x - 1].type() == CellType.PASSAGE) {
            return true;
        }
        if (x < grid[0].length - 1 && grid[y][x + 1].type() == CellType.PASSAGE) {
            return true;
        }
        if (y > 0 && grid[y - 1][x].type() == CellType.PASSAGE) {
            return true;
        }
        return y < grid.length - 1 && grid[y + 1][x].type() == CellType.PASSAGE;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private int[] getRandomBoundaryCell(int height, int width) {
        int side = random.nextInt(4);
        int x = 0;
        int y = 0;
        switch (side) {
            case 0 -> x = random.nextInt(width);
            case 1 -> {
                x = random.nextInt(width);
                y = height - 1;
            }
            case 2 -> y = random.nextInt(height);
            case 3 -> {
                x = width - 1;
                y = random.nextInt(height);
            }
            default -> throw new RuntimeException();
        }
        return new int[] {x, y};
    }

    private void addWalls(Cell[][] grid, List<Cell> walls, int x, int y) {
        if (x > 1 && grid[y][x - 2].type() == CellType.WALL && !walls.contains(grid[y][x - 1])) {
            walls.add(grid[y][x - 1]);
        }
        if (x < grid[0].length - 2 && grid[y][x + 2].type() == CellType.WALL && !walls.contains(grid[y][x + 1])) {
            walls.add(grid[y][x + 1]);
        }
        if (y > 1 && grid[y - 2][x].type() == CellType.WALL && !walls.contains(grid[y - 1][x])) {
            walls.add(grid[y - 1][x]);
        }
        if (y < grid.length - 2 && grid[y + 2][x].type() == CellType.WALL && !walls.contains(grid[y + 1][x])) {
            walls.add(grid[y + 1][x]);
        }
    }

    private boolean canBePassage(Cell[][] grid, Cell wall) {
        int x = wall.x();
        int y = wall.y();
        int passages = 0;

        if (x > 0 && grid[y][x - 1].type() == CellType.PASSAGE) {
            passages++;
        }
        if (x < grid[0].length - 1 && grid[y][x + 1].type() == CellType.PASSAGE) {
            passages++;
        }
        if (y > 0 && grid[y - 1][x].type() == CellType.PASSAGE) {
            passages++;
        }
        if (y < grid.length - 1 && grid[y + 1][x].type() == CellType.PASSAGE) {
            passages++;
        }

        return passages == 1;
    }
}
