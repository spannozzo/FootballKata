package one.xingyi.datakata;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.function.Function;

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
    static Function<String, Weather> weatherParser = ColumnsParser.parseInt(3, 4).thenInt(7, 8).thenInt(13, 14).parseWith(Weather::new);
    static Function<String, Football> footballParser = ColumnsParser.parseString(8, 22).thenInt(44, 45).thenInt(51, 52).parseWith(Football::new);

    static MapReduce<String, Weather> weatherMapReduce = new MapReduce<>(weatherParser, smallestReducer(Weather::tempRange));
    static MapReduce<String, Football> footballMapReduce = new MapReduce<>(footballParser, smallestReducer(Football::scoreDifference));

    public static void main(String[] args) {
        System.out.println(weatherMapReduce.mapReduce(FileLinesSource.stream("weather.dat")));
        System.out.println(footballMapReduce.mapReduce(FileLinesSource.stream("football.dat")));
    }
}
