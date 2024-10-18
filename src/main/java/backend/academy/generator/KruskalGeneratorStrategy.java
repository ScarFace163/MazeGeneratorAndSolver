package backend.academy.generator;

import backend.academy.enums.CellType;
import backend.academy.model.Cell;
import backend.academy.model.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class KruskalGeneratorStrategy implements GeneratorStrategy {
    private static final Random RANDOM = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        List<Edge> edges = new ArrayList<>();
        UnionFind uf = new UnionFind(width * height);
        initializeMaze(height, width, grid);
        initializeEdges(height, width, edges);
        generateMaze(edges, uf, width, grid);

        return new Maze(height, width, grid);
    }

    private void initializeMaze(int height, int width, Cell[][] maze) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                maze[y][x] = new Cell(x, y, CellType.WALL); // Стена
            }
        }
    }

    private void initializeEdges(int height, int width, List<Edge> edges) {
        for (int y = 1; y < height; y += 2) {
            for (int x = 1; x < width; x += 2) {
                if (x + 2 < width) {
                    edges.add(new Edge(y * width + x, y * width + (x + 2)));
                }
                if (y + 2 < height) {
                    edges.add(new Edge(y * width + x, (y + 2) * width + x));
                }
            }
        }
        Collections.shuffle(edges);
    }

    public void generateMaze(List<Edge> edges, UnionFind uf, int width, Cell[][] maze) {
        for (Edge edge : edges) {
            int cell1 = edge.cell1;
            int cell2 = edge.cell2;
            if (uf.find(cell1) != uf.find(cell2)) {
                uf.union(cell1, cell2);
                int x1 = cell1 % width;
                int y1 = cell1 / width;
                int x2 = cell2 % width;
                int y2 = cell2 / width;
                maze[y1][x1].type(CellType.PASSAGE);
                maze[y2][x2].type(CellType.PASSAGE);
                maze[(y1 + y2) / 2][(x1 + x2) / 2].type(CellType.PASSAGE);
            }
        }
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private int[] getRandomBoundaryCell(int height, int width) {
        int side = RANDOM.nextInt(4);
        int x = 0;
        int y = 0;
        switch (side) {
            case 0: // Top
                x = RANDOM.nextInt(width);
                break;
            case 1: // Bottom
                x = RANDOM.nextInt(width);
                y = height - 1;
                break;
            case 2: // Left
                y = RANDOM.nextInt(height);
                break;
            case 3: // Right
                x = width - 1;
                y = RANDOM.nextInt(height);
                break;
            default:
                throw new RuntimeException();
        }
        return new int[] {x, y};
    }

    private Cell getValidBoundaryCell(Cell[][] grid, int height, int width) {
        int[] cell;
        while (true) {
            cell = getRandomBoundaryCell(height, width);
            int x = cell[0];
            int y = cell[1];
            if (hasAdjacentPassage(grid, x, y)) {
                grid[y][x].type(CellType.PASSAGE);
                return grid[y][x];
            }
        }
    }

    private boolean hasAdjacentPassage(Cell[][] grid, int x, int y) {
        if (x > 0 && grid[y][x - 1].type() == CellType.PASSAGE) {
            return true;
        }
        if (x < grid[0].length - 1 && grid[y][x + 1].type() == CellType.PASSAGE) {
            return true;
        }
        if (y > 0 && grid[y - 1][x].type() == CellType.PASSAGE) {
            return true;
        }
        return y < grid.length - 1 && grid[y + 1][x].type() == CellType.PASSAGE;
    }

    private static class Edge {
        int cell1;
        int cell2;

        Edge(int cell1, int cell2) {
            this.cell1 = cell1;
            this.cell2 = cell2;
        }
    }

    private static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int p) {
            if (parent[p] != p) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }

        void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP != rootQ) {
                if (rank[rootP] > rank[rootQ]) {
                    parent[rootQ] = rootP;
                } else if (rank[rootP] < rank[rootQ]) {
                    parent[rootP] = rootQ;
                } else {
                    parent[rootQ] = rootP;
                    rank[rootP]++;
                }
            }
        }
    }
}
