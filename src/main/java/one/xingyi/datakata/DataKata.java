package one.xingyi.datakata;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static one.xingyi.datakata.Reducers.smallestReducer;


@RequiredArgsConstructor @ToString @EqualsAndHashCode
class Football {
    final String team;
    final int oneScore;
    final int twoScore;

    public int scoreDifference() {return Math.abs(oneScore - twoScore);}
}

@RequiredArgsConstructor @ToString @EqualsAndHashCode
class Weather {
    final int dayNumber;
    final int maxTemp;
    final int minTemp;

    public int tempRange() {return maxTemp - minTemp;}
}

public class DataKata {
    static CompositeParseData3<Integer, Integer, Integer> weatherParserBuilder = ColumnsParser.parseInt(3, 4).thenInt(7, 8).thenInt(13, 14);
    static Function<String, Weather> weatherParser = weatherParserBuilder.parseWith((l, m, r) -> new Weather(l, m, r));

    static CompositeParseData3<String, Integer, Integer> footballParserBuilder = ColumnsParser.parseString(8, 22).thenInt(44, 45).thenInt(51, 52);
    static Function<String, Football> footBallParser = footballParserBuilder.parseWith((l, m, r) -> new Football(l, m, r));

    static <From, To> To mapReduce(Stream<From> stream, Function<From, To> mapFn, BinaryOperator<To> reducer) { return stream.map(mapFn).reduce(reducer).get(); }

    public static void main(String[] args) {
        System.out.println(mapReduce(FileLinesSource.stream("football.dat"), footBallParser, smallestReducer(Football::scoreDifference)));
        System.out.println(mapReduce(FileLinesSource.stream("weather.dat"), weatherParser, smallestReducer(Weather::tempRange)));
    }
}
