package backend.academy.service;

import backend.academy.enums.CellType;
import backend.academy.model.Maze;

public class MazeService {
    public void setStartCell(Maze maze, int[] cords) {
        maze.grid()[cords[0]][cords[1]].type(CellType.PASSAGE);
        maze.start(maze.grid()[cords[0]][cords[1]]);
    }

    public void setEndCell(Maze maze, int[] cords) {
        maze.grid()[cords[0]][cords[1]].type(CellType.PASSAGE);
        maze.end(maze.grid()[cords[0]][cords[1]]);
    }
}
