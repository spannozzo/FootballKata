package one.xingyi.datakata;

import java.util.function.BinaryOperator;
import java.util.function.Function;

public class Reducers {
    public static <T, C extends Comparable<C>> BinaryOperator<T> smallestReducer(Function<T, C> byFn) {
        return (acc, v) -> byFn.apply(acc).compareTo(byFn.apply(v)) > 0 ? v : acc;
    }

}
