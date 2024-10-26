package backend.academy.service;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.enums.SolverStrategyType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;

public class OutputService {

    @SuppressWarnings("checkstyle:RegexpSinglelineJava") public void print(String text) {
        System.out.print(text);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava") public void println(String text) {
        System.out.println(text);
    }

    public void printMaze(Maze maze) {
        Cell[][] grid = maze.grid();
        for (Cell[] cells : grid) {
            for (Cell cell : cells) {
                switch (cell.type()) {
                    case WALL -> print("██");
                    case PASSAGE -> print("  ");
                    case VISITED -> print("••");
                    case PATH -> print("░░");
                    default -> throw new RuntimeException();
                }
            }
            print("\n"); // Запись новой строки
        }
        println("Passage cells : " + maze.passageCellsCount());
        println("Visited cells : " + maze.visitedCellsCount());
        println("Percentage of visited cells : " + maze.percentageOfVisitedCells());
        println("Optimal path length : " + maze.optimalPathLength());
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public void printMazeForChooseCell(Maze maze) {
        Cell[][] grid = maze.grid();
        print("  ");
        for (int i = 0; i < maze.width(); i++) {
            print(i < 10 ? i + " " : String.valueOf(i));
        }
        print("\n");
        for (int i = 0; i < grid.length; i++) {
            print(i < 10 ? i + " " : String.valueOf(i));
            for (Cell cell : grid[i]) {
                switch (cell.type()) {
                    case WALL -> print("██");
                    case PASSAGE -> print("  ");
                    case VISITED -> print("••");
                    case PATH -> print("░░");
                    default -> throw new RuntimeException();
                }
            }
            print("\n");
        }
    }

    public void printChooseGeneratorTypeMenu() {
        println("Select the algorithm by which the maze will be generated."
            + " Type nothing if you want the algorithm to be selected randomly");
        for (GeneratorStrategyType type : GeneratorStrategyType.values()) {
            printType(String.valueOf(type), type.ordinal());
        }
    }

    public void printChooseSolverTypeMenu() {
        println("Select the algorithm by which the maze will be solved. "
            + "Type nothing if you want the algorithm to be selected randomly");
        for (SolverStrategyType type : SolverStrategyType.values()) {
            printType(String.valueOf(type), type.ordinal());
        }
    }

    public void printTryAgain() {
        println("Incorrect. Try again");
    }

    public void printSelectedStrategies(
        GeneratorStrategyType generatorStrategyType, SolverStrategyType solverStrategyType
    ) {
        println("Chosen generator type: " + generatorStrategyType);
        println("Chosen solver type: " + solverStrategyType);
    }

    public void printChooseMazeSize() {
        println("Type the size of Maze in format \"a b\" \nBoth numbers must be odd");
    }

    private void printType(String type, int number) {
        println(number + " - " + type);
    }

    public void printChooseStartCell() {
        println("Type the coordinates of start cell of Maze in format \"row column\"");
    }

    public void printChooseEndCell() {
        println("Type the coordinates of end cell of Maze in format \"row column\"");
    }

}
