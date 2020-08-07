package one.xingyi.datakata;

import lombok.RequiredArgsConstructor;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class MapReduce<From, To> {
    final Function<From, To> mapFn;
    final BinaryOperator<To> reducer;

    To mapReduce(Stream<From> stream) { return stream.map(mapFn).reduce(reducer).get(); }
}
