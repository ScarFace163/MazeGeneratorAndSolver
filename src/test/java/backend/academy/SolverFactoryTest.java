package backend.academy;

import backend.academy.enums.SolverStrategyType;
import backend.academy.solver.AStarSolverStrategy;
import backend.academy.solver.BFSSolverStrategy;
import backend.academy.solver.SolverStrategy;
import backend.academy.solver.SolverStrategyFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SolverFactoryTest {
    @Test
    public void testSolverFactoryReturnRightObject(){
        SolverStrategy solver = SolverStrategyFactory.getSolver(SolverStrategyType.BFS);
        assertSame(solver.getClass(), BFSSolverStrategy.class);
        solver = SolverStrategyFactory.getSolver(SolverStrategyType.ASTAR);
        assertSame(solver.getClass(), AStarSolverStrategy.class);
    }
}
