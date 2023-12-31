package byx.trampoline.example.generator;

import byx.trampoline.core.Generator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static byx.trampoline.core.Trampolines.pause;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciGeneratorTest {
    @Test
    public void testFibonacciGenerator() {
        Generator<Integer> generator = fibonacciGenerator(1, 2);
        List<Integer> nums = generator.stream().limit(10).toList();
        assertEquals(List.of(1, 2, 3, 5, 8, 13, 21, 34, 55, 89), nums);
    }

    private Generator<Integer> fibonacciGenerator(int a, int b) {
        AtomicInteger x = new AtomicInteger(a);
        AtomicInteger y = new AtomicInteger(b);

        return pause(a)
            .loop(() -> true, pause(y::get).then(() -> {
                // (x, y) -> (y, x + y)
                int t = x.get();
                x.set(y.get());
                y.set(t + y.get());
            }))
            .toGenerator();
    }
}
