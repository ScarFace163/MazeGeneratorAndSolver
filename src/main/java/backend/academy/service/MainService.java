package backend.academy.service;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.enums.SolverStrategyType;
import backend.academy.generator.GeneratorStrategy;
import backend.academy.generator.GeneratorStrategyFactory;
import backend.academy.model.Maze;
import backend.academy.solver.SolverStrategy;
import backend.academy.solver.SolverStrategyFactory;

public class MainService {
    InputService inputService;
    OutputService outputService;
    public void start(){
        outputService.printChooseGeneratorTypeMenu();
        GeneratorStrategyType generatorStrategyType = inputService.inputGenerateStrategyType();
        while (generatorStrategyType == null){
            outputService.printTryAgain();
            outputService.printChooseGeneratorTypeMenu();
            generatorStrategyType = inputService.inputGenerateStrategyType();
        }

        outputService.printChooseSolverTypeMenu();
        SolverStrategyType solverStrategyType = inputService.inputSolverStrategyType();
        while (solverStrategyType == null){
            outputService.printTryAgain();
            outputService.printChooseSolverTypeMenu();
            solverStrategyType = inputService.inputSolverStrategyType();
        }
        outputService.printSelectedStrategies(generatorStrategyType, solverStrategyType);
        GeneratorStrategy generator = GeneratorStrategyFactory.getGenerator(generatorStrategyType);
        SolverStrategy solver = SolverStrategyFactory.getSolver(solverStrategyType);
        outputService.printChooseMazeSize();
        int[] size = inputService.inputMazeSize();
        while (size == null){
            outputService.printTryAgain();
            outputService.printChooseMazeSize();
            size = inputService.inputMazeSize();
        }
        Maze maze = generator.generate(size[0],size[1]);
        solver.solve(maze);
        outputService.printMaze(maze);
    }

    public MainService() {
        inputService = new InputService();
        outputService = new OutputService();
    }
}
