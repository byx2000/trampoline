package byx.trampoline.exception;

/**
 * 协程运行结束后，继续调用run方法则抛出此异常
 */
public class EndOfCoroutineException extends RuntimeException {
    private final Object retVal;

    public EndOfCoroutineException(Object retVal) {
        super("coroutine is end, return value is " + retVal);
        this.retVal = retVal;
    }

    /**
     * 获取协程返回值
     */
    public Object getRetVal() {
        return retVal;
    }
}
