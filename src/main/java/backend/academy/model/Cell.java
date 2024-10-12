package backend.academy.model;

import backend.academy.enums.CellType;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter public class Cell {
    private final int y;
    private final int x;
    private final List<Cell> neighbors;
    @Setter private CellType type;

    public Cell(int x, int y, CellType type) {
        this.y = y;
        this.x = x;
        this.type = type;
        this.neighbors = new ArrayList<>();
    }

}
