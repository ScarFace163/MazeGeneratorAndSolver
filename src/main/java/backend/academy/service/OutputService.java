package backend.academy.service;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.enums.SolverStrategyType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;

public class OutputService {

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public void printMaze(Maze maze) {
        Cell[][] grid = maze.grid();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                switch (grid[y][x].type()) {
                    case WALL -> print("██");
                    case PASSAGE -> print("  ");
                    case VISITED -> print("••");
                    case PATH -> print("░░");
                }
            }
            print("\n"); // Запись новой строки
        }
        println("Passage cells : " + maze.passageCellsCount());
        println("Visited cells : " + maze.visitedCellsCount());
        println("Percentage of visited cells : " + maze.percentageOfVisitedCells());
        println("Optimal path length : " + maze.optimalPathLength());
    }
    public void printChooseGeneratorTypeMenu(){
        println("Select the algorithm by which the maze will be generated. Type nothing if you want the algorithm to be selected randomly");
        for (GeneratorStrategyType type : GeneratorStrategyType.values()){
            println(type.ordinal() + " - " + type);
        }
    }
    public void printChooseSolverTypeMenu(){
        println("Select the algorithm by which the maze will be solved. Type nothing if you want the algorithm to be selected randomly");
        for (SolverStrategyType type : SolverStrategyType.values()){
            println(type.ordinal() + " - " + type);
        }
    }

    public void printTryAgain(){
        println("Incorrect. Try again");
    }
    public void printSelectedStrategies(GeneratorStrategyType generatorStrategyType, SolverStrategyType solverStrategyType){
        println("Chosen generator type: "+generatorStrategyType);
        println("Chosen solver type: "+solverStrategyType);
    }

    public void printChooseMazeSize() {
        println("Type the size of Maze in format \"a b\" \nBoth numbers must be odd");
    }
}
