package backend.academy.service;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.enums.SolverStrategyType;
import backend.academy.model.Maze;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

@SuppressFBWarnings(value = {"PREDICTABLE_RANDOM", "CLI_CONSTANT_LIST_INDEX"})
public class InputService {
    private final Scanner sc;
    private final Random random;

    public GeneratorStrategyType inputGenerateStrategyType() {
        String input = sc.nextLine();
        if ("".equals(input)) {
            return GeneratorStrategyType.values()[random.nextInt(GeneratorStrategyType.values().length)];
        }
        try {
            int number = Integer.parseInt(input);
            if (number >= 0 && number < GeneratorStrategyType.values().length) {
                return GeneratorStrategyType.values()[number];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public InputService() {
        sc = new Scanner(System.in, StandardCharsets.UTF_8);
        random = new Random();
    }

    public SolverStrategyType inputSolverStrategyType() {
        String input = sc.nextLine();
        if ("".equals(input)) {
            return SolverStrategyType.values()[random.nextInt(SolverStrategyType.values().length)];
        }
        try {
            int number = Integer.parseInt(input);
            if (number >= 0 && number < SolverStrategyType.values().length) {
                return SolverStrategyType.values()[number];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public int[] inputMazeSize() {
        String[] cords = sc.nextLine().split(" ");
        int[] ans = new int[2];
        if (cords.length != 2) {
            return null;
        }
        try {
            ans[0] = Integer.parseInt(cords[0]);
            ans[1] = Integer.parseInt(cords[1]);
            if (ans[0] % 2 == 0 || ans[1] % 2 == 0) {
                return null;
            }
            return ans;
        } catch (Exception e) {
            return null;
        }
    }

    public int[] inputCell(Maze maze) {
        String[] cords = sc.nextLine().split(" ");
        int[] ans = new int[2];
        if (cords.length != 2) {
            return null;
        }
        try {
            ans[0] = Integer.parseInt(cords[0]);
            ans[1] = Integer.parseInt(cords[1]);
            if (ans[0] < 0 || ans[0] >= maze.height() || ans[1] < 0 || ans[1] >= maze.width()) {
                return null;
            }
            return ans;
        } catch (Exception e) {
            return null;
        }
    }
}
