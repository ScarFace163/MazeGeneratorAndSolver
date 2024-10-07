package backend.academy.solver;

import backend.academy.model.Maze;

public interface SolverStrategy {
    boolean solve(Maze maze);
}
