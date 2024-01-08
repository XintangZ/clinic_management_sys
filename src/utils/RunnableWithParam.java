package src.utils;

@FunctionalInterface
public interface RunnableWithParam {
    void run(String param) throws Exception;
}
