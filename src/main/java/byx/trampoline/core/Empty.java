package byx.trampoline.core;

public class Empty<T> implements Trampoline<T> {
    private static final Empty<?> INSTANCE = new Empty<>();

    private Empty() {}

    @SuppressWarnings("unchecked")
    public static <T> Empty<T> getInstance() {
        return (Empty<T>) INSTANCE;
    }
}
