package backend.academy.generator;

import backend.academy.enums.GeneratorStrategyType;

public class GeneratorStrategyFactory {
    public static GeneratorStrategy getGenerator(GeneratorStrategyType type) {
        return switch (type) {
            case GeneratorStrategyType.KRUSKAL -> new KruskalGeneratorStrategy();
            case GeneratorStrategyType.PRIM -> new PrimGeneratorStrategy();
            case GeneratorStrategyType.RANDOM_WALK-> new RandomWalkGeneratorStrategy();
            case GeneratorStrategyType.GROWING_TREE -> new GrowingTreeGeneratorStrategy();
        };
    }

    private GeneratorStrategyFactory() {
    }
}
