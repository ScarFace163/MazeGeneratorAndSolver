package backend.academy.generator;

import backend.academy.model.Maze;

public interface GeneratorStrategy {
    Maze generate(int height, int width);
}
