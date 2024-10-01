package backend.academy.solver;

import backend.academy.enums.SolverType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SolverFactory {
    public static Solver getSolver(SolverType type) {
        switch (type){
            case SolverType.BFS:
                return new BFSSolver();
            default:
                log.info("No such solver type");
        }
        return null;
    }
}
