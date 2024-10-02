package backend.academy.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Maze {
    private final int height;
    private final int width;
    private final Cell[][] grid;
    @Setter private int visitedCellsCount;
    @Setter private int percentageOfVisitedCells;
    @Setter private int passageCellsCount;

    public Maze(
        int height,
        int width,
        Cell[][] grid
    ) {
        this.height = height;
        this.width = width;
        this.grid = grid;
        this.visitedCellsCount = 0;
        this.percentageOfVisitedCells = 0;
        this.passageCellsCount = 0;
    }
}
