package backend.academy.generator;

import backend.academy.enums.GeneratorType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GeneratorFactory {
    public static Generator getGenerator(GeneratorType type) {
        switch (type){
            case GeneratorType.KRUSKAL:
                return new KruskalGenerator();
            default:
                log.info("No such generator type");
        }
        return null;
    }
}
