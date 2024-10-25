package backend.academy;

import backend.academy.enums.GeneratorStrategyType;
import backend.academy.generator.GeneratorStrategy;
import backend.academy.generator.GeneratorStrategyFactory;
import backend.academy.generator.GrowingTreeGeneratorStrategy;
import backend.academy.generator.KruskalGeneratorStrategy;
import backend.academy.generator.PrimGeneratorStrategy;
import backend.academy.generator.RandomWalkGeneratorStrategy;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertSame;

public class GeneratorFactoryTest {
    @Test
    public void testGeneratorFactoryReturnRightObject(){
        GeneratorStrategy generator = GeneratorStrategyFactory.getGenerator(GeneratorStrategyType.KRUSKAL);
        assertSame(generator.getClass(), KruskalGeneratorStrategy.class);

        generator = GeneratorStrategyFactory.getGenerator(GeneratorStrategyType.PRIM);
        assertSame(generator.getClass(), PrimGeneratorStrategy.class);

        generator = GeneratorStrategyFactory.getGenerator(GeneratorStrategyType.GROWING_TREE);
        assertSame(generator.getClass(), GrowingTreeGeneratorStrategy.class);

        generator = GeneratorStrategyFactory.getGenerator(GeneratorStrategyType.RANDOM_WALK);
        assertSame(generator.getClass(), RandomWalkGeneratorStrategy.class);
    }
}
