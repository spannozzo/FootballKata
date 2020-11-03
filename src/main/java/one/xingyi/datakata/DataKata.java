package one.xingyi.datakata;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static one.xingyi.datakata.Reducers.smallestReducer;


@RequiredArgsConstructor @ToString
class Football {
    final String team;
    final int oneScore;
    final int twoScore;

    public int scoreDifference() {return Math.abs(oneScore - twoScore);}
}

public class DataKata {
    static CompositeParseData3<String, Integer, Integer> parserBuilder = ColumnsParser.parseString(8, 22).thenInt(44, 45).thenInt(51, 52);
    static Function<String, Football> parser = parserBuilder.parseWith((l, m, r) -> new Football(l, m, r));

    static <From, To> To mapReduce(Stream<From> stream, Function<From, To> mapFn, BinaryOperator<To> reducer) { return stream.map(mapFn).reduce(reducer).get(); }

    public static void main(String[] args) {
        System.out.println(mapReduce(FootballSource.stream("football.dat"), parser, smallestReducer(Football::scoreDifference)));
    }
}
