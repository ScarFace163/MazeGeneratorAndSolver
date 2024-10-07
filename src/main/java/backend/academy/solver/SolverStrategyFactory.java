package backend.academy.solver;

import backend.academy.enums.SolverType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolverStrategyFactory {
    public static SolverStrategy getSolver(SolverType type) {
        switch (type){
            case SolverType.BFS:
                return new BFSSolverStrategy();
            case SolverType.ASTAR:
                return new AStarSolverStrategy();
            default:
                log.info("No such solver type");
        }
        return null;
    }
}
