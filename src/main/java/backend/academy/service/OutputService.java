package backend.academy.service;

import backend.academy.model.Maze;

public interface OutputService {
    public void print(String text);
    public void println(String text);
    public void printMaze(Maze maze);
}
