package backend.academy.generator;

import backend.academy.enums.GeneratorStrategyType;

public class GeneratorStrategyFactory {
    public static GeneratorStrategy getGenerator(GeneratorStrategyType type) {
        return switch (type) {
            case GeneratorStrategyType.KRUSKAL_STRATEGY -> new KruskalGeneratorStrategy();
            case GeneratorStrategyType.PRIM_STRATEGY -> new PrimGeneratorStrategy();
            case GeneratorStrategyType.RANDOM_WALK_STRATEGY -> new RandomWalkGeneratorStrategy();
            case GeneratorStrategyType.GROWING_TREE_STRATEGY -> new GrowingTreeGeneratorStrategy();
        };
    }

    private GeneratorStrategyFactory() {
    }
}
