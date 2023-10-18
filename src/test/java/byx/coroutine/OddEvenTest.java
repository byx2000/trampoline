package byx.coroutine;

import byx.coroutine.core.Thunk;
import org.junit.jupiter.api.Test;

import static byx.coroutine.core.Thunks.exec;
import static byx.coroutine.core.Thunks.value;
import static org.junit.jupiter.api.Assertions.*;

public class OddEvenTest {
    @Test
    public void testOddEven() {
        for (int i = 0; i <= 100; i++) {
            assertEquals(isOdd1(i), isOdd2(i).run());
            assertEquals(isEven1(i), isEven2(i).run());
        }
    }

    @Test
    public void testStackOverflow() {
        assertThrows(StackOverflowError.class, () -> isOdd1(1000000));
        assertThrows(StackOverflowError.class, () -> isEven1(1000000));
        assertDoesNotThrow(() -> isOdd2(1000000).run());
        assertDoesNotThrow(() -> isEven2(1000000).run());
    }

    private boolean isOdd1(int n) {
        if (n == 0) {
            return false;
        }
        return isEven1(n - 1);
    }

    private boolean isEven1(int n) {
        if (n == 0) {
            return true;
        }
        return isOdd1(n - 1);
    }

    private Thunk<Boolean> isOdd2(int n) {
        if (n == 0) {
            return value(false);
        }
        return exec(() -> isEven2(n - 1));
    }

    private Thunk<Boolean> isEven2(int n) {
        if (n == 0) {
            return value(true);
        }
        return exec(() -> isOdd2(n - 1));
    }
}