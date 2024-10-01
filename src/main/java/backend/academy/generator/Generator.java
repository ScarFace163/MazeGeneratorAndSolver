package backend.academy.generator;

import backend.academy.model.Maze;

public interface Generator {
    Maze generate(int height, int width);
}
