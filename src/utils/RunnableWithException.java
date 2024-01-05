package src.utils;

@FunctionalInterface
public interface RunnableWithException {
    void run() throws Exception;
}