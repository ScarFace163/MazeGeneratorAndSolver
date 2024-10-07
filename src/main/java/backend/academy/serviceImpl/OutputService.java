package backend.academy.serviceImpl;

import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputService {
    private BufferedWriter writer;

    public OutputService(){
        // Инициализация BufferedWriter для записи в указанный файл
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void print(String text) {
        try {
            writer.write(text); // Запись текста в файл
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void println(String text) {
        try {
            writer.write(text); // Запись текста в файл
            writer.newLine(); // Добавление новой строки
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }

    // Метод для закрытия BufferedWriter
    public void close() {
        try {
            writer.close(); // Закрытие BufferedWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
