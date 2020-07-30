package one.xingyi.datakata;


import lombok.RequiredArgsConstructor;

import java.util.function.Function;

interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}

@RequiredArgsConstructor
public class ColumnsParser<T> {

    public static <T1> ColumnsParser<T1> parse(Function<String, T1> fn, int from, int to) { return new ColumnsParser<>(fn, from, to);}

    public static ColumnsParser<String> parseString(int from, int to) { return new ColumnsParser<String>(String::trim, from, to);}
    public static ColumnsParser<Integer> parseInt(int from, int to) { return new ColumnsParser<Integer>(s -> Integer.parseInt(s.trim()), from, to);}

    final Function<String, T> fn;
    final int from;
    final int to;


    public T apply(String s) {
        return fn.apply(s.substring(from - 1, to));
    }

    <T2> CompositeParseData2<T, T2> then(Function<String, T2> fn2, int from2, int to2) {
        return new CompositeParseData2<T, T2>(this, new ColumnsParser<T2>(fn2, from2, to2));
    }

    CompositeParseData2<T, Integer> thenInt(int from2, int to2) {
        return then(Integer::parseInt, from2, to2);
    }
}

@RequiredArgsConstructor
class CompositeParseData2<T1, T2> {
    final private ColumnsParser<T1> p1;
    final private ColumnsParser<T2> p2;


    <T3> CompositeParseData3 then(Function<String, T3> fn3, int from3, int to3) {
        return new CompositeParseData3<T1, T2, T3>(p1, p2, new ColumnsParser<T3>(fn3, from3, to3));
    }

    CompositeParseData3<T1, T2, Integer> thenInt(int from2, int to2) {
        return then(Integer::parseInt, from2, to2);
    }

}

@RequiredArgsConstructor
class CompositeParseData3<T1, T2, T3> {
    final ColumnsParser<T1> p1;
    final ColumnsParser<T2> p2;
    final ColumnsParser<T3> p3;


    public <T> Function<String, T> parseWith(Function3<T1, T2, T3, T> constructor) {
        return s -> constructor.apply(
                p1.apply(s),
                p2.apply(s),
                p3.apply(s));
    }
}
