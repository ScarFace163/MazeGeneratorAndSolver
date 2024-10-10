package backend.academy.generator;

import backend.academy.enums.GeneratorStrategyType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorStrategyFactory {
    public static GeneratorStrategy getGenerator(GeneratorStrategyType type) {
        switch (type){
            case GeneratorStrategyType.KRUSKAL_STRATEGY:
                return new KruskalGeneratorStrategy();
            case GeneratorStrategyType.PRIM_STRATEGY:
                return new PrimGeneratorStrategy();
            default:
                log.info("No such generator type");
        }
        return null;
    }
}
