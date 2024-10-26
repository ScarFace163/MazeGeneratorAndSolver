package backend.academy;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.enums.SolverStrategyType;
import backend.academy.generator.GeneratorStrategy;
import backend.academy.generator.GeneratorStrategyFactory;
import backend.academy.model.Maze;
import backend.academy.service.MainService;
import backend.academy.solver.SolverStrategy;
import backend.academy.solver.SolverStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolversTest {
    Maze maze;
    MainService mainService;

    @BeforeEach
    public void setUp() {
        mainService = new MainService();
        GeneratorStrategy generator = GeneratorStrategyFactory.getGenerator(GeneratorStrategyType.KRUSKAL);
        maze = generator.generate(21, 21);
    }

    @Test
    public void testSolvers() {
        Maze maze1 = maze;
        Maze maze2 = maze;

        maze1.start(maze1.grid()[1][0]);
        maze1.end(maze1.grid()[19][20]);

        maze2.start(maze1.grid()[1][0]);
        maze2.end(maze1.grid()[19][20]);

        SolverStrategy bfsSolver = SolverStrategyFactory.getSolver(SolverStrategyType.BFS);
        SolverStrategy aStarSolver = SolverStrategyFactory.getSolver(SolverStrategyType.ASTAR);

        bfsSolver.solve(maze1);
        aStarSolver.solve(maze2);

        assertEquals(maze1.optimalPathLength(), maze2.optimalPathLength());


    }

}
