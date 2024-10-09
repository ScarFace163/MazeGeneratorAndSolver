package backend.academy.solver;

import backend.academy.enums.SolverStrategyType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolverStrategyFactory {
    public static SolverStrategy getSolver(SolverStrategyType type) {
        switch (type){
            case SolverStrategyType.BFS:
                return new BFSSolverStrategy();
            case SolverStrategyType.ASTAR:
                return new AStarSolverStrategy();
            default:
                log.info("No such solver type");
        }
        return null;
    }
}
