package backend.academy.solver;

import backend.academy.enums.SolverStrategyType;

public class SolverStrategyFactory {
    public static SolverStrategy getSolver(SolverStrategyType type) {
        return switch (type) {
            case SolverStrategyType.BFS -> new BFSSolverStrategy();
            case SolverStrategyType.ASTAR -> new AStarSolverStrategy();
        };
    }

    private SolverStrategyFactory() {}
}
