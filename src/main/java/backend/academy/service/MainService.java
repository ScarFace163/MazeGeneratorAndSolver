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
    MazeService mazeService;

    public void start() {
        outputService.printChooseGeneratorTypeMenu();
        GeneratorStrategyType generatorStrategyType = inputService.inputGenerateStrategyType();
        while (generatorStrategyType == null) {
            outputService.printTryAgain();
            outputService.printChooseGeneratorTypeMenu();
            generatorStrategyType = inputService.inputGenerateStrategyType();
        }

        outputService.printChooseSolverTypeMenu();
        SolverStrategyType solverStrategyType = inputService.inputSolverStrategyType();
        while (solverStrategyType == null) {
            outputService.printTryAgain();
            outputService.printChooseSolverTypeMenu();
            solverStrategyType = inputService.inputSolverStrategyType();
        }
        GeneratorStrategy generator = GeneratorStrategyFactory.getGenerator(generatorStrategyType);
        SolverStrategy solver = SolverStrategyFactory.getSolver(solverStrategyType);
        outputService.printSelectedStrategies(generatorStrategyType, solverStrategyType);
        outputService.printChooseMazeSize();
        int[] size = inputService.inputMazeSize();
        while (size == null) {
            outputService.printTryAgain();
            outputService.printChooseMazeSize();
            size = inputService.inputMazeSize();
        }
        Maze maze = generator.generate(size[0], size[1]);
        outputService.printMazeForChooseCell(maze);
        outputService.printChooseStartCell();
        int[] start = inputService.inputCell();
        while (start == null) {
            outputService.printTryAgain();
            outputService.printChooseStartCell();
            start = inputService.inputCell();
        }
        mazeService.setStartCell(maze, start);
        outputService.printChooseEndCell();
        int[] end = inputService.inputCell();
        while (end == null) {
            outputService.printTryAgain();
            outputService.printChooseEndCell();
            end = inputService.inputCell();
        }
        mazeService.setEndCell(maze, end);
        if (solver.solve(maze)){
            outputService.println("Solution found");
            outputService.printMaze(maze);
        }
        else{
            outputService.println("Solution not found");
            outputService.printMaze(maze);
        }
    }

    public MainService() {
        inputService = new InputService();
        outputService = new OutputService();
        mazeService = new MazeService();
    }
}
