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

@RequiredArgsConstructor
class MapReduce<From, To> {
    final Function<From, To> mapFn;
    final BinaryOperator<To> reducer;

    To mapReduce(Stream<From> stream) { return stream.map(mapFn).reduce(reducer).get(); }
}


public class DataKata {
    static CompositeParseData3<Integer, Integer, Integer> weatherParserBuilder = ColumnsParser.parseInt(3, 4).thenInt(7, 8).thenInt(13, 14);
    static CompositeParseData3<String, Integer, Integer> footballParserBuilder = ColumnsParser.parseString(8, 22).thenInt(44, 45).thenInt(51, 52);

    static MapReduce<String, Weather> weatherMapReduce = new MapReduce<>(weatherParserBuilder.parseWith(Weather::new), smallestReducer(Weather::tempRange));
    static MapReduce<String, Football> footballMapReduce = new MapReduce<>(footballParserBuilder.parseWith(Football::new), smallestReducer(Football::scoreDifference));

    public static void main(String[] args) {
        System.out.println(weatherMapReduce.mapReduce(FileLinesSource.stream("weather.dat")));
        System.out.println(footballMapReduce.mapReduce(FileLinesSource.stream("football.dat")));
    }
}
